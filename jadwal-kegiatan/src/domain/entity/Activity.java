package domain.entity;

public class Activity {
    private final int id;
    private String title;
    private String day;
    private String time;

    public Activity(int id, String title, String day, String time) {
        validateText(title, "Judul kegiatan");
        validateText(day, "Hari kegiatan");
        validateText(time, "Waktu kegiatan");
        this.id = id;
        this.title = title;
        this.day = day;
        this.time = time;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDay() { return day; }
    public String getTime() { return time; }

    public void changeTitle(String title) {
        validateText(title, "Judul kegiatan");
        this.title = title;
    }

    public void changeDay(String day) {
        validateText(day, "Hari kegiatan");
        this.day = day;
    }

    public void changeTime(String time) {
        validateText(time, "Waktu kegiatan");
        this.time = time;
    }

    private static void validateText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong");
        }
    }
}
