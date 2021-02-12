package fullstack.oving2.repo;

import fullstack.oving2.model.Address;
import fullstack.oving2.model.Author;
import fullstack.oving2.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class Repo {

    public Author authorDAO() {
        // Do fancy database or DAO calls here
        return new Author("Jens Person", new Address(7070, "Trondheim", "Gatenavn 1"));
    }

    public Book bookDAO() {

        return new Book("Godboka", 1993);
    }
}
