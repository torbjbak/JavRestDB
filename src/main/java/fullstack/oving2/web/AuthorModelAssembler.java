package fullstack.oving2.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import fullstack.oving2.model.Author;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
class AuthorModelAssembler implements RepresentationModelAssembler<Author, EntityModel<Author>> {

    @Override
    public EntityModel<Author> toModel(Author a) {

        return EntityModel.of(a, //
                WebMvcLinkBuilder.linkTo(methodOn(AuthorController.class).one(a.getId())).withSelfRel(),
                linkTo(methodOn(AuthorController.class).all()).withRel("authors"));
    }
}
