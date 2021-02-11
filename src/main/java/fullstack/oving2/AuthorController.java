package fullstack.oving2;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AuthorController {
    private final ArrayList<Author> authors = new ArrayList<>();

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/authors")
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    @PostMapping("/authors")
    public Author addAuthor(@RequestBody Author author) {
        author.setId(authors.size());
        authors.add(author);
        return author;
    }

    @PutMapping("/users/{id}")
    public Author changeAuthor(@RequestBody Author newData, @PathVariable int id){
        for (Author a : authors){
            if (a.getId() == id){
                authors.set(authors.indexOf(a), newData);
                return newData;
            }
        }
        return null;
    }

    @DeleteMapping("/authors/{id}")
    public boolean removeAuthor(@PathVariable int id) {
        for(Author a : authors) {
            if(a.getId() == id) {
                authors.set(authors.indexOf(a), authors.get(authors.size() - 1));
                authors.remove(authors.get(authors.size() - 1));
                return true;
            }
        }
        return false;
    }
}
