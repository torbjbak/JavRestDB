package fullstack.oving2;

import fullstack.oving2.model.Address;
import fullstack.oving2.model.Author;
import fullstack.oving2.model.Book;
import fullstack.oving2.repo.AddressRepo;
import fullstack.oving2.repo.AuthorRepo;
import fullstack.oving2.repo.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AuthorRepo authorRepo, BookRepo bookRepo, AddressRepo addressRepo) {

        return args -> {
            log.info("Preloading " + authorRepo.save(new Author("Ola", "Normann", addressRepo.save(new Address(7070, "Trondheim", "Gateveien 12")))));
            log.info("Preloading " + bookRepo.save(new Book("Godboka", 1993)));
        };
    }
}
