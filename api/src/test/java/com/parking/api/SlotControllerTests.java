package com.parking.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.parking.api.controllers.SlotController;
import com.parking.api.models.Slot;
import com.parking.api.models.Reservation;
import com.parking.api.models.Billing;
import com.parking.api.models.SlotRepository;
import com.parking.api.models.ReservationRepository;
import com.parking.api.models.BillingRepository;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static java.util.Collections.singletonList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = SlotController.class)
public class SlotControllerTests {

	@Autowired
	private MockMvc mockMvc;

    @MockBean
    private SlotRepository slotRepository;
    @MockBean
    private ReservationRepository reservationRepository;
    @MockBean
    private BillingRepository billingRepository;
	@MockBean
    private JdbcTemplate jtm;


    @Test
    public void testListSlots() throws Exception {
        Slot newSlot = new Slot("EV-20KW", 1);

        List<Slot> allSlots = singletonList(newSlot);

        given(slotRepository.findAll()).willReturn(allSlots);

        mockMvc.perform(get("/slot/list")
                //.with(user("blaze").password("Q1w2e3r4"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(newSlot.getId())));
    }

    /**
     * Test the behavior when the car is parked successfully.
     * @throws Exception
     */
    @Test
    public void testParkCarSuccess() throws Exception {
        String carType = "EV-20KW";
        String carId = "EV-XXX-ZZ";
        Slot slot = new Slot(carType, 1);
        slot.setIsAvailable(true);
        List<Slot> allSlots = singletonList(slot);

        Example<Slot> query = any(Example.class);
        given(slotRepository.findAll(query)).willReturn(allSlots);

        mockMvc.perform(post(String.format("/slot/park?type=%s&carId=%s", carType, carId))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carId", is(carId)));
    }


    /**
     *  Test the case when car has been parked already.
     *  The API should return the existing reservation.
     * @throws Exception
     */
    @Test
    public void testCarParkedAlready() throws Exception {
        String carType = "EV-20KW";
        String carId = "EV-XXX-ZZ";

        Reservation reservation = new Reservation();
        reservation.setCarId(carId);
        List<Reservation> existingReservation = singletonList(reservation);

        Example<Reservation> query = any(Example.class);
        given(reservationRepository.findAll(query)).willReturn(existingReservation);

        mockMvc.perform(post(String.format("/slot/park?type=%s&carId=%s", carType, carId))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carId", is(carId)));
    }

    /**
     *  Not slot is available to park the car.
     * @throws Exception
     */
    @Test
    public void testParkCarNoSlotAvailable() throws Exception {
        String carType = "EV-20KW";
        String carId = "EV-XXX-ZZ";

        List<Slot> emptySlot = new ArrayList<Slot>();

        //Example<Slot> mockQuery = (Example<Slot>) mock(Example.class);
        Example<Slot> query = any(Example.class);
        given(slotRepository.findAll(query)).willReturn(emptySlot);

        mockMvc.perform(post(String.format("/slot/park?type=%s&carId=%s", carType, carId))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    /**
     *  Leave the parking slot, but does not find the reservation.
     * @throws Exception
     */
    @Test
    public void testLeaveSlotNoReservationFound() throws Exception {
        String carId = "EV-XXX-ZZ";

        Optional<Reservation> noReservation = Optional.empty();
        Example<Reservation> query = any(Example.class);
        given(reservationRepository.findOne(query)).willReturn(noReservation);

        mockMvc.perform(put(String.format("/slot/leave?carId=%s", carId))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     *  Leave the parking slot successfully.
     *  A billing record should be generated.
     * @throws Exception
     */
    @Test
    public void testLeaveSlotSuccess() throws Exception {
        String carId = "EV-XXX-ZZ";

        Reservation reservation = new Reservation();
        reservation.setCarId(carId);
        reservation.setBillingPolicy(1);
        reservation.setCheckinDatetime(LocalDateTime.now());
        Optional<Reservation> theReservation = Optional.of(reservation);
        Example<Reservation> query = any(Example.class);
        given(reservationRepository.findOne(query)).willReturn(theReservation);

        Billing billing = new Billing();
        billing.setCarId(carId);
        reservation.setBillingPolicy(1);
        billing.setId(1L);
        given(billingRepository.save(any(Billing.class))).willReturn(billing);

        // given(slotRepository.findById(any(Long.class))).willReturn(Optional.empty());

        mockMvc.perform(put(String.format("/slot/leave?carId=%s", carId))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.carId", is(carId)));
    }
}