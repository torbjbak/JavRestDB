package fullstack.oving2.web;

import fullstack.oving2.model.Author;
import fullstack.oving2.model.Book;
import fullstack.oving2.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BookController {
    private final ArrayList<Book> books = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private MyService service;

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

    @RequestMapping("/books/log")
    public Book logMessage() {

        logger.trace("Trace!");
        logger.debug("Debug!");
        logger.info("Info!");
        logger.warn("Warning!");
        logger.error("Error!");

        return this.service.bookMessage();
    }
}
