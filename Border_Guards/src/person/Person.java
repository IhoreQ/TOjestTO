package person;

import person.flyweight.PersonName;

public class Person {
    private final PersonName name;
    private final String surname;
    private final double latitude;
    private final double longitude;

    public Person(PersonName name, String surname, double latitude, double longitude) {
        this.name = name;
        this.surname = surname;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name.getFirstName();
    }

    public String getSurname() {
        return surname;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
