
/*
* 0. Podanie przedziału czasowego przez użytkownika
* 1. Pobranie danych z pliku do Stringa
* 2. Odseparowanie danych -> Czas, Wyniki sekcji 0 i 1, Sekcja 3 <- do wyrzucenia
* 3. Utworzenie obiektu klasy Czas, utworzenie obiektów sekcji 0 i sekcji 1
* 4. Utworzenie obiektu klasy Pomiar, opakowującej wszystkie obiekty sekcji 0 i sekcji 1
* 5. Wyświetlenie wyniku w konsoli -> Pomiar.toString()
* */

import data.DataProvider;
import sections.sectionone.SectionOne;
import sections.sectionzero.SectionZero;
import sections.time.Time;
import view.Viewer;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Viewer viewer = new Viewer();
        viewer.run();
    }
}