package json_xmlTests;

public class Phones {
    private String number;
    private String country;

    public Phones() {
    }

    public Phones(String number, String country) {
        this.number = number;
        this.country = country;

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Phones{" +
                "number='" + number + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
