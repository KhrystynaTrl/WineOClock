package it.wineoclock.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductDto {
    @NotBlank(message = "Please insert the type")
    private String type;
    @NotBlank(message = "Please insert the name")
    private String name;
    @Min(message = "Please insert a min capacity",value = 50)
    @Max(message = "Please insert a valid number", value = 5000)
    private int capacity;
    @NotBlank(message = "Please insert a valid productor")
    private String productor;

    private String country;

    public ProductDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
