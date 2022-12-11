package person;

import person.flyweight.PersonName;

public class Person {
    private final PersonName name;
    private final String surname;
    private final String latitude;
    private final String longitude;

    public Person(PersonName name, String surname, String latitude, String longitude) {
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

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
