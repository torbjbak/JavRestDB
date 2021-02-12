package fullstack.oving2.model;

import java.util.ArrayList;


public class Author {
    private int id;
    private String persName;
    private String famName;
    private Address address;
    private ArrayList<Book> books;

    public Author(String persName, String famName, Address address) {
        this.persName = persName;
        this.famName = famName;
        this.address = address;
        this.books = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersName() {
        return persName;
    }

    public void setPersName(String persName) {
        this.persName = persName;
    }

    public String getFamName() {
        return famName;
    }

    public void setFamName(String famName) {
        this.famName = famName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
