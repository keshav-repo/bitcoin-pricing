package com.sapient.bitcoin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stats {
    private String query;
    private String date;
    private double value;
    public Stats(String query, double value) {
        this.query = query;
        this.value = value;
    }
}
