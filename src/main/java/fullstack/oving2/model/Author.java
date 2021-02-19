package fullstack.oving2.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fullstack.oving2.web.BookSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Author {
    @Id @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    private String persName;
    private String famName;
    @OneToOne(
            cascade = CascadeType.ALL
    )
    private Address address;
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    private Set<Book> books;

    public Author(String persName, String famName, Address address, Set<Book> books) {
        this.persName = persName;
        this.famName = famName;
        this.address = address;
        this.books = books;
    }

    public Author() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersName() {
        return this.persName;
    }

    public void setPersName(String persName) {
        this.persName = persName;
    }

    public String getFamName() {
        return this.famName;
    }

    public void setFamName(String famName) {
        this.famName = famName;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonSerialize(using = BookSerializer.class)
    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (!(obj instanceof Author))
            return false;
        Author employee = (Author) obj;
        return Objects.equals(this.id, employee.id) &&
                Objects.equals(this.persName, employee.persName) &&
                Objects.equals(this.famName, employee.famName) &&
                Objects.equals(this.address, employee.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.persName, this.famName, this.address);
    }

    @Override
    public String toString() {
        return "Author ID: "+ id +
                "\nName: "+ famName +", "+ persName +
                "\nAddress: "+ address +
                "\nBooks: "+ books.stream().map(Book::getName).collect(Collectors.toList());
    }

    public String getName() {
        return persName +" "+ famName;
    }
}
