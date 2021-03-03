package fullstack.oving2.service;

import fullstack.oving2.model.Author;
import fullstack.oving2.repo.AddressRepo;
import fullstack.oving2.repo.AuthorRepo;
import fullstack.oving2.repo.BookRepo;
import fullstack.oving2.web.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepo aRepo;
    private final BookRepo bRepo;
    private final AddressRepo addRepo;


    @Autowired
    public AuthorService(AuthorRepo aRepo, BookRepo bRepo, AddressRepo addRepo) {
        this.aRepo = aRepo;
        this.bRepo = bRepo;
        this.addRepo = addRepo;
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
        Author newAuthor = new Author(author.getPersName(), author.getFamName(),
                addRepo.findAll().stream()
                        .filter(a -> a.equals(author.getAddress()))
                        .findFirst().orElse(null),
                bRepo.findAll().stream()
                        .filter(b1 -> author.getBooks().stream().anyMatch(b2 -> b2.equals(b1)))
                        .collect(Collectors.toSet()));

        bRepo.findAll().stream()
                .filter(b1 -> author.getBooks().stream()
                        .anyMatch(b2 -> b2.equals(b1)))
                .collect(Collectors.toSet())
                .forEach(b -> b.getAuthors().add(newAuthor));

        return aRepo.save(newAuthor);
    }

    public void deleteAuthor(@PathVariable Long id) {
        Author author = aRepo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        bRepo.findAll().stream()
                .filter(b -> b.getAuthors().contains(author))
                .forEach(b -> b.getAuthors().remove(author));

        author.setBooks(null);

        aRepo.delete(author);
    }
}
