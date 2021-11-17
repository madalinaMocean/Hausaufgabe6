package main.Model;
import java.io.Serializable;

public abstract class Person implements Identifiable<Integer>, Serializable{
    public int Id;
    public String firstName;
    public String lastName;

    public Person(int id, String firstName, String lastName) {
        this.Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
        this.Id = 0;
        this.firstName = "";
        this.lastName = "";
    }

    @Override
    public Integer getId() {
        return Id;
    }
    @Override
    public void setId(Integer id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        String str=Id+" "+firstName+" "+lastName;
        return str;
    }
}
