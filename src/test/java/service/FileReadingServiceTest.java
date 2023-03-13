package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;


class FileReadingServiceTest {

    public static final String FILE_NAME = "TestAddressBook";
    FileReadingService fileReader;

    @BeforeEach
    public void setUp() {
        fileReader = new FileReadingService();
    }

    @Test
    public void givenAFileVerifyTheNumberOfLines() {
        List<String> lines = fileReader.readFile(FILE_NAME);
        assertThat(lines, hasSize(5));
    }

    @Test
    public void givenAFileVerifyIfTheContentsAreValid() {
        List<String> lines = fileReader.readFile(FILE_NAME);
        assertThat(lines, hasItem("Bill McKnight, Male, 16/03/77"));
        assertThat(lines, hasItem("Paul Robinson, Male, 15/01/85"));
        assertThat(lines, hasItem("Gemma Lane, Female, 20/11/91"));
    }

}