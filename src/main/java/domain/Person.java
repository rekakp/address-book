package domain;

import java.time.LocalDate;

public class Person {
    private final String name;
    private final Gender gender;
    private final LocalDate dateOfBirth;

    public Person(String name, Gender gender, LocalDate dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
