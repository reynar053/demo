package org.example.model.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Company {
    @JsonProperty("name")

    private String name;
    @JsonProperty("catchPhrase")

    private String catchPhrase;
    @JsonProperty("bs")

    private String bs;

    @Override
    public String toString() {
        return "Company{" +
                "name:'" + name + '\'' +
                ", catchPhrase:'" + catchPhrase + '\'' +
                ", bs:'" + bs + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) && Objects.equals(catchPhrase, company.catchPhrase) && Objects.equals(bs, company.bs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, catchPhrase, bs);
    }
}
