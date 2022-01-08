package az.coders.project.entity;

import java.util.Objects;

public class Booking {

    private long bookId;
    private Person person;
    private Flight flight;

    public Booking(long bookId, Person person, Flight flight) {
        this.bookId = bookId;
        this.person = person;
        this.flight = flight;
    }

    public Booking(Person person, Flight flight) {
        this.person = person;
        this.flight = flight;
    }

    public Booking() {
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookId == booking.bookId && person.equals(booking.person) && flight.equals(booking.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, person, flight);
    }

    @Override
    public String toString() {
        return "[" +
                "bookId :" + bookId +
                ", person :" + person +
                ", date : " + flight.getDate() +
                ", destination : '" + flight.getDestination() + '\'' +
                ']';
    }
}
