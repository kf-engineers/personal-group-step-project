package az.coders.project.controllers;


import az.coders.project.entity.Flight;
import az.coders.project.services.Service;
import az.coders.project.services.ServiceImpl;

public class Controller {

    private static final Service service = new ServiceImpl();

    public boolean cmd(String cmd) {
        return service.checkCmd(cmd);
    }

    public void show() {
        System.out.println("Online Table Airport: Kiev Boryspil");
        service.showFlights()
                .forEach(System.out::println);
    }

    public void info() {
        System.out.println("Flight info.");

        Flight flight = service.getFlightInfoById();

        System.out.println(flight);
    }

    public void book() {
        service.bookingFlights();
    }

    public void cancel() {
        service.cancelFlights();
    }

    public void flights() {
        System.out.println("My flights.");

        service.getMyFlights()
                .forEach(System.out::println);

    }

}
