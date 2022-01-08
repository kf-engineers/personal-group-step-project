package az.coders.project.services;


import az.coders.project.commands.Cmd;
import az.coders.project.dao.JDBC;
import az.coders.project.dao.PgJDBC;
import az.coders.project.entity.Booking;
import az.coders.project.entity.Flight;
import az.coders.project.entity.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ServiceImpl implements Service {

    private static final Scanner sc = new Scanner(System.in);
    private static final JDBC pgjdbc = new PgJDBC();

    @Override
    public List<Flight> showFlights() {
        return pgjdbc.getFlights();
    }

    @Override
    public Flight getFlightInfoById() {
        long id = checkFlightId();

        return pgjdbc.getFlightInfoById(id);
    }

    @Override
    public List<Flight> getSearchingFlights() {

        System.out.println("Enter flight info.");

        Date date = checkDate();

        String destination = checkDestination();

        pgjdbc.getSearchingFlights(new Flight(date,destination)).forEach(System.out::println);

        return pgjdbc.getSearchingFlights(new Flight(date,destination));
    }

    @Override
    public void bookingFlights() {
        boolean isFlightExist = false;
        int peopleNumber = 0;
        List<Flight> flights = getSearchingFlights();
        Flight bookFlight = null;

        String flightNumber = checkFlightNumber();


        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                isFlightExist = true;
                bookFlight = flight;
            }
        }

        if (isFlightExist) {
            peopleNumber = checkPeopleNumber();
        }

        for (int i = 0; i < peopleNumber; i++) {
            String name = checkName();

            String surname = checkSurname();

            int age = checkAge();

            System.out.println("------------------------");

            pgjdbc.bookingFlights(bookFlight, new Person(name, surname, age));
        }
    }

    @Override
    public void cancelFlights() {
        System.out.println("Cancel flight");

        long id = checkBookingId();
        long previousCount = pgjdbc.getMyFlightsCount();

        pgjdbc.cancelFlights(id);

        long presentCount = pgjdbc.getMyFlightsCount();

        if (presentCount < previousCount) {
            System.out.println("Cancellation was successful");
        } else {
            System.out.println("Cancellation failed");
        }
    }

    @Override
    public List<Booking> getMyFlights() {
        return pgjdbc.getMyFlights();
    }

    @Override
    public boolean checkCmd(String cmd) {
        for (Cmd cmdd : Cmd.values()) {
            if (cmd.trim().equalsIgnoreCase(cmdd.name())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public long checkFlightId() {
        boolean c = true;
        int Id = 0;
        while (c) {
            System.out.print("Enter Id: ");
            Id = sc.nextInt();

            if (Id < 0) {
                System.out.println("Id can't be negative!");
            } else if (Id > lastFlightId()) {
                System.out.println("Id not found.");
            } else {
                c = false;
            }
        }
        sc.nextLine();
        return Id;
    }

    @Override
    public Date checkDate() {
        boolean c = true;
        String date = null;
        while (c) {
            System.out.print("Enter date(YYYY-MM-DD): ");
            date = sc.nextLine();

            if (date.isEmpty()) {
                System.out.println("Date can't be empty!");
            } else if ((boolean) dateFormat(date).get(0)) {
                System.out.println("Please try again");
            } else {
                c = false;
            }
        }
        return (Date) dateFormat(date).get(1);
    }

    @Override
    public String checkDestination() {
        boolean c = true;
        String destination = null;
        while (c) {
            System.out.print("Enter destination: ");
            destination = sc.nextLine();

            if (destination.isEmpty()) {
                System.out.println("Destination can't be empty!");
            } else {
                c = false;
            }
        }
        return destination;
    }

    @Override
    public String checkFlightNumber() {
        boolean c = true;
        String flightNumber = null;
        while (c) {
            System.out.print("Enter flightNumber: ");
            flightNumber = sc.nextLine();

            if (flightNumber.isEmpty()) {
                System.out.println("FlightNumber can't be empty!");
            } else {
                c = false;
            }
        }
        return flightNumber;
    }

    @Override
    public int checkPeopleNumber() {
        boolean c = true;
        int peopleNumber = 0;
        while (c) {
            System.out.print("Enter people number: ");
            peopleNumber = sc.nextInt();

            if (peopleNumber <= 0) {
                System.out.println("People number can't be 0 or negative!");
            } else {
                c = false;
            }
        }
        sc.nextLine();
        return peopleNumber;
    }

    @Override
    public String checkName() {
        boolean c = true;
        String name = null;
        while (c) {
            System.out.print("Enter name: ");
            name = sc.nextLine();

            if (name.isEmpty()) {
                System.out.println("Name can't be empty!");
            } else {
                c = false;
            }
        }
        return name;
    }

    @Override
    public String checkSurname() {
        boolean c = true;
        String surname = null;
        while (c) {
            System.out.print("Enter surname: ");
            surname = sc.nextLine();

            if (surname.isEmpty()) {
                System.out.println("Surname can't be empty!");
            } else {
                c = false;
            }
        }
        return surname;
    }

    @Override
    public int checkAge() {
        boolean c = true;
        int age = 0;
        while (c) {
            System.out.print("Enter age: ");
            age = sc.nextInt();

            if (age <= 0) {
                System.out.println("Age can't be 0 or negative!");
            } else {
                c = false;
            }
        }
        sc.nextLine();
        return age;
    }

    @Override
    public long checkBookingId() {

        boolean c = true;
        int bookId = 0;
        while (c) {
            System.out.print("Enter Id: ");
            bookId = sc.nextInt();

            if (bookId < 0) {
                System.out.println("Id can't be negative!");
            } else if (bookId > lastBookId()) {
                System.out.println("Id not found.");
            } else {
                c = false;
            }
        }
        sc.nextLine();
        return bookId;
    }

    public static long lastBookId() {
        return pgjdbc.findMaxBookId();
    }

    public static long lastFlightId() {
        return pgjdbc.findMaxId();
    }

    private static List<Object> dateFormat(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date date1 = null;

        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            System.out.println("Date format wrong!");
            return Arrays.asList(true);
        }
        return Arrays.asList(false, date1);
    }
}
