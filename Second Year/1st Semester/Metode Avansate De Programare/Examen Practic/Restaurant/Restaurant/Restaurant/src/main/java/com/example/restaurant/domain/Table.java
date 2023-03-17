package com.example.restaurant.domain;

public class Table extends Entity<Integer> {

    public Table(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + getId() +
                '}';
    }
}
