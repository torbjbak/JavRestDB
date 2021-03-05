package fullstack.oving2.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fullstack.oving2.web.AuthorSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Book {
    @Id @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    private String title;
    private int year;
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    private Set<Author> authors;

    public Book(String title, int year, Set<Author> authors) {
        this.title = title;
        this.year = year;
        this.authors = authors;
    }

    public Book() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @JsonSerialize(using = AuthorSerializer.class)
    public Set<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Book))
            return false;
        Book employee = (Book) obj;
        return Objects.equals(this.id, employee.id) &&
                Objects.equals(this.title, employee.title) &&
                Objects.equals(this.year, employee.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.year);
    }

    @Override
    public String toString() {
        return "Book ID: "+ id +
                "\nTitle: "+ title +
                "\nRelease year: "+ year +
                "\nAuthor(s): "+ authors.stream().map(Author::getName).collect(Collectors.toList());
    }
}
