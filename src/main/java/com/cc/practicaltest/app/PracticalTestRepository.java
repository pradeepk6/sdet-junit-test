package com.cc.practicaltest.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PracticalTestRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String FETCH_ORDERS_QUERY
            = "SELECT o.order_number, " +
              "       c.forename, " +
              "       c.surname " +
              "FROM practicaltest.orders o " +
              "JOIN practicaltest.customers c ON o.customer_number = c.customer_number " +
              "WHERE o.order_date >= :date";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Order> getOrdersOnOrAfter(LocalDate date) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        List<Order> orders = jdbcTemplate.query(FETCH_ORDERS_QUERY, params, new Order.OrderRowMapper());
        if (orders.isEmpty()) {
            return null;
        }
        return orders;
    }
}
