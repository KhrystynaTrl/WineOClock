package it.wineoclock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_detail")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "city", length = 30, nullable = false)
    private String city;
    @Column(name = "street", length = 40, nullable = false)
    private String street;
    @Column(name = "street_number", nullable = false)
    private int streetNumber;
    @Column(name = "zip_code", length = 5, nullable = false)
    private String zipCode;
    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;

    public UserDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
