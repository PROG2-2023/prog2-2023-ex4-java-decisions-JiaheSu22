package prog2.exercise4.flight.booking.system;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class FlightBooking {
    private final String flightCompany = "Flights-of-Fancy";//This must NOT be entered by the Passenger
    private String flightID;//This must NOT be entered by the Passenger
    private String passengerFullName;

    public enum BookingClass {FIRST, BUSINESS, ECONOMY}

    public enum TripSource {NANJING, BEIJING, SHANGHAI, OULU, HELSINKI, PARIS}

    public enum TripDestination {NANJING, BEIJING, SHANGHAI, OULU, HELSINKI, PARIS}

    public enum TripType {ONEWAY, RETURN}

    private TripType tripType;
    private TripSource tripSource;
    private TripDestination tripDestination;
    private BookingClass bookingClass;

    private enum sourceAirport {Nanjing_Lukou_International_Airport, Beijing_Capital_International_Airport, Shanghai_Pudong_International_Airport, Oulu_Airport, Helsinki_Airport, Paris_Charles_de_Gaulle_Airport}

    private enum destinationAirport {Nanjing_Lukou_International_Airport, Beijing_Capital_International_Airport, Shanghai_Pudong_International_Airport, Oulu_Airport, Helsinki_Airport, Paris_Charles_de_Gaulle_Airport}

    private LocalDate departureDate;
    private LocalDate returnDate;
    private int childPassengers;
    private int adultPassengers;
    private int totalPassengers;
    private double departingTicketPrice; //This must NOT be entered by the Passenger
    private double returnTicketPrice; //This must NOT be entered by the Passenger
    private double totalTicketPrice; //This must NOT be entered by the Passenger
    private String ticketNumber = generateTicketNumber();/*This must NOT be entered by the Passenger*/

    private String generateFlightID() {
        int randomInt = (int) (Math.random() * 9999) + 1;
        return "FOF" + String.format("%04d", randomInt) + "IN";
    }

    private String generateTicketNumber() {
        String ticketNum = "11";
        if (tripType == TripType.RETURN) {
            ticketNum = "22";
        }
        if (bookingClass == BookingClass.FIRST) {
            ticketNum += "F";
        } else if (bookingClass == BookingClass.BUSINESS) {
            ticketNum += "B";
        } else if (bookingClass == BookingClass.ECONOMY) {
            ticketNum += "E";
        }
        ticketNum += UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        if (tripSource == null || tripDestination == null) {
            ticketNum += "DOM";
        } else {
            int temp = tripSource.toString().length() + tripDestination.toString().length();
            if (temp == 14 || temp == 12 || temp == 15) {
                ticketNum += "DOM";
            } else ticketNum += "INT";
        }
        return ticketNum;
    }

    public FlightBooking(String name, LocalDate sdepart, LocalDate sreturn, int childNum, int adultNum) {
        this.passengerFullName = name;
        this.departureDate = sdepart;
        this.returnDate = sreturn;
        this.childPassengers = childNum;
        this.adultPassengers = adultNum;
        System.out.println();
    }

    public String toString() {
        return "Dear " + passengerFullName + ". Thank you for booking your flight with " + flightCompany + "." + "\n"
                + "Following are the details of your booking and the trip:" + "\n"
                + "Ticket Number: " + ticketNumber + "\n"
                + "From " + tripSource + " to " + tripDestination + "\n"
                + "Date of departure: " + departureDate + "\n"
                + "Date of return: " + returnDate + "\n"
                + "Total passengers: " + getTotalPassengers() + "\n"
                + "Total ticket price in Euros: " + getTotalTicketPrice();
    }

    public void setTripDestination(TripDestination tripDestination) {
        this.tripDestination = tripDestination;
    }

    public void setBookingClass(String inputChoice) {
        int choice = Integer.parseInt(inputChoice);
        this.bookingClass = BookingClass.values()[choice - 1];
    }

    public BookingClass getBookingClass() {
        return this.bookingClass;
    }

    public TripSource getTripSource() {
        return tripSource;
    }

    public void setTripSource(String tripSourceChoice) {
        int choice = Integer.parseInt(tripSourceChoice);
        switch (choice) {
            case 1:
                this.tripSource = TripSource.NANJING;
                break;
            case 2:
                this.tripSource = TripSource.BEIJING;
                break;
            case 3:
                this.tripSource = TripSource.OULU;
                break;
            case 4:
                this.tripSource = TripSource.HELSINKI;
                break;
            default:
                System.out.println("Invalid trip source choice: " + tripSourceChoice);
        }
    }

    public void setTripDestination(String sourceInput, String destinationInput) {
        int sourceChoice = Integer.parseInt(sourceInput);
        int destinationChoice = Integer.parseInt(destinationInput);
        TripDestination[] destinations = TripDestination.values();
        this.tripDestination = destinations[destinationChoice - 1];
    }

    public TripDestination getTripDestination() {
        return this.tripDestination;
    }


    public void setTripType(String inputChoice) {
        int choice = Integer.parseInt(inputChoice);
        this.tripType = choice == 1 ? TripType.ONEWAY : TripType.RETURN;
    }

    public TripType getTripType() {
        return this.tripType;
    }


    public FlightBooking() {

    }

    public void displayConfirmationMessage() {
        // Format and display confirmation message
        System.out.println("Dear " + passengerFullName + ". Thank you for booking your flight with " + flightCompany + ".");
        System.out.println("Following are the details of your booking and the trip:");
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("From " + tripSource + " to " + tripDestination);
        System.out.println("Date of departure: " + departureDate);
        System.out.println("Date of return: " + returnDate);
        System.out.println("Total passengers: " + getTotalPassengers());
        System.out.println("Total ticket price in Euros: " + getTotalTicketPrice());
        flightID = generateFlightID();
    }

    public String getFlightID() {
        return flightID;
    }


    public int getChildPassengers() {
        return childPassengers;
    }

    public int getAdultPassengers() {
        return adultPassengers;
    }

    public String getFlightCompany() {
        return flightCompany;
    }

    public String getPassengerFullName() {
        return passengerFullName;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
        if (returnDate != null && departureDate.plusDays(2).isAfter(returnDate)) {
            returnDate = departureDate.plusDays(2);
            System.out.println("Return date adjusted to " + returnDate);
        }
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        if (departureDate != null && departureDate.plusDays(2).isAfter(returnDate)) {
            this.returnDate = departureDate.plusDays(2);
        } else {
            this.returnDate = returnDate;
        }
    }

    public int getTotalPassengers() {
        totalPassengers = childPassengers + adultPassengers;
        return totalPassengers;
    }


    public double getTotalTicketPrice() {
        totalTicketPrice = departingTicketPrice + returnTicketPrice;
        return totalTicketPrice;
    }

    public void setTotalTicketPrice() {
        this.totalTicketPrice = 0;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public int setTotalPassengers(int num1, int num2) {
        return num1 + num2;
    }

    public void setPassengerFullName(String passengerFullName) {
        this.passengerFullName = passengerFullName;
    }


    public void setChildPassengers(int childPassengers) {
        this.childPassengers = childPassengers;
    }

    public void setAdultPassengers(int adultPassengers) {
        this.adultPassengers = adultPassengers;
    }

    public void setDepartingTicketPrice(int childNum, int adultNum) {
        if (tripDestination.toString() == "BEIJING" && tripSource.toString() == "NANJING") {
            this.departingTicketPrice = ((childNum * (300 * (0.1 * 300) + (0.05 * 300))) + (adultNum * (300 * (0.1 * 300) + (0.05 * 300)))) + 250;
        }
    }

    public void setReturnTicketPrice() {
        if (tripType.toString() == "RETURN") {
            this.returnTicketPrice = departingTicketPrice;
        } else {
            this.returnTicketPrice = 0;
        }
    }

}