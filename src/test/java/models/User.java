package models;

import java.util.Map;
import java.util.Objects;

public class User {
    private final int userNumber;
    private final Map<String, String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public User(Map<String, String> map, int userNumber) {
        this.userNumber = userNumber;
        this.map = map;
    }

    public User(Map<String, String> map) {
        this.userNumber = -1;
        this.map = map;
    }

    public int getUserNumber() {
        return userNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(map, user.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    @Override
    public String toString() {
        return "User{" +
                "userNumber=" + userNumber +
                ", map=" + map +
                '}';
    }
}
