package com.parking.api;

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

import java.util.List;

@RestController
public class SlotController {

    @Autowired
    private JdbcTemplate jtm;

    @RequestMapping("/slot/list")
    public List<ParkingSlot> listSlots() {

        String sql = "SELECT * FROM SLOT;";

        return jtm.query(sql, new BeanPropertyRowMapper<>(ParkingSlot.class));
    }

    @GetMapping("/slot/status")
    public ParkingSlot getSlotStatus(@RequestParam(value = "slotId") Long slotId) {

        String sql = "SELECT * FROM SLOT WHERE ID = " + slotId;
        List<ParkingSlot> result = jtm.query(sql, new BeanPropertyRowMapper<>(ParkingSlot.class));
        if (result.size() == 0) {
            throw new SlotNotFoundException(slotId);
        }
        return result.get(0);
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


}