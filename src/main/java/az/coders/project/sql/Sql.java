package az.coders.project.sql;

import az.coders.project.entity.Flight;
import az.coders.project.entity.Person;

public interface Sql {

    String getAllFlights();

    String getFlightById(long id);

    String getFlightByDestinitionAndDate(Flight flight);

    String booking(Flight flight, Person person);

    String cancel(long id);

    String myFlights();

    String findMaxId();

    String findMaxBookId();

    String getMyFlightsCount();
}
