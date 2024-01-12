package com.example.springcore.controller;

import com.example.springcore.entity.BookingResponseEntity;
import com.example.springcore.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/booking")
@SuppressWarnings("unused")
public class AppController {
//    List<Integer> seats = new ArrayList<>(Collections.nCopies(20, 0));
    List<BookingResponseEntity> tickets = new ArrayList<>(20);

    @PostMapping("/book")
    BookingResponseEntity bookTickets(@RequestBody Ticket ticket) {
        BookingResponseEntity bookingResponseEntity = new BookingResponseEntity();
        if(tickets.size() == 20) {
            bookingResponseEntity.setSection("NA");
            bookingResponseEntity.setSeatNumber(-1);
            return bookingResponseEntity;
        }
        int i=tickets.size();
        bookingResponseEntity.setSeatNumber(tickets.size());
        if(tickets.size() < 10) {
            bookingResponseEntity.setSection("A");
        } else {
            bookingResponseEntity.setSection("B");
        }
        bookingResponseEntity.setTicket(ticket);
        tickets.add(bookingResponseEntity);
        return bookingResponseEntity;
    }

    @PostMapping("/details")
    BookingResponseEntity details(@RequestBody Email email) {
        for (BookingResponseEntity ticket : tickets) {
            if (ticket.getTicket().getUser().getEmail().equals(email.email))
                return ticket;
        }
        return null;
    }


    @PostMapping("/viewUsers")
    List<BookingResponseEntity> viewUsers(@RequestBody Section section) {
        List<BookingResponseEntity> bookings=new ArrayList<>();
        for (BookingResponseEntity ticket : tickets) {
            if (ticket.getSection().equals(section.section))
                bookings.add(ticket);
        }
        return bookings;
    }


    @PostMapping("/removeUser")
    String removeUser(@RequestBody Email email) {
        for (BookingResponseEntity ticket : tickets) {
            if (ticket.getTicket().getUser().getEmail().equals(email.email)){
                tickets.remove(ticket);
                return "removed";
            }

        }
        return "not removed";
    }
    @PostMapping("/modifySeat")
    String changeSection(@RequestBody seatModifyRequest request) {
        for (BookingResponseEntity ticket : tickets) {
            if (ticket.getTicket().getUser().getEmail().equals(request.email.email)){
                ticket.setSection(request.section.section);
                return "section changed";
            }

        }
        return "section not changed";
    }
}
