package com.dicka.springcoronatracking.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person implements Serializable {

    private int id;
    private String name;
    private String gender;
    private double latitude;
    private double longitude;
}
