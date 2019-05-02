package com.cc.practicaltest.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class PracticalTestService {

    @Autowired
    private PracticalTestRepository repository;

    @Autowired
    private Clock clock;

    public List<Order> findOrdersSince(LocalDate date) {
        if (date.isAfter(LocalDate.now(clock))) {
            throw new InvalidDateException("Supplied date cannot be in future: " + date);
        }
        List<Order> orders = repository.getOrdersOnOrAfter(date);
        Collections.sort(orders);
        return orders;
    }
}
