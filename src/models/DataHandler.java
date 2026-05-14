package models;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DataHandler {
    private static final String DONORS_FILE = "donors.txt";
    private static final String DONATIONS_FILE = "donations.txt";

    // Save Donor
    public static void saveDonor(Donor donor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DONORS_FILE, true))) {
            String id = generateNextId(DONORS_FILE, "D");  // Generate ID starting with 'D'
            donor.setId(id);
            writer.write(id + "," + donor.getName() + "," + donor.getContact() + "," +
                         donor.getEmail() + "," + donor.getAddress());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving donor: " + e.getMessage());
        }
    }

    // Save Donation
    public static void saveDonation(Donation donation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DONATIONS_FILE, true))) {
            String id = generateNextId(DONATIONS_FILE, "X");  // Generate ID starting with 'X'
            donation.setId(id);
            writer.write(id + "," + donation.getDonorId() + "," + donation.getNgoName() + "," +
                         donation.getType() + "," + donation.getQuantity() + "," + donation.getDate());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving donation: " + e.getMessage());
        }
    }

    // Read all donors
    public static List<Donor> getAllDonors() {
        List<Donor> donors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DONORS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Donor donor = new Donor(parts[1], parts[2], parts[3], parts[4]);
                    donor.setId(parts[0]);
                    donors.add(donor);
                }
            }
        } catch (IOException e) {
            // File may not exist yet; return empty list
            //System.err.println("Error reading donors: " + e.getMessage());
        }
        return donors;
    }

    // Read all donations
    public static List<Donation> getAllDonations() {
        List<Donation> donations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DONATIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Donation donation = new Donation(parts[1], parts[2], parts[3],
                                                      Integer.parseInt(parts[4]), parts[5]);
                    donation.setId(parts[0]);
                    donations.add(donation);
                }
            }
        } catch (IOException e) {
            // File doesn't exist or empty, return empty list
            //System.err.println("Error reading donations: " + e.getMessage());
        }
        return donations;
    }

    // Generate next ID
    private static String generateNextId(String fileName, String prefix) {
        int nextId = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String lastLine = null;
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
            if (lastLine != null) {
                String[] parts = lastLine.split(",");
                String lastId = parts[0];  // e.g., D5
                nextId = Integer.parseInt(lastId.substring(1)) + 1;  // Extract number and increment
            }
        } catch (IOException e) {
            // File doesn't exist or empty, start from 1
        }
        return prefix + nextId;
    }

    // Reports methods
    public static int getTotalDonors() {
        return getAllDonors().size();
    }

    public static int getTotalDonationQuantity() {
        List<Donation> donations = getAllDonations();
        int total = 0;
        for (Donation d : donations) {
            total += d.getQuantity();
        }
        return total;
    }

    // New: total donation quantity for a specific month ("YYYY-MM"). If month is null or "All", returns overall total.
    public static int getTotalDonationQuantityForMonth(String month) {
        if (month == null || month.equalsIgnoreCase("All")) {
            return getTotalDonationQuantity();
        }
        List<Donation> donations = getAllDonations();
        int total = 0;
        for (Donation d : donations) {
            String date = d.getDate(); // expected "YYYY-MM-DD"
            if (date != null && date.length() >= 7) {
                String m = date.substring(0, 7); // "YYYY-MM"
                if (m.equals(month)) {
                    total += d.getQuantity();
                }
            }
        }
        return total;
    }

    // Existing: full breakdown by type (no month filter)
    public static Map<String, Integer> getDonationBreakdownByType() {
        List<Donation> donations = getAllDonations();
        Map<String, Integer> breakdown = new HashMap<>();
        for (Donation d : donations) {
            breakdown.put(d.getType(), breakdown.getOrDefault(d.getType(), 0) + d.getQuantity());
        }
        return breakdown;
    }

    // New: breakdown by type for a given month ("YYYY-MM"). If month is null or "All", returns overall breakdown.
    public static Map<String, Integer> getDonationBreakdownByTypeForMonth(String month) {
        Map<String, Integer> breakdown = new HashMap<>();
        List<Donation> donations = getAllDonations();

        if (month == null || month.equalsIgnoreCase("All")) {
            return getDonationBreakdownByType();
        }

        for (Donation d : donations) {
            String date = d.getDate();
            if (date != null && date.length() >= 7) {
                String m = date.substring(0, 7); // "YYYY-MM"
                if (m.equals(month)) {
                    breakdown.put(d.getType(), breakdown.getOrDefault(d.getType(), 0) + d.getQuantity());
                }
            }
        }
        return breakdown;
    }

    // New: return a sorted list of months (format "YYYY-MM") for which there are donations
    public static List<String> getAllMonthsWithDonations() {
        List<Donation> donations = getAllDonations();
        Set<String> months = new TreeSet<>(); // TreeSet to keep sorted order ascending
        for (Donation d : donations) {
            String date = d.getDate();
            if (date != null && date.length() >= 7) {
                months.add(date.substring(0, 7)); // "YYYY-MM"
            }
        }
        return new ArrayList<>(months);
    }
}
