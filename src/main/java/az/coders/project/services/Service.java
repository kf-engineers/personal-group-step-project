package az.coders.project.services;


import az.coders.project.entity.Booking;
import az.coders.project.entity.Flight;

import java.util.Date;
import java.util.List;

public interface Service {

    List<Flight> showFlights();

    Flight getFlightInfoById();

    List<Flight> getSearchingFlights();

    void bookingFlights();

    void cancelFlights();

    List<Booking> getMyFlights();

    boolean checkCmd(String cmd);

    long checkFlightId();

    Date checkDate();

    String checkDestination();

    String checkFlightNumber();

    int checkPeopleNumber();

    String checkName();

    String checkSurname();

    int checkAge();

    long checkBookingId();
}
