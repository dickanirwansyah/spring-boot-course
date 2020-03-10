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
@Table(name = "bank")
@Entity
public class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "address_bank", nullable = false)
    private String addressBank;

    //rekening
    @Column(name = "number_account_bank", nullable = false)
    private String NumberAccountBank;

    //atas nama
    @Column(name = "as_name", nullable = false)
    private String asName;
}
