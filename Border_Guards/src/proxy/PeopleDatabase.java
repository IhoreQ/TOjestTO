package proxy;

import person.Person;
import person.flyweight.PersonFactory;
import person.flyweight.PersonName;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class PeopleDatabase {

    private final PersonFactory peopleNameCache = new PersonFactory();
    private ArrayList<Person> peopleList = new ArrayList<>();

    public void addPerson(String name, String surname, double latitude, double longitude) {

        PersonName personName = peopleNameCache.getPersonName(name);
        peopleList.add(new Person(personName, surname, latitude, longitude));
    }

    public ArrayList<Person> getPeopleList() {
        return peopleList;
    }

    public void saveToFile() {
        try (PrintWriter printWriter = new PrintWriter("people.csv")){

            for (Person person : peopleList) {
                printWriter.printf("%s, %s, %f, %f\n", person.getName(), person.getSurname(), person.getLatitude(), person.getLongitude());
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
