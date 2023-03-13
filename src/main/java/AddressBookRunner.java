import domain.Gender;
import domain.Person;
import service.AddressBookQuery;
import service.ContentParser;
import service.FileReadingService;

import java.util.List;
import java.util.Optional;

public class AddressBookRunner {

    public static final String ADDRESS_BOOK = "AddressBook";

    // Can be injected via spring. Since its a very small application, I have created the dependencies
    AddressBookQuery addressBookQuery;
    ContentParser contentParser;
    FileReadingService fileReadingService;

    AddressBookRunner(){
        addressBookQuery = new AddressBookQuery();
        contentParser = new ContentParser();
        fileReadingService = new FileReadingService();
    }

    public static void main(String[] args) {
        AddressBookRunner addressBookRunner = new AddressBookRunner();
        addressBookRunner.runCommands(ADDRESS_BOOK);

    }

    public void runCommands(String fileName) {
        List<Person> people = parseTheFile(fileName);
        AddressBookQuery addressBookQuery = getAddressBookQuery();

        // How many males are in the address book?
        long malesCount = addressBookQuery.countPersonWithGivenGender(people, Gender.MALE);
        System.out.println("Number of males: " + malesCount);

        // Who is the oldest person in the address book?
        Person oldestPerson = addressBookQuery.findTheOldestPerson(people);
        System.out.println("Oldest person: " + oldestPerson.getName());

        // How many days older is Bill than Paul?
        Optional<Long> ageDifference = addressBookQuery.ageDifference(people, "Bill McKnight", "Paul Robinson");
        System.out.println("Bill is " + ageDifference.get() + " days older than Paul");
    }

    public List<Person> parseTheFile(String fileName){
        List<String> lines = fileReadingService.readFile(fileName);
        return contentParser.parseToPerson(lines);
    }

    public AddressBookQuery getAddressBookQuery() {
        return addressBookQuery;
    }

    public ContentParser getContentParser() {
        return contentParser;
    }

    public FileReadingService getFileReadingService() {
        return fileReadingService;
    }
}
