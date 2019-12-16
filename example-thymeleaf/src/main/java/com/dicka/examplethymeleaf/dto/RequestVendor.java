package com.dicka.examplethymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class RequestVendor implements Serializable {

    @NotBlank(message = "first name cannot be null")
    private String firstName;

    @NotBlank(message = "last name cannot be null")
    private String lastName;

    @NotBlank(message = "vendor type cannot be null")
    private String vendorType;

    private String npwp;
    private MultipartFile npwpFile;

    @NotBlank(message = "back account cannot be null")
    private String backAccount;

    @NotBlank(message = "back name cannot be null")
    private String bankName;
    private MultipartFile coverBankAcc;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public MultipartFile getNpwpFile() {
        return npwpFile;
    }

    public void setNpwpFile(MultipartFile npwpFile) {
        this.npwpFile = npwpFile;
    }

    public String getBackAccount() {
        return backAccount;
    }

    public void setBackAccount(String backAccount) {
        this.backAccount = backAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public MultipartFile getCoverBankAcc() {
        return coverBankAcc;
    }

    public void setCoverBankAcc(MultipartFile coverBankAcc) {
        this.coverBankAcc = coverBankAcc;
    }

    @Override
    public String toString() {
        return "RequestVendor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", vendorType='" + vendorType + '\'' +
                ", npwp='" + npwp + '\'' +
                ", npwpFile=" + npwpFile +
                ", backAccount='" + backAccount + '\'' +
                ", bankName='" + bankName + '\'' +
                ", coverBankAcc=" + coverBankAcc +
                '}';
    }
}
