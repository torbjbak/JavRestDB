package fullstack.oving2.service;

import fullstack.oving2.model.Author;
import fullstack.oving2.model.AuthorBook;
import fullstack.oving2.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class MyService {
    private final ArrayList<Author> authors = new ArrayList<>();
    private final ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<AuthorBook> abConns = new ArrayList<>();
    private int bookIDcount = 0;
    private int authorIDcount = 0;


    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<AuthorBook> getAbConns() {
        return abConns;
    }

    public ArrayList<Book> getBooksAuthor(int id) {
        ArrayList<Book> ret = new ArrayList<>();
        for (AuthorBook ab : abConns) {
            if (ab.getAuthorID() == id) {
                for (Book b : books) {
                    if (b.getId() == ab.getBookID()) {
                        ret.add(b);
                        break;
                    }
                }
            }
        }
        return ret;
    }

    public ArrayList<Author> authorSearch(String search) {
        ArrayList<Author> result = new ArrayList<>();
        for (Author a : authors) {
            if (a.getPersName().toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT)) ||
                    a.getFamName().toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT))) {
                result.add(a);
            }
        }
        return result;
    }

    public Author addAuthor(Author author) {
        //author.setId(authorIDcount);
        authorIDcount++;
        authors.add(author);
        return author;
    }

    public boolean deleteAuthor(int id) {
        boolean removed = false;
        for(Author a : authors) {
            if(a.getId() == id) {
                authors.set(authors.indexOf(a), authors.get(authors.size() - 1));
                authors.remove(authors.get(authors.size() - 1));
                removed = true;
            }
        }
        for(AuthorBook ab : abConns) {
            if(ab.getAuthorID() == id) {
                abConns.set(abConns.indexOf(ab), abConns.get(abConns.size() - 1));
                abConns.remove(abConns.get(abConns.size() - 1));
            }
        }
        return removed;
    }

    public ArrayList<Author> getAuthorsBook(int id) {
        ArrayList<Author> ret = new ArrayList<>();
        for (AuthorBook ab : abConns) {
            if (ab.getBookID() == id) {
                for (Author a : authors) {
                    if (a.getId() == ab.getAuthorID()) {
                        ret.add(a);
                        break;
                    }
                }
            }
        }
        return ret;
    }

    public ArrayList<Book> bookSearch(String search) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getName().toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT))) {
                result.add(b);
            }
        }
        return result;
    }

    public Book addBook(@RequestBody Book book) {
        //book.setId(bookIDcount);
        bookIDcount++;
        books.add(book);
        return book;
    }

    public boolean deleteBook(int id) {
        boolean removed = false;
        for(Book b : books) {
            if(b.getId() == id) {
                books.set(books.indexOf(b), books.get(books.size() - 1));
                books.remove(books.get(books.size() - 1));
                removed = true;
            }
        }
        for(AuthorBook ab : abConns) {
            if(ab.getBookID() == id) {
                abConns.set(abConns.indexOf(ab), abConns.get(abConns.size() - 1));
                abConns.remove(abConns.get(abConns.size() - 1));
            }
        }
        return removed;
    }

    public ArrayList<String> searchAll(String search) {
        ArrayList<String> result = new ArrayList<>();
        for (Author a : authorSearch(search)) {
            result.add(a.toString());
        }
        for (Book b : bookSearch(search)) {
            result.add(b.toString());
        }
        return result;
    }

    public AuthorBook addConnection(int authorID, int bookID) {
        AuthorBook ab = new AuthorBook(authorID, bookID);
        abConns.add(ab);
        return ab;
    }
}