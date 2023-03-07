package prog2.exercise4.flight.booking.system;

import java.time.LocalDate;
import java.util.UUID;

public class FlightBooking {
    private final String flightCompany = "Flights-of-Fancy";//This must NOT be entered by the Passenger
    private String flightID;//This must NOT be entered by the Passenger
    private String passengerFullName;

    public enum BookingClass {FIRST, BUSINESS, ECONOMY}

    public enum TripSource {NANJING, BEIJING, SHANGHAI, OULU, HELSINKI, PARIS}

    public enum TripDestination {NANJING, BEIJING, SHANGHAI, OULU, HELSINKI, PARIS}

    public enum TripType {ONE_WAY, RETURN}

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
    private double departingTicketPrice = 0; //This must NOT be entered by the Passenger
    private double returnTicketPrice = 0; //This must NOT be entered by the Passenger
    private double totalTicketPrice = 0; //This must NOT be entered by the Passenger
    private String ticketNumber = generateTicketNumber();/*This must NOT be entered by the Passenger*/
    private boolean flag = false;
    private String oldDate;

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
            if (temp == 14 || temp == 12) {
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

    public String toString1() {
        return "Thank you for booking your flight with " + flightCompany + "."
                + "Following are the details" + "\n" + "of your booking and the trip:" + "\n" + "\n"
                + "Ticket Number: " + ticketNumber + "\n"
                + "Passenger Name:" + passengerFullName + "\n"
                + "From " + tripSource + " to " + tripDestination + "\n"
                + "Date of departure: " + departureDate + "\n"
                + "Date of return: " + returnDate + "\n"
                + "Total passengers: " + totalPassengers + "\n"
                + "Total ticket price in Euros: " + totalTicketPrice;
    }

    public String toString2() {
        return "Thank you for booking your flight with " + flightCompany + "."
                + "Following are the details" + "\n" + "of your booking and the trip:" + "\n" + "\n"
                + "Ticket Number: " + ticketNumber + "\n"
                + "Passenger Name:" + passengerFullName + "\n"
                + "From " + tripSource + " to " + tripDestination + "\n"
                + "Date of departure: " + departureDate + "\n"
                + "Date of return: " + returnDate + "(Changed from old " + oldDate + " to new " + returnDate + ")" + "\n"
                + "Total passengers: " + totalPassengers + "\n"
                + "Total ticket price in Euros: " + totalTicketPrice + "\n" + "\n"
                + "IMPORTANT NOTICE: As per our policy, the return date was changed because it was" + "\n"
                + "less than two days apart from your departure date.";
    }

    public void setTripDestination(String tripDestinationChoice) {
        int choice = Integer.parseInt(tripDestinationChoice);
        switch (choice) {
            case 1:
                this.tripDestination = TripDestination.NANJING;
                break;
            case 2:
                this.tripDestination = TripDestination.BEIJING;
                break;
            case 3:
                this.tripDestination = TripDestination.OULU;
                break;
            case 4:
                this.tripDestination = TripDestination.HELSINKI;
                break;
            default:
                System.out.println("Invalid trip source choice: " + tripDestinationChoice);
        }
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
        this.tripType = choice == 1 ? TripType.ONE_WAY : TripType.RETURN;
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
            flag = true;
            oldDate = returnDate.toString();
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
        this.totalTicketPrice = departingTicketPrice + returnTicketPrice;
    }

    public void setTicketNumber() {
        this.ticketNumber = generateTicketNumber();
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTotalPassengers(int num1, int num2) {
        totalPassengers = num1 + num2;
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
        String tempString = ticketNumber.substring(7,10);
        if (tempString.equals("DOM")) {
            double temp = (childNum * (300 + (0.1 * 300) + (0.05 * 300))) + (adultNum *(300 + (0.1 * 300) + (0.05 * 300)));
            if(bookingClass.equals(BookingClass.ECONOMY)){
                departingTicketPrice = temp + 50;
            } else if (bookingClass.equals(BookingClass.BUSINESS)) {
                departingTicketPrice = temp + 150;
            } else if (bookingClass.equals(BookingClass.FIRST)) {
                departingTicketPrice = temp + 250;
            }
        }else if (tempString.equals("INT")){
            double temp = (childNum * (300 + (0.15 * 300) + (0.1 * 300))) + (adultNum + (300 + (0.15 * 300) + (0.1 * 300)));
            if(bookingClass.equals(BookingClass.ECONOMY)){
                departingTicketPrice = temp + 50;
            } else if (bookingClass.equals(BookingClass.BUSINESS)) {
                departingTicketPrice = temp + 150;
            } else if (bookingClass.equals(BookingClass.FIRST)) {
                departingTicketPrice = temp + 250;
            }
        }
    }

    public void setReturnTicketPrice() {
        if (tripType.toString().equals("RETURN")) {
            this.returnTicketPrice = departingTicketPrice;
        } else {
            this.returnTicketPrice = 0;
        }
    }

    public boolean getFlag() {
        return flag;
    }
}