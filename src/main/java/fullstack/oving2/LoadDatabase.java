package fullstack.oving2;

import fullstack.oving2.model.Address;
import fullstack.oving2.model.Author;
import fullstack.oving2.model.Book;
import fullstack.oving2.repo.AuthorRepo;
import fullstack.oving2.repo.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AuthorRepo authorRepo, BookRepo bookRepo) {

        return args -> {

           /* Address ad1 = new Address(7070, "Trondheim", "Gateveien 12");
            Address ad2 = new Address(2020, "Oslo", "Veigata 21");
            Address ad3 = new Address(1001, "Jerusalem", "Righteous Path");
            Address ad4 = new Address(6666, "Seoul", "Demon Street 1");

            Author au1 = new Author("Ola", "Normann", ad1, null);
            Author au2 = new Author("Kari", "Normann", ad2, null);
            Author au3 = new Author("Jesus", "Christ", ad3, null);
            Author au4 = new Author("Sang-Hyouk", "Lee", ad4, null);

            Book b1 = new Book("Godboka", 1993, null);
            Book b2 = new Book("Din Bok", 1995, null);
            Book b3 = new Book("A Book to Remember", 1945, null);
            Book b4 = new Book("The Good Book", 28, null);
            Book b5 = new Book("Remember The Name", 2005, null);

            au1.setBooks(Set.of(b1, b2));
            au2.setBooks(Set.of(b2));
            au3.setBooks(Set.of(b3, b4));
            au4.setBooks(Set.of(b3, b5));

            b1.setAuthors(Set.of(au1));
            b2.setAuthors(Set.of(au1, au2));
            b3.setAuthors(Set.of(au3, au4));
            b4.setAuthors(Set.of(au3));
            b5.setAuthors(Set.of(au4));

            log.info("Preloading " + authorRepo.save(au1));
            log.info("Preloading " + authorRepo.save(au2));
            log.info("Preloading " + authorRepo.save(au3));
            log.info("Preloading " + authorRepo.save(au4));

            log.info("Preloading " + bookRepo.save(b1));
            log.info("Preloading " + bookRepo.save(b2));
            log.info("Preloading " + bookRepo.save(b3));
            log.info("Preloading " + bookRepo.save(b4));
            log.info("Preloading " + bookRepo.save(b5));*/
        };
    }
}