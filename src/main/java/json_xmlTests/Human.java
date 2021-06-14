package json_xmlTests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.lang.Override;
import java.lang.String;
import java.util.List;

public class Human {

    @SerializedName(value="name", alternate = {"first_name","fName,fname"})
    @JsonProperty("Name")
    private String name;
    @JsonIgnore  // for jackson //transient for gson
    private transient int age;
    private Car car;
    private List<Phones> phones;

    public Human (){}
    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Phones> getPhones() {
        return phones;
    }

    public void setPhones(List<Phones> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                ", phones=" + phones +
                '}';
    }
}
