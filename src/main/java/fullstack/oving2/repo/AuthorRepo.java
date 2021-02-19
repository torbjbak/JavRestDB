package fullstack.oving2.repo;

import fullstack.oving2.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {

}
