package com.cc.practicaltest.app;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;

public class Order implements Comparable<Order> {
    private Integer orderNumber;
    private String forename, surname;

    public Order(Integer orderNumber, String forename, String surname) {
        this.orderNumber = orderNumber;
        this.forename = forename;
        this.surname = surname;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public int compareTo(Order o) {
        return Comparator.comparing(Order::getSurname)
                         .thenComparing(Order::getForename)
                         .thenComparing(Order::getOrderNumber)
                         .compare(this, o);
    }

    public static class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs, int rowNumber) throws SQLException {
            return new Order(rs.getInt("order_number"),
                             rs.getString("forename"),
                             rs.getString("surname"));
        }
    }
}
