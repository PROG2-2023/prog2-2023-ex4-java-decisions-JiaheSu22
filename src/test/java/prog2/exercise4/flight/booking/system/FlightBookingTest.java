package prog2.exercise4.flight.booking.system;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;

import org.junit.Test;


public class FlightBookingTest 
{
    
    @Test
    public void testing_Setting_Up_Trip_Source_Using_Enum() {
        FlightBooking.TripSource tripSource = FlightBooking.TripSource.NANJING;
        assertEquals(FlightBooking.TripSource.valueOf("NANJING"), tripSource);
    }

    @Test
    public void testing_Setting_Up_Booking_Class_Using_Enum() {
        FlightBooking.BookingClass bookingClass = FlightBooking.BookingClass.FIRST;
        assertEquals(FlightBooking.BookingClass.valueOf("FIRST"), bookingClass);
    }

    @Test
    public void testing_total_values_in_Booking_Class_Enum() {
        int expected = 3;
        int result = FlightBooking.BookingClass.values().length;
        assertEquals(expected, result);
    }

    @Test
    public void testing_Setting_Up_Trip_Source_Using_Input_Choice() {
        FlightBooking fb = new FlightBooking(null, null, null, 0, 0);
        fb.setTripSource("2");
        FlightBooking.TripSource expected = FlightBooking.TripSource.BEIJING;
        FlightBooking.TripSource  result = fb.getTripSource(); 
        assertEquals(expected, result);
    }

    @Test
    public void testing_Departure_And_Return_Date_Difference() {
        
        FlightBooking fb = new FlightBooking(null, null, null, 0, 0);
        
        String sdepart = "2023-03-04";
        LocalDate departureDate = LocalDate.parse(sdepart);

        String sreturn = "2023-03-05";
        LocalDate returnDate = LocalDate.parse(sreturn);

        LocalDate expected = departureDate.plusDays(2);

        fb.setDepartureDate(departureDate);
        //LocalDate departingOn = fb.getDepartureDate();

        fb.setReturnDate(returnDate);
        LocalDate result = fb.getReturnDate();
        
        //int result = returningOn.compareTo(departingOn);

        //assertNotEquals(expected, result);
        assertEquals(expected, result);
    }

    @Test
    public void testing_Conditional_Ticket_Number_Generation() {
        
        FlightBooking fb = new FlightBooking(null, null, null, 0, 0);
        
        String expectedTicketNumber = "11FASDFDOM";

        String expectedSubstring1 = expectedTicketNumber.substring(0,2);
        String expectedSubstring2 = expectedTicketNumber.substring(7, 9);

        String ticketNumber = fb.getTicketNumber();

        String returnedSubstring1 = ticketNumber.substring(0, 2);
        
        String returnedSubstring2 = ticketNumber.substring(7, 9);

        if(expectedSubstring1.equals(returnedSubstring1) && expectedSubstring2.equals(returnedSubstring2)){
            assertEquals(1,1);
        }
        else {
            assertEquals(1,2);
        }
    }

    @Test
    public void testing_Conditional_Ticket_Price_Calculation() {
        FlightBooking fb = new FlightBooking(null, null, null, 0, 0);
        
        FlightBooking.TripSource source = FlightBooking.TripSource.NANJING;
        FlightBooking.TripDestination destination = FlightBooking.TripDestination.BEIJING;

        FlightBooking.TripType type = FlightBooking.TripType.RETURN;

        FlightBooking.BookingClass bookClass = FlightBooking.BookingClass.FIRST;

        int child = 2;
        int adults = 5;
        fb.setTripSource("1");
        fb.setTripDestination("1", "2");

        fb.setTripType("2");
        fb.setBookingClass("1");

        fb.setDepartingTicketPrice(child, adults);
        fb.setReturnTicketPrice();

        fb.setTotalTicketPrice();

        double returnedPrice = Math.abs(fb.getTotalTicketPrice());
        double expectedPrice = Math.abs((((child *(300 * (0.1*300) + (0.05*300))) + (adults*(300 * (0.1*300) + (0.05*300)))) + 250)*2);

        if (returnedPrice == expectedPrice){
            assertEquals(1, 1);
        }
        else {
            assertEquals(1, 2);
        }
    }   
}
