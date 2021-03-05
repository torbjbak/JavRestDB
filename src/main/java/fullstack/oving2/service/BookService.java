package fullstack.oving2.service;

import fullstack.oving2.model.Book;
import fullstack.oving2.repo.AuthorRepo;
import fullstack.oving2.repo.BookRepo;
import fullstack.oving2.web.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepo bRepo;
    private final AuthorRepo aRepo;

    @Autowired
    public BookService(BookRepo bRepo, AuthorRepo aRepo) {
        this.bRepo = bRepo;
        this.aRepo = aRepo;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(bRepo.findAll());
    }

    public Book getBook(Long id) {
        return bRepo.findById(id).orElseThrow(
                () -> new BookNotFoundException(id));
    }

    public List<Book> bookSearch(String search) {
        return bRepo.findAll().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Book addBook(Book book) {
        return bRepo.save(book);
    }

    public void deleteBook(Long id) {
        Book b = getBook(id);

        aRepo.findAll().stream()
                .filter(a -> a.getBooks().contains(b))
                .forEach(a -> a.getBooks().remove(b));

        b.setAuthors(null);

        bRepo.delete(b);
    }
}
