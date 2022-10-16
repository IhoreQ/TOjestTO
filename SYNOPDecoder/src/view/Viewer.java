package view;

import data.DataProvider;
import sections.sectionone.SectionOne;
import sections.sectionzero.SectionZero;
import sections.time.Time;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Viewer {

    private SectionZero sectionZero;
    private SectionOne sectionOne;
    private Time time;
    private final DataProvider dataProvider;

    public Viewer() {
        dataProvider = new DataProvider();;
    }

    private void viewResults() {

        System.out.println("\n----------------------------------------------------------");
        System.out.println("Data pomiaru: " + time.getDate());
        System.out.println("Godzina pomiaru: " + time.getTime());
        System.out.println(sectionZero);
        System.out.println(sectionOne);
        System.out.println("----------------------------------------------------------\n");
    }

    private String inputDate() {
        String year, month, day, hour, minutes;

        year = checkInput("Podaj rok: ", 2000, 2022);
        month = checkInput("Podaj numer miesiąca: ", 1, 12);
        day = checkDayInput(month, year);
        hour = checkInput("Podaj godzinę: ", 0, 23);
        minutes = checkInput("Podaj minuty: ", 0, 59);

        return year + month + day + hour + minutes;
    }

    private String checkInput(String line, int startBound, int endBound) {

        Scanner scanner = new Scanner(System.in);
        String result;

        try {
            System.out.println(line);
            result = scanner.nextLine();
            if (startBound > Integer.parseInt(result) || endBound < Integer.parseInt(result)) {
                throw new Exception();
            }

        } catch (Exception e) {
            System.err.println("Wprowadzono niepoprawne dane");
            return checkInput(line, startBound, endBound);
        }

        if (result.length() == 1)
            result = "0" + result;

        return result;
    }

    private String checkDayInput(String month, String year) {
        String result;
        int startBound = 1;
        int endBound = 0;
        Scanner scanner = new Scanner(System.in);

        switch (month) {
            case "01":
            case "03":
            case "05":
            case "07":
            case "08":
            case "10":
            case "12":
                endBound = 31;
                break;
            case "04":
            case "06":
            case "09":
            case "11":
                endBound = 30;
                break;
            case "02":
                if (Integer.parseInt(year) % 4 == 0)
                    endBound = 29;
                else
                    endBound = 28;
                break;
            default:
                break;
        }

        try {
            System.out.println("Podaj numer dnia: ");
            result = scanner.nextLine();
            if (Integer.parseInt(result) < startBound || Integer.parseInt(result) > endBound)
                throw new Exception();
        } catch (Exception e) {
            System.err.println("Wprowadzono nieprawidłowe dane.");
            return checkDayInput(month, year);
        }

        if (result.length() == 1)
            result = "0" + result;

        return result;
    }

    private boolean checkDates(String beginDate, String endDate) {
        int beginYear = Integer.parseInt(beginDate.substring(0, 4));
        int endYear = Integer.parseInt(endDate.substring(0, 4));
        int beginMonth = Integer.parseInt(beginDate.substring(4, 6));
        int endMonth = Integer.parseInt(endDate.substring(4, 6));
        int beginDay = Integer.parseInt(beginDate.substring(6, 8));
        int endDay = Integer.parseInt(endDate.substring(6, 8));
        int beginHour = Integer.parseInt(beginDate.substring(8, 10));
        int endHour = Integer.parseInt(endDate.substring(8, 10));
        int beginMinutes = Integer.parseInt(beginDate.substring(10));
        int endMinutes = Integer.parseInt(endDate.substring(10));

        if (beginYear > endYear)
            return false;

        if (beginYear == endYear && beginMonth > endMonth)
            return false;

        if (beginYear == endYear && beginMonth == endMonth && beginDay > endDay)
            return false;

        if (beginYear == endYear && beginMonth == endMonth && beginDay == endDay && beginHour > endHour)
            return false;

        if (beginYear == endYear && beginMonth == endMonth && beginDay == endDay && beginHour == endHour && beginMinutes > endMinutes)
            return false;

        return true;
    }


    public void run() {

        List<String> sections;
        String line;
        String beginDate, endDate;

        beginDate = inputDate();
        endDate = inputDate();

        if (checkDates(beginDate, endDate)) {
            try {
                String urlPath = "https://www.ogimet.com/cgi-bin/getsynop?begin=" + beginDate + "&end=" + endDate + "&state=Pol";
                URL url = new URL(urlPath);
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    sections = dataProvider.splitTimeAndSections(line);
                    time = dataProvider.prepareTimeObject(sections.get(0));
                    sectionZero = dataProvider.prepareSectionZeroObject(sections.get(1));
                    sectionOne = dataProvider.prepareSectionOneObject(sections.get(2));
                    viewResults();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Wprowadzono błędne przedziały czasowe.");
        }

        /*
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/datafiles/getsynop.dat"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sections = dataProvider.splitTimeAndSections(line);
                time = dataProvider.prepareTimeObject(sections.get(0));
                sectionZero = dataProvider.prepareSectionZeroObject(sections.get(1));
                sectionOne = dataProvider.prepareSectionOneObject(sections.get(2));
                viewResults();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

}

