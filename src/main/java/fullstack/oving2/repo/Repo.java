package fullstack.oving2.repo;

import fullstack.oving2.model.Author;
import org.springframework.stereotype.Repository;

@Repository
public class Repo {

    public Author saySomething() {

        // Do fancy database or DAO calls here

        return new Author();
    }
}
