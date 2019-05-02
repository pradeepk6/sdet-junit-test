package com.cc.practicaltest;

import com.cc.practicaltest.app.Order;
import com.cc.practicaltest.app.PracticalTestService;
import com.cc.practicaltest.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class AppRunner {
    private static final Logger LOG = LoggerFactory.getLogger(AppRunner.class);

    public static void main(String[] args) {
        LOG.info("Starting application...");

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PracticalTestService service = context.getBean(PracticalTestService.class);

        List<Order> orders = service.findOrdersSince(LocalDate.of(2019, 1, 1));

        for (Order o : orders) {
            LOG.info("Customer: {} {}, Order#: {}", o.getForename(), o.getSurname(), o.getOrderNumber());
        }
    }
}
