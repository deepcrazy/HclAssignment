package com.deepanshu.hclassignment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private long postcode;

    public Address(String street, String city, String state, long postcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }
}
