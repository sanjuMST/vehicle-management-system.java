import java.util.ArrayList;
import java.util.Scanner;
class Vehicle {
    // Fields to store vehicle information
    private int vehicleId;
    private String vehicleNumber;
    private String vehicleName;
    private String vehicleType;
    private String ownerName;

    // Constructor to initialize a new Vehicle object
    public Vehicle(int vehicleId, String vehicleNumber, String vehicleName,
                   String vehicleType, String ownerName) {
        this.vehicleId = vehicleId;
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.vehicleType = vehicleType;
        this.ownerName = ownerName;
    }

    // Getter methods
    public int getVehicleId() {
        return vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    // Setter methods (used while updating vehicle details)
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    // Method to display vehicle details in a readable format
    public void display() {
        System.out.println("-----------------------------------");
        System.out.println("Vehicle ID     : " + vehicleId);
        System.out.println("Vehicle Number : " + vehicleNumber);
        System.out.println("Vehicle Name   : " + vehicleName);
        System.out.println("Vehicle Type   : " + vehicleType);
        System.out.println("Owner Name     : " + ownerName);
        System.out.println("-----------------------------------");
    }
}
// VehicleManagementSystem class - contains main method
// and all menu-driven operations
class VehicleManagementSystem {
    // ArrayList to store all vehicle records in memory
    private static ArrayList<Vehicle> vehicleList = new ArrayList<>();
    // Scanner object for taking user input
    private static Scanner sc = new Scanner(System.in);
    // Counter used to generate unique vehicle IDs
    private static int idCounter = 1;
    public static void main(String[] args) {
        int choice;

        printWelcomeMessage();

        // Menu loop - keeps running until user chooses to exit
        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    viewVehicles();
                    break;
                case 3:
                    searchVehicle();
                    break;
                case 4:
                    updateVehicle();
                    break;
                case 5:
                    deleteVehicle();
                    break;
                case 6:
                    countVehicles();
                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select between 1 and 7.");
            }
        } while (choice != 7);

        sc.close();
    }

    // Prints a welcome banner when the program starts
    private static void printWelcomeMessage() {
        System.out.println("=======================================");
        System.out.println("  WELCOME TO VEHICLE MANAGEMENT SYSTEM  ");
        System.out.println("=======================================");
        System.out.println("This program lets you add, view, search,");
        System.out.println("update, delete, and count vehicle records.");
    }

    // Prints the main menu options
    private static void printMenu() {
        System.out.println("\n===== VEHICLE MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Vehicle");
        System.out.println("2. View All Vehicles");
        System.out.println("3. Search Vehicle by Vehicle Number");
        System.out.println("4. Update Vehicle Details");
        System.out.println("5. Delete Vehicle");
        System.out.println("6. Display Total Number of Vehicles");
        System.out.println("7. Exit");
        System.out.println("======================================");
    }

    // Adds a new vehicle to the ArrayList
    private static void addVehicle() {
        System.out.println("\n--- Add New Vehicle ---");

        String number = readNonEmptyString("Enter Vehicle Number: ");

        // Basic validation: check if vehicle number already exists
        if (findVehicleByNumber(number) != null) {
            System.out.println("Error: A vehicle with this number already exists!");
            return;
        }

        String name = readNonEmptyString("Enter Vehicle Name: ");
        String type = readVehicleType();
        String owner = readNonEmptyString("Enter Owner Name: ");

        // Create a new Vehicle object and add it to the list
        Vehicle vehicle = new Vehicle(idCounter, number, name, type, owner);
        vehicleList.add(vehicle);

        System.out.println("Vehicle added successfully with ID: " + idCounter);
        idCounter++; // Increment ID counter for the next vehicle
    }

    // Displays details of all vehicles currently stored
    private static void viewVehicles() {
        System.out.println("\n--- All Vehicles ---");

        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles found in the system.");
            return;
        }

        // Loop through the list and display each vehicle
        for (Vehicle v : vehicleList) {
            v.display();
        }
    }

    // Searches for a vehicle using its vehicle number
    private static void searchVehicle() {
        System.out.println("\n--- Search Vehicle ---");
        String number = readNonEmptyString("Enter Vehicle Number to search: ");

        Vehicle found = findVehicleByNumber(number);

        if (found != null) {
            System.out.println("Vehicle found:");
            found.display();
        } else {
            System.out.println("Error: No vehicle found with number " + number);
        }
    }

    // Updates the details of an existing vehicle
    private static void updateVehicle() {
        System.out.println("\n--- Update Vehicle Details ---");
        String number = readNonEmptyString("Enter Vehicle Number to update: ");

        Vehicle vehicle = findVehicleByNumber(number);

        if (vehicle == null) {
            System.out.println("Error: No vehicle found with number " + number);
            return;
        }

        System.out.println("Current details:");
        vehicle.display();

        // Ask for new details; allow leaving fields unchanged
        String name = readNonEmptyString("Enter new Vehicle Name: ");
        String type = readVehicleType();
        String owner = readNonEmptyString("Enter new Owner Name: ");

        vehicle.setVehicleName(name);
        vehicle.setVehicleType(type);
        vehicle.setOwnerName(owner);

        System.out.println("Vehicle details updated successfully!");
    }

    // Deletes a vehicle record based on vehicle number
    private static void deleteVehicle() {
        System.out.println("\n--- Delete Vehicle ---");
        String number = readNonEmptyString("Enter Vehicle Number to delete: ");

        Vehicle vehicle = findVehicleByNumber(number);

        if (vehicle == null) {
            System.out.println("Error: No vehicle found with number " + number);
            return;
        }

        vehicleList.remove(vehicle);
        System.out.println("Vehicle deleted successfully!");
    }

    // Displays the total number of vehicles currently stored
    private static void countVehicles() {
        System.out.println("\n--- Total Vehicles ---");
        System.out.println("Total number of vehicles: " + vehicleList.size());
    }

    // Helper method: searches the ArrayList for a vehicle by its number
    // Returns the Vehicle object if found, otherwise returns null
    private static Vehicle findVehicleByNumber(String number) {
        for (Vehicle v : vehicleList) {
            if (v.getVehicleNumber().equalsIgnoreCase(number)) {
                return v;
            }
        }
        return null;
    }

    // Helper method: reads an integer safely from the user
    private static int readInt(String prompt) {
        int value = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        return value;
    }

    // Helper method: reads and validates the vehicle type
    // Accepts only a fixed set of known types (case-insensitive)
    private static String readVehicleType() {
        String[] validTypes = {"Car", "Bike", "Bus", "Truck", "Van"};
        String input;

        while (true) {
            System.out.print("Enter Vehicle Type (Car, Bike, Bus, Truck, Van): ");
            input = sc.nextLine().trim();

            for (String type : validTypes) {
                if (type.equalsIgnoreCase(input)) {
                    return type; // Return the properly formatted type
                }
            }
            System.out.println("Invalid type! Please choose from Car, Bike, Bus, Truck, Van.");
        }
    }

    // Helper method: reads a non-empty string from the user
    private static String readNonEmptyString(String prompt) {
        String input;

        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                break;
            }
            System.out.println("Input cannot be empty! Please try again.");
        }
        return input;
    }
}