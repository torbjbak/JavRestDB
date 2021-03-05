package fullstack.oving2.service;

import fullstack.oving2.model.Author;
import fullstack.oving2.repo.AuthorRepo;
import fullstack.oving2.repo.BookRepo;
import fullstack.oving2.web.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepo aRepo;
    private final BookRepo bRepo;


    @Autowired
    public AuthorService(AuthorRepo aRepo, BookRepo bRepo) {
        this.aRepo = aRepo;
        this.bRepo = bRepo;
    }

    public List<Author> getAuthors() {
        return new ArrayList<>(aRepo.findAll());
    }

    public Author getAuthor(Long id) {
        return aRepo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public List<Author> authorSearch(String search) {
        return aRepo.findAll().stream()
                .filter(a -> a.getPersName().toLowerCase().contains(search.toLowerCase())
                        || a.getFamName().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Author addAuthor(Author author) {
        return aRepo.save(author);
    }

    public void deleteAuthor(Long id) {
        Author a = getAuthor(id);

        bRepo.findAll().stream()
                .filter(b -> b.getAuthors().contains(a))
                .forEach(b -> b.getAuthors().remove(a));

        a.setBooks(null);

        aRepo.delete(a);
    }
}
