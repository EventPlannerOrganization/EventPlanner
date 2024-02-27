package models;

import java.util.Objects;

public class Address {
    private String country;
    private String city;
    private String town;
    private String street;

    public Address(String country, String city, String town, String street) {
        this.country = country;
        this.city = city;
        this.town = town;
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(town, address.town) && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, town, street);
    }
}
