package fullstack.oving2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Objects;

@Entity
public class Book {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int year;

    public Book(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public Book() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Book))
            return false;
        Book employee = (Book) obj;
        return Objects.equals(this.id, employee.id) &&
                Objects.equals(this.name, employee.name) &&
                Objects.equals(this.year, employee.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.year);
    }

    @Override
    public String toString() {
        return "Book ID: "+ id +
                "\nName: "+ name +
                "\nRelease year: "+ year +"\n";
    }
}
