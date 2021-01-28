package com.parking.api.controllers;

import com.parking.api.models.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@RestController
public class SlotController {

    /** connection to the backend database */
    @Autowired
    private JdbcTemplate jtm;

    /** auto-map to the table in the database */
    @Autowired
    private SlotRepository slotRepository;

    Logger logger = LoggerFactory.getLogger(SlotController.class);

    @RequestMapping("/ping")
    public String ping() {
        return "alive";
    }

    /**
     * list all the parking slots
     * @return
     */
    @RequestMapping("/slot/list")
    public List<Slot> listSlots() {

        // String sql = "SELECT * FROM SLOT;";
        // return jtm.query(sql, new BeanPropertyRowMapper<>(ParkingSlot.class));

        List<Slot> slotList = new ArrayList<Slot>();
        for (Slot slot : slotRepository.findAll()) {
            slotList.add(slot);
        }
        return slotList;
    }

    @GetMapping("/slot/status")
    public Slot getSlotStatus(@RequestParam(value = "slotId") Long slotId) {

        String sql = "SELECT * FROM SLOT WHERE ID = " + slotId;
        List<Slot> slot = jtm.query(sql, new BeanPropertyRowMapper<>(Slot.class));
        if (slot.size() == 0) {
            throw new SlotNotFoundException(slotId);
        }
        return slot.get(0);
    }

    @GetMapping("/slot/park")
    public Slot bookSlot(@RequestParam(value = "type") String type,
                         @RequestParam(value = "carId") String carId) {

        logger.debug(String.format("[booking request] type: %s, carId: %s", type, carId));

        String sql = String.format(
            "SELECT * FROM SLOT WHERE IS_AVAILABLE = TRUE AND TYPE = '%s'", type);

        List<Slot> slots = jtm.query(sql, new BeanPropertyRowMapper<>(Slot.class));
        if (slots.size() == 0) {
            throw new NoSlotAvailableException(type);
        }

        return slots.get(0);
    }

    // @RequestMapping("/slot/status/{slotId}")
    // public ParkingSlot getSlotStatus(@PathVariable Long slotId) {
    //     String sql = "SELECT * FROM SLOT WHERE ID = " + slotId;
    //     List<ParkingSlot> result = jtm.query(sql, new BeanPropertyRowMapper<>(ParkingSlot.class));
    //     return result.get(0);
    // }

    @ExceptionHandler(SlotNotFoundException.class)
    public ResponseEntity<String> noSlotFound(SlotNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NoSlotAvailableException.class)
    public ResponseEntity<String> noSlotAvailable(NoSlotAvailableException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}