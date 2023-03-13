package service;

import domain.Gender;
import domain.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContentParser {

    public List<Person> parseToPerson(List<String> lines) {
        List<Person> personList = new ArrayList<>();
        for (String line : lines) {
            String[] words = line.split(", ");
            String name = words[0];
            Gender gender = getGender(words[1]);
            LocalDate dateOfBirth = getDateOfBirth(words[2]);
            personList.add(new Person(name, gender, dateOfBirth));
        }
        return personList;
    }

    private Gender getGender(String word) {
        return Gender.valueOf(word.toUpperCase());
    }

    private LocalDate getDateOfBirth(String word) {
        LocalDate dob = LocalDate.parse(word, DateTimeFormatter.ofPattern("dd/MM/yy"));
        if (dob.isAfter(LocalDate.now())) {
            return dob.minusYears(100);
        }
        return dob;
    }
}
