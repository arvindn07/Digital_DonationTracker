package models;

public class Donor {
    private String id;
    private String name;
    private String contact;
    private String email;
    private String address;

    public Donor(String name, String contact, String email, String address) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.address = address;
        // ID will be set by DataHandler
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
}