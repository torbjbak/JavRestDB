package fullstack.oving2.service;

import fullstack.oving2.model.Author;
import fullstack.oving2.model.Book;
import fullstack.oving2.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Value("${name:World}")
    private String name;

    // DI av repo her
    @Autowired
    private Repo repo;

    public Author authorMessage() {
        return repo.authorDAO();
    }

    public Book bookMessage() {
        return repo.bookDAO();
    }
}