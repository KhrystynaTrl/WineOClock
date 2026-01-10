package it.wineoclock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UserDetailDto {
    @NotBlank(message = "City mandatory" )
    private String city;
    @NotBlank(message = "Street mandatory")
    private String street;
    @Min(message = "Min number 1", value = 1)
    private int streetNumber;
    @NotBlank(message = "Zip code mandatory" )
    private String zipCode;

    public UserDetailDto(){}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
