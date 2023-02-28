package org.example.model.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Address{
    @JsonProperty("street")

    private String street;
    @JsonProperty("suite")

    private String suite;
    @JsonProperty("city")

    private String city;
    @JsonProperty("zipcode")

    private String zipcode;
    @JsonProperty("geo")

    private Geo geo;

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", geo=" + geo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(suite, address.suite) && Objects.equals(city, address.city) && Objects.equals(zipcode, address.zipcode) && Objects.equals(geo, address.geo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, suite, city, zipcode, geo);
    }
}

