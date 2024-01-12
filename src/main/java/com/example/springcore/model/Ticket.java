package com.example.springcore.model;

public class Ticket {
    String from;
    String to;
    User user;
    Integer price;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Ticket(String from, String to, User user, Integer price) {
        this.from = from;
        this.to = to;
        this.user = user;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", user=" + user +
                ", price=" + price +
                '}';
    }
}
