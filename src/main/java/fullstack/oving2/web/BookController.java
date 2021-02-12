package fullstack.oving2.web;

import fullstack.oving2.model.Book;
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
    public Book changeBook(@RequestBody Book newData, @PathVariable int id){
        for (Book b : books){
            if (b.getId() == id){
                books.set(books.indexOf(b), newData);
                return newData;
            }
        }
        return null;
    }

    @DeleteMapping("/books/{id}")
    public boolean removeBook(@PathVariable int id) {
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
