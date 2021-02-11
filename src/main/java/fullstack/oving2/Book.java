package fullstack.oving2;

public class Book {
    private final int id;
    private final String name;
    private final int year;

    public Book(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }
}
