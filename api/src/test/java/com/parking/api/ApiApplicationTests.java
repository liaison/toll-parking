package com.parking.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.parking.api.models.Reservation;
import com.parking.api.models.ReservationRepository;
import com.parking.api.models.Slot;
import com.parking.api.models.SlotRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.RunWith;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

//@SpringBootTest

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
class ApiApplicationTests {

	@Autowired
    private SlotRepository slotRepository;

	@Autowired
    private ReservationRepository reservationRepository;

	// @Test
	// void contextLoads() {
	// 	assertTrue(true);
	// }

    @Test
    public void testSlotRepository() {
		Slot newSlot = new Slot("EV-20KW", 1);
		slotRepository.save(newSlot);
		List<Slot> slots = new ArrayList<Slot>();
		for (Slot slot : slotRepository.findAll()) {
			slots.add(slot);
		}
		assertTrue(slots.size() == 1);

		Optional<Slot> foundSlot = slotRepository.findById(1L);
		assertEquals(newSlot, foundSlot.get());
    }

	@Test
    public void testReservationRepository() {

		Reservation newReservation = new Reservation(1L, "EV-XXX-YY", 1);
		reservationRepository.save(newReservation);
		List<Reservation> reservations = new ArrayList<Reservation>();
		for (Reservation reservation : reservationRepository.findAll()) {
			reservations.add(reservation);
		}
		assertTrue(reservations.size() == 1);

		Optional<Reservation> foundReservation = reservationRepository.findById(1L);
		assertEquals(newReservation, foundReservation.get());
    }

}
