package proxy;

import person.flyweight.PersonFactory;
import person.flyweight.PersonName;

public class Guard {
    private final PeopleDatabase peopleDatabase;

    public Guard(PeopleDatabase peopleDatabase) {
        this.peopleDatabase = peopleDatabase;
    }

    public void addPerson(String name, String surname, double latitude, double longitude) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
        peopleDatabase.addPerson(name, surname, latitude, longitude);
    }

}
