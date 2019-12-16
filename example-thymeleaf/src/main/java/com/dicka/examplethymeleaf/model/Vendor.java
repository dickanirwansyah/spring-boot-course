package com.dicka.examplethymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vendor")
@Entity
public class Vendor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    /** personal or non personal **/
    private String vendorType;
    private String npwp;

    @Lob
    @Column(name = "npwp_file", length = Integer.MAX_VALUE)
    private byte[] npwpFile;
    private String bankAccount;
    private String bankName;
    @Lob
    @Column(name = "cover_bank_acc", length = Integer.MAX_VALUE)
    private byte[] coverBankAcc;
    private Date createdAt;
    private Date updatedAt;
}
