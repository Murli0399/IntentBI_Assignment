package com.murli.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vin;
    private Integer year;
    private String vehicleType;
    private String drivetrain;
    private Integer cylinders;
    private String bodySubtype;
    private Integer doors;
    private String madeIn;
    private String trim;
    private String engine;
    private String model;
    private Double engineSize;
    private String fuelType;
    private String trimR;
    private String make;
    private Integer price;
    private String sellerType;
    private String source;
    private String interiorColor;
    private String exteriorColor;

}
