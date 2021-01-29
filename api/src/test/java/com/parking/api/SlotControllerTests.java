package com.parking.api;

import java.util.List;

import com.parking.api.controllers.SlotController;
import com.parking.api.models.Slot;
import com.parking.api.models.SlotRepository;
import com.parking.api.models.ReservationRepository;
import com.parking.api.models.BillingRepository;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static java.util.Collections.singletonList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

}