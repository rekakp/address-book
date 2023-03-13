package service;

import domain.Gender;
import domain.Person;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AddressBookQuery {


    public long countPersonWithGivenGender(List<Person> people, Gender gender) {
        return people.stream()
                .filter(p -> p.getGender() == gender)
                .count();
    }

    public Person findTheOldestPerson(List<Person> people) {
        return findTheMinUsingComparator(people, Comparator.comparing(Person::getDateOfBirth));
    }

    private Person findTheMinUsingComparator(List<Person> people, Comparator<Person> comparator) {
        return people.stream()
                .min(comparator)
                .orElse(null);
    }

    public Optional<Long> ageDifference(List<Person> people, String firstName, String secondName) {
        Person firstPerson = people.stream()
                .filter(p -> p.getName().equals(firstName))
                .findFirst()
                .orElse(null);
        Person secondPerson = people.stream()
                .filter(p -> p.getName().equals(secondName))
                .findFirst()
                .orElse(null);
        if (firstPerson != null && secondPerson != null) {
            return Optional.of(ChronoUnit.DAYS.between(firstPerson.getDateOfBirth()
                    , secondPerson.getDateOfBirth()));
        }
        return Optional.empty();
    }

}
