package fullstack.oving2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Author {
    @Id @GeneratedValue
    private Long id;
    private String persName;
    private String famName;
    @OneToOne
    private Address address;

    public Author(String persName, String famName, Address address) {
        this.persName = persName;
        this.famName = famName;
        this.address = address;
    }

    public Author() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersName() {
        return persName;
    }

    public void setPersName(String persName) {
        this.persName = persName;
    }

    public String getFamName() {
        return famName;
    }

    public void setFamName(String famName) {
        this.famName = famName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
                "\nAddress: "+ address +"\n";
    }
}
