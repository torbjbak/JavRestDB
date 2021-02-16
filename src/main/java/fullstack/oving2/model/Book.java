package fullstack.oving2.model;

import java.util.ArrayList;

public class Book {
    private int id;
    private String name;
    private ArrayList<Author> authors;
    private int year;

    public Book(String name, int year) {
        this.name = name;
        this.year = year;
        this.authors = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Author> getAuthor() {
        return authors;
    }

    public void setAuthor(ArrayList<Author> author) {
        this.authors = author;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }
}
