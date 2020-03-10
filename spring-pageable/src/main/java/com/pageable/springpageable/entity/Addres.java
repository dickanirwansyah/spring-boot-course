package com.pageable.springpageable.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Addres implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "village")
    private String village;

    @Column(name = "number_home")
    private String numberHome;

    @Column(name = "postal_code")
    private String postalCode;
}
