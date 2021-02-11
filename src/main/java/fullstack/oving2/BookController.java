package fullstack.oving2;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BookController {
    private final ArrayList<Book> books = new ArrayList<>();

    @GetMapping("/books")
    public ArrayList<Book> getBooks() {
        return books;
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        book.setId(books.size());
        books.add(book);
        return book;
    }

    @PutMapping("/books/{id}")
    public Book changeAuthor(@RequestBody Book newData, @PathVariable int id){
        for (Book b : books){
            if (b.getId() == id){
                books.set(books.indexOf(b), newData);
                return newData;
            }
        }
        return null;
    }

    @DeleteMapping("/books/{id}")
    public boolean removeAuthor(@PathVariable int id) {
        for(Book b : books) {
            if(b.getId() == id) {
                books.set(books.indexOf(b), books.get(books.size() - 1));
                books.remove(books.get(books.size() - 1));
                return true;
            }
        }
        return false;
    }
}
