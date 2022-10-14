
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

import java.util.List;

public class Main {
    public static void main(String[] args) {

        DataProvider dataProvider = new DataProvider();
        List<String> sections = dataProvider.splitTimeAndSections("12001,2022,07,27,06,00,AAXX 27061 12001 46/// /2612 10157 20082 30071 40109 52004 333 10177 20154==");
        Time time = dataProvider.prepareTimeObject(sections.get(0));
        System.out.println(time.getDate());
        SectionZero sectionZero = dataProvider.prepareSectionZeroObject(sections.get(1));
        SectionOne sectionOne = dataProvider.prepareSectionOneObject(sections.get(2));


        System.out.println(sectionOne.getWindDirection());
        System.out.println(sectionOne.getAmountOfCloudCover());
        System.out.println(sectionOne.getWindSpeed());
        System.out.println(sectionOne.getAirTemperatureIndicator());
        System.out.println(sectionOne.getAirTemperatureValue());
        System.out.println(sectionOne.getDewTemperatureIndicator());
        System.out.println(sectionOne.getDewTempaeratureValue());
        System.out.println(sectionOne.getPressureLevelStation());
        System.out.println(sectionOne.getPressureLevelSea());
        System.out.println(sectionOne.getPressureTendencyCharacteristic());
        System.out.println(sectionOne.getPressureTendencyValue());
    }
}