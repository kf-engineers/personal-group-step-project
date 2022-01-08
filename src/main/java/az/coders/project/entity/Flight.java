package az.coders.project.entity;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Flight {

    private long id;
    private String flightNumber;
    private Date date;
    private Time time;
    private String destination;
    private Time duration;

    public Flight() {
    }

    public Flight(long id, String flightNumber, Date date, Time time, String destination, Time duration) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.date = date;
        this.time = time;
        this.destination = destination;
        this.duration = duration;
    }

    public Flight(String flightNumber, Date date, Time time, String destination, Time duration) {
        this.flightNumber = flightNumber;
        this.date = date;
        this.time = time;
        this.destination = destination;
        this.duration = duration;
    }

    public Flight(Date date, String destination) {
        this.date = date;
        this.destination = destination;
    }

    public long getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getDestination() {
        return destination;
    }

    public Time getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && flightNumber.equals(flight.flightNumber) && date.equals(flight.date) && time.equals(flight.time) && destination.equals(flight.destination) && duration.equals(flight.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, date, time, destination, duration);
    }

    @Override
    public String toString() {
        return "[" +
                "id : " + id +
                ", flightNumber : '" + flightNumber + '\'' +
                ", date : " + date +
                ", time : " + time +
                ", destination : '" + destination + '\'' +
                ", duration: " + duration +
                ']';
    }
}
