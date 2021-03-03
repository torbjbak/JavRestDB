package fullstack.oving2.web;

import fullstack.oving2.model.Author;
import fullstack.oving2.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService service;
    private final AuthorModelAssembler assembler;
    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    public AuthorController(AuthorService service, AuthorModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Author>> all() {
        logMessage("Returning all authors.");

        return CollectionModel.of(service
                        .getAuthors()
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(AuthorController.class).all())
                        .withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Author> one(@PathVariable Long id) {
        logMessage("Lookup for author with ID: "+ id);

        return assembler.toModel(service.getAuthor(id));
    }

    @GetMapping("/search/{search}")
    public CollectionModel<EntityModel<Author>> authorSearch(@PathVariable String search) {
        logMessage("Author search for: '"+ search +"'");

        return CollectionModel.of(service
                        .authorSearch(search)
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(AuthorController.class).all())
                        .withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        logMessage("Adding author: "+ author.getFamName() +", "+ author.getPersName());

        EntityModel<Author> em = assembler.toModel(service.addAuthor(author));

        return ResponseEntity
                .created(em.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(em);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        logMessage("Attempt to delete author with ID: "+ id);

        service.deleteAuthor(id);

        return ResponseEntity.noContent().build();
    }

    public void logMessage(String log) {
        logger.info("Info: "+ log);
    }
}