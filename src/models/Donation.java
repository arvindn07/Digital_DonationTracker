package models;

public class Donation {
    private String id;
    private String donorId;
    private String ngoName;
    private String type;
    private int quantity;
    private String date;

    public Donation(String donorId, String ngoName, String type, int quantity, String date) {
        this.donorId = donorId;
        this.ngoName = ngoName;
        this.type = type;
        this.quantity = quantity;
        this.date = date;
        // ID will be set by DataHandler
    }

    // Getters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDonorId() { return donorId; }
    public String getNgoName() { return ngoName; }
    public String getType() { return type; }
    public int getQuantity() { return quantity; }
    public String getDate() { return date; }
}