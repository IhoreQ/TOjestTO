package sections.time;

public class Time {
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minutes;

    public Time(String year,String month,String day,String hour,String minutes) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
    }

    public String getTime() {
        return String.format("%s:%s", hour, minutes);
    }

    public String getDate() {
        return String.format("%s-%s-%s", day, month, year);
    }
}
