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

    public void addPerson(String name, String surname, String latitude, String longitude) {

        PersonName personName = peopleNameCache.getPersonName(name);
        peopleList.add(new Person(personName, surname, latitude, longitude));
    }

    public ArrayList<Person> getPeopleList() {
        return peopleList;
    }

    public void saveToFile() {
        try (PrintWriter printWriter = new PrintWriter("people.csv")){

            for (Person person : peopleList) {
                printWriter.printf("%s,%s,%s,%s\n", person.getName(), person.getSurname(), person.getLatitude(), person.getLongitude());
            }


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println("Zapis wykonany prawidłowo.");
    }

    public void loadFromFile() {
        String line = "";
        final int DATA_LENGTH = 4;
        ArrayList<Person> peopleListCopy = new ArrayList<>(peopleList);

        try (BufferedReader br = new BufferedReader(new FileReader("people.csv"))) {
            peopleList.clear();
            while ((line = br.readLine()) != null) {
                String[] personData = line.split(",");
                if (personData.length == DATA_LENGTH) {
                    addPerson(personData[0], personData[1], personData[2], personData[3]);
                }
            }
        } catch(IOException e) {
            peopleList = peopleListCopy;
            e.printStackTrace();
        }
        System.out.println("Załadowano dane prawidłowo.");
    }
}
