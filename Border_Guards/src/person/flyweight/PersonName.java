package person.flyweight;

public class PersonName {
    private final String firstName;

    public PersonName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }
}
