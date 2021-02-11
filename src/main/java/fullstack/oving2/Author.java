package fullstack.oving2;

import java.util.ArrayList;

public class Author {
    private final int id;
    private final String name;
    private final Address address;
    private final ArrayList<Book> books;

    public Author(int id, String name, Address address, ArrayList<Book> books) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
