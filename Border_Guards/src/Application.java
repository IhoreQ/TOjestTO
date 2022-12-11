import person.Person;
import proxy.Guard;
import proxy.PeopleDatabase;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Application {
    private final Scanner scanner;
    private final PeopleDatabase peopleDatabase;
    private final Guard guard;

    public Application() {
        scanner = new Scanner(System.in);
        peopleDatabase = new PeopleDatabase();
        guard = new Guard(peopleDatabase);
    }

    private void printMenu() {
        System.out.println("1 - Dodaj osobe\n2 - Pokaz liste osob\n3 - Zapisz osoby do pliku\n4 - Wczytaj osoby z pliku\n5 - Wyjdz");
    }
    private int parseInt(String message) {
        System.out.println(message);
        int input;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Wprowadzono bledne dane!");
            return parseInt(message);
        }
        return input;
    }

    private String parseName(String message) {
        System.out.println(message);
        String input;
        String regexPattern = "^(?=.{1,256}$)[A-ZĆŁŚŻŹa-ząćęńóśżź\\p{L}]+['-]?[A-ZĆŁŚŻŹa-ząćęńóśżź]+";

        try {
            input = scanner.nextLine();
            if (!checkPattern(input, regexPattern))
                throw new Exception();
        } catch (Exception e) {
            System.out.println("Wprowadzono błędne dane!");
            return parseName(message);
        }
        return input;
    }

    private String parseLatitude(String message) {

        System.out.println(message);
        String input;
        String regexPattern = "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";

        try {
            input = scanner.nextLine();
            if (!checkPattern(input, regexPattern))
                throw new Exception();
        } catch (Exception e) {
            System.out.println("Wprowadzono błędne dane!");
            return parseLatitude(message);
        }
        return input;
    }

    private String parseLongitude(String message) {

        System.out.println(message);
        String input;
        String regexPattern = "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";

        try {
            input = scanner.nextLine();
            if (!checkPattern(input, regexPattern))
                throw new Exception();
        } catch (Exception e) {
            System.out.println("Wprowadzono błędne dane!");
            return parseLongitude(message);
        }
        return input;
    }

    public boolean checkPattern(String line, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(line).matches();
    }

    private void showPersonInfo(Person p) {
        System.out.println("Dane osobowe: " + p.getName() + " " + p.getSurname());
        System.out.println("Szerokosc geograficzna: " + p.getLatitude());
        System.out.println("Dlugosc geograficzna: " + p.getLongitude());
    }

    public void run() {
        int choice = 0;
        String name, surname, latitude, longitude;

        while (choice != 5) {
            printMenu();
            choice = parseInt("Wybierz opcje: ");

            switch (choice) {
                case 1:
                    name = parseName("Podaj imię: ");
                    surname = parseName("Podaj nazwisko: ");
                    latitude = parseLatitude("Podaj szerokosc geograficzna: ");
                    longitude = parseLongitude("Podaj dlugosc geograficzna: ");
                    guard.addPerson(name, surname, latitude, longitude);
                    break;
                case 2:
                    System.out.println("--------------------");
                    for (Person p : peopleDatabase.getPeopleList()) {
                        showPersonInfo(p);
                        System.out.println("--------------------");
                    }
                    break;
                case 3:
                    peopleDatabase.saveToFile();
                    break;
                case 4:
                    peopleDatabase.loadFromFile();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Nie ma takiej opcji.");
                    break;
            }


        }
    }
}
