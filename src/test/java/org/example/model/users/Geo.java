package org.example.model.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Geo {
    @JsonProperty("lat")

    private String lat;
    @JsonProperty("lng")

    private String lng;

    @Override
    public String toString() {
        return "Geo{" +
                "lat:'" + lat + '\'' +
                ", lng:'" + lng + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geo geo = (Geo) o;
        return Objects.equals(lat, geo.lat) && Objects.equals(lng, geo.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }
}
