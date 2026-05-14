# 🎁 Digital Donation Tracker

A **GUI-based Java Swing application** for NGOs and donation centers to efficiently manage donors, record donations, and generate organized reports — all without spreadsheets or paper registers.

---

## 📋 Problem Statement

Managing donations manually using registers, spreadsheets, or paper records is time-consuming, inefficient, and prone to errors. NGOs and donation centers often face difficulties in:

- Maintaining donor information
- Tracking different types of donations
- Generating reports for transparency
- Organizing records for future reference

**Digital Donation Tracker** solves these problems with a clean, easy-to-use desktop application.

---

## ✨ Features

| Feature | Description |
|---|---|
| 👤 Add Donors | Store donor name, contact, email, and address |
| 💰 Add Donations | Record donations with donor ID, NGO name, type, quantity, and date |
| 📋 View Donors | Browse all donors in a searchable table |
| 📦 View Donations | Browse all donations with real-time search and filter |
| 📊 Reports | Category-wise and month-wise donation summaries |
| 💾 Export Reports | Save reports as `.txt` files for documentation |

---

## 🛠️ Tech Stack

- **Language:** Java (JDK 8+)
- **UI Framework:** Java Swing
- **Storage:** Plain text files (`donors.txt`, `donations.txt`)
- **IDE:** VS Code (with Java Extension Pack)

---

## 📁 Project Structure

```
DigitalDonationTracker/
├── src/
│   ├── Main.java                  # Entry point
│   ├── gui/
│   │   ├── HomeScreen.java        # Main navigation window
│   │   ├── AddDonorForm.java      # Form to add a new donor
│   │   ├── AddDonationForm.java   # Form to record a donation
│   │   ├── ViewDonors.java        # Table view of all donors
│   │   ├── ViewDonations.java     # Table view of all donations
│   │   └── Reports.java           # Report generation + export
│   └── models/
│       ├── Donor.java             # Donor data model
│       ├── Donation.java          # Donation data model
│       └── DataHandler.java       # File I/O and data logic
├── .vscode/
│   └── settings.json
├── .gitignore
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- Java JDK 8 or higher installed
- VS Code with the **Extension Pack for Java** (recommended), or any Java IDE

### Running the App

**Option 1 — VS Code**
1. Open the `DigitalDonationTracker` folder in VS Code
2. Open `src/Main.java`
3. Click **Run** (the play button above `main`)

**Option 2 — Command Line**
```bash
# From the project root, compile all files
javac -d out src/Main.java src/gui/*.java src/models/*.java

# Run the app
java -cp out Main
```

> Data files (`donors.txt`, `donations.txt`) are created automatically in the working directory the first time you save a record.

---

## 📸 Screenshots

> *(Add screenshots of the app here after running it)*

| Home Screen | Add Donor | Reports |
|---|---|---|
| *(screenshot)* | *(screenshot)* | *(screenshot)* |

---

## 🧩 How It Works

1. **Donors** are stored in `donors.txt`, one per line, with auto-generated IDs like `D1`, `D2`, etc.
2. **Donations** are stored in `donations.txt`, with IDs like `X1`, `X2`, etc.
3. **DataHandler** handles all file reads/writes, ID generation, and report calculations.
4. **Reports** support filtering by month and breakdown by donation type (Money, Clothes, Food, Others).

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you'd like to change.

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
