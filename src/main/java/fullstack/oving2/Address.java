package fullstack.oving2;

public class Address {
    private final int postCode;
    private final String city;
    private final String streetAdd;

    public Address(int postCode, String city, String streetAdd) {
        this.postCode = postCode;
        this.city = city;
        this.streetAdd = streetAdd;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreetAdd() {
        return streetAdd;
    }

    public String toString() {
        return streetAdd +", "+ postCode +" "+ city;
    }
}
