package service;

import domain.Gender;
import domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContentParserTest {

    ContentParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ContentParser();
    }

    @Test
    public void whenParsingVerifyTheReturnedListSize() {
        List<String> lines = givenListofPerson();
        List<Person> personList = parser.parseToPerson(lines);
        assertThat(personList, hasSize(2));
    }

    @Test
    public void whenParsingVerifyTheReturnedListContentForAPerson() {
        List<String> lines = givenAPerson();
        List<Person> personList = parser.parseToPerson(lines);
        assertThat(personList, hasItem(hasProperty("name", is("Bill McKnight"))));
        assertThat(personList, hasItem(hasProperty("gender", is(Gender.MALE))));
        LocalDate dateOfBirth = LocalDate.of(1977, 3, 16);
        assertThat(personList, hasItem(hasProperty("dateOfBirth", is(dateOfBirth))));
    }

    @Test
    public void whenParsingVerifyTheReturnedListContentForPeople() {
        List<String> lines = givenListofPerson();
        List<Person> personList = parser.parseToPerson(lines);

        assertThat(personList, hasItem(hasProperty("name", is("Bill McKnight"))));
        assertThat(personList, hasItem(hasProperty("gender", is(Gender.MALE))));
        LocalDate dateOfBirth = LocalDate.of(1977, 3, 16);
        assertThat(personList, hasItem(hasProperty("dateOfBirth", is(dateOfBirth))));

        assertThat(personList, hasItem(hasProperty("name", is("Gemma Lane"))));
        assertThat(personList, hasItem(hasProperty("gender", is(Gender.FEMALE))));
        LocalDate dob2 = LocalDate.of(1991, 11, 20);
        assertThat(personList, hasItem(hasProperty("dateOfBirth", is(dob2))));
    }

    @Test
    public void whenParsingWithInvalidGenderVerifyIfExceptionIsThrown() {
        List<String> lines = givenAPersonWithInvalidGender();
        assertThrows(IllegalArgumentException.class, () -> parser.parseToPerson(lines));
    }

    @Test
    public void whenParsingWithInvalidDobVerifyIfExceptionIsThrown() {
        List<String> lines = givenAPersonWithInvalidDob();
        assertThrows(IllegalArgumentException.class, () -> parser.parseToPerson(lines));
    }

    private List<String> givenAPersonWithInvalidGender() {
        return List.of("Bill McKnight, Mal, 16/03/77");
    }

    private List<String> givenAPersonWithInvalidDob() {
        return List.of("Bill McKnight, Mal, 16/16/77");
    }

    private List<String> givenAPerson() {
        return List.of("Bill McKnight, Male, 16/03/77");
    }

    private List<String> givenListofPerson() {
        return List.of("Bill McKnight, Male, 16/03/77", "Gemma Lane, Female, 20/11/91");
    }

}