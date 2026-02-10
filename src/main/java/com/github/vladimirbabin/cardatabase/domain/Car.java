package com.github.vladimirbabin.cardatabase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String brand;
    private String model;
    private String color;
    private String registerNumber;
    private int year;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    public Car(String brand, String model, String color, String registerNumber, int year, int price, Owner owner) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
        this.owner = owner;
    }

//    @ManyToMany(mappedBy = "cars")
//    private Set<Owner> owners = new HashSet<>();
//
//    public Car(String brand, String model, String color, String registerNumber, int productionYear, int price, Set<Owner> owners) {
//        this.brand = brand;
//        this.model = model;
//        this.color = color;
//        this.registerNumber = registerNumber;
//        this.productionYear = productionYear;
//        this.price = price;
//        this.owners = owners;
//    }
}
