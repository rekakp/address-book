import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookRunnerIntegrationTest {

    ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
    AddressBookRunner addressBookRunner = new AddressBookRunner();

    @BeforeEach
    public void setUp() {
        PrintStream printStream = new PrintStream(outputCapture);
        System.setOut(printStream);
    }

    @AfterEach
    public void tearDown() {
        outputCapture = new ByteArrayOutputStream();
    }


    @Test
    public void testMain() {
       addressBookRunner.runCommands("TestAddressBook");
       assertThatTheResultIsAsExpected("Number of males: 3\n" +
                "Oldest person: Wes Jackson\n" +
                "Bill is 2862 days older than Paul");
    }

    @Test
    public void testMainWithDifferentInput() {
        addressBookRunner.runCommands("TestAddressBook2");
        assertThatTheResultIsAsExpected("Number of males: 2\n" +
                "Oldest person: Bill McKnight\n" +
                "Bill is 365 days older than Paul");
    }

    private void assertThatTheResultIsAsExpected(String expectedOutput) {
        String actual = getOutputFromConsole();
        assertEquals(expectedOutput, actual);
    }

    private String getOutputFromConsole() {
        String[] lines = outputCapture.toString().split(System.lineSeparator());
        return String.join("\n", lines);
    }




}