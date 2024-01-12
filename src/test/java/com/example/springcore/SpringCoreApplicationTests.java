//package com.example.springcore;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class SpringCoreApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}
package com.example.springcore;

import com.example.springcore.controller.AppController;
import com.example.springcore.entity.BookingResponseEntity;
import com.example.springcore.model.Email;
import com.example.springcore.model.Section;
import com.example.springcore.model.Ticket;
import com.example.springcore.model.seatModifyRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class AppControllerTest {

    @Mock
    private List<BookingResponseEntity> tickets;

    @InjectMocks
    private AppController appController;

    private MockMvc mockMvc;

    @Test
    void bookTickets_shouldReturnBookingResponseEntity() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
        com.example.springcore.model.User newuser= new com.example.springcore.model.User("sarthak","arora","sarthak@gmail.com");
        Ticket mockTicket = new Ticket("London","Paris", newuser ,20);
        BookingResponseEntity mockBookingResponseEntity = new BookingResponseEntity();
        mockBookingResponseEntity.setSeatNumber(1);
        mockBookingResponseEntity.setSection("A");
        mockBookingResponseEntity.setTicket(mockTicket);

        Mockito.when(tickets.size()).thenReturn(19);
        Mockito.when(tickets.add(Mockito.any(BookingResponseEntity.class))).thenReturn(true);
        String ticketJson = "{\n" +
                "    \"from\":\"London\",\n" +
                "    \"to\":\"Paris\",\n" +
                "    \"user\":{\n" +
                "        \"firstName\":\"sarthak\",\n" +
                "        \"lastName\":\"Arora\",\n" +
                "        \"email\":\"somu@gmail.com\"\n" +
                "    },\n" +
                "    \"price\":20\n" +
                "}";
        ResultActions result = mockMvc.perform(post("/booking/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ticketJson));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.seatNumber").value(1))
                .andExpect(jsonPath("$.section").value("A"));
    }

    @Test
    void details_shouldReturnBookingResponseEntity() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(appController).build();

        Email mockEmail = new Email(/* Provide necessary fields for Email */);
        BookingResponseEntity mockBookingResponseEntity = new BookingResponseEntity();
        mockBookingResponseEntity.setSeatNumber(1);
        mockBookingResponseEntity.setSection("A");

//        Mockito.when(tickets.stream()).thenReturn(List.of(mockBookingResponseEntity).stream());

        String emailJson = "{\n" +
                "    \"email\": \"somu@gmail.com\"\n" +
                "}";
        ResultActions result = mockMvc.perform(post("/booking/details")
                .contentType(MediaType.APPLICATION_JSON)
                .content(emailJson));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.seatNumber").value(1))
                .andExpect(jsonPath("$.section").value("A"));
    }
}
