package prog2.exercise4.flight.booking.system;

import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FlightBooking p = new FlightBooking();
        System.out.println("Please enter your full name>> ");
        p.setPassengerFullName(in.nextLine());
        System.out.println("Please enter your trip source(1.NANJING 2.BEIJING 3.OULU 4.HELSINKI)>> ");
        p.setTripSource(in.nextLine());
        System.out.println("Please enter your trip destination(1.NANJING 2.BEIJING 3.OULU 4.HELSINKI)>>");
        p.setTripDestination(in.nextLine());
        System.out.println("Please enter your trip type(1.ONEWAY/2.RETURN)>>");
        p.setTripType(in.nextLine());
        System.out.println("Date of departure(YYYY-MM-DD)>> ");
        p.setDepartureDate(LocalDate.parse(in.nextLine()));
        if (p.getTripType().toString().equals("RETURN")) {
            System.out.println("Date of return(YYYY-MM-DD)>> ");
            p.setReturnDate(LocalDate.parse(in.nextLine()));
        }
        System.out.println("Number of children>> ");
        int childNum = in.nextInt();
        System.out.println("Number of adults>> ");
        int adultNum = in.nextInt();
        p.setTicketNumber();
        p.setTotalPassengers(childNum, adultNum);
        p.setDepartingTicketPrice(childNum, adultNum);
        p.setTotalTicketPrice();
        if (p.getFlag()) {
            System.out.println(p.toString2());
        } else {
            System.out.println(p.toString1());
        }
    }
}
