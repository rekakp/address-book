package service;

import domain.Gender;
import domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

class AddressBookQueryTest {

    AddressBookQuery bookQuery;

    @BeforeEach
    public void setUp() {
        bookQuery = new AddressBookQuery();
    }

    @Test
    void countPersonWithGivenGenderAsMale() {
        List<Person> people = givenPeople();
        long count = bookQuery.countPersonWithGivenGender(people, Gender.MALE);
        assertThat(count, is(3L));
    }

    @Test
    void countPersonWithGivenGenderAsFemale() {
        List<Person> people = givenPeople();
        long count = bookQuery.countPersonWithGivenGender(people, Gender.FEMALE);
        assertThat(count, is(2L));
    }

    @Test
    void findTheOldestPerson() {
        List<Person> people = givenPeople();
        Person theOldestPerson = bookQuery.findTheOldestPerson(people);
        assertThat(theOldestPerson, hasProperty("name", is("Wes Jackson")));
    }

    @Test
    void ageDifferenceBetweenBillAndPaul() {
        List<Person> people = givenPeople();
        Long ageDifference = bookQuery.ageDifference(people, "Bill McKnight", "Paul Robinson").get();
        assertThat(ageDifference, is(2862L));
    }

    private List<Person> givenPeople() {
        Person bill = new Person("Bill McKnight", Gender.MALE,
                LocalDate.of(1977, 03, 16));
        Person paul = new Person("Paul Robinson", Gender.MALE,
                LocalDate.of(1985, 01, 15));
        Person gemma = new Person("Gemma Lane", Gender.FEMALE
                , LocalDate.of(1991, 11, 20));
        Person sarah = new Person("Sarah Stone", Gender.FEMALE
                , LocalDate.of(1980, 9, 20));
        Person wes = new Person("Wes Jackson", Gender.MALE
                , LocalDate.of(1974, 8, 14));

        return List.of(bill, paul, gemma, sarah, wes);
    }
}