package person.flyweight;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonFactory {
    private HashMap<String, PersonName> personNames = new HashMap<>();

    public PersonName getPersonName(String name) {
        if (personNames.get(name) == null) {
            personNames.put(name, new PersonName(name));
        }
        return personNames.get(name);
    }
}
