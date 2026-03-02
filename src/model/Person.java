package model;

public abstract class Person {

    private String name;
    private String email;
    private String id;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Person(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return name + " | " + email;
    }
}