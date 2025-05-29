package com.example.zboruri.domain;

import java.time.LocalDateTime;

public class Ticket extends Entity<Integer>{
    private String username;
    private Long id_zbor;
    private LocalDateTime purchase_data;

    public Ticket(Integer integer, String username, Long id_zbor, LocalDateTime purchase_data) {
        super(integer);
        this.username = username;
        this.id_zbor = id_zbor;
        this.purchase_data = purchase_data;
    }

    public String getUsername() {
        return username;
    }

    public Long getId_zbor() {
        return id_zbor;
    }

    public LocalDateTime getPurchase_data() {
        return purchase_data;
    }
}
