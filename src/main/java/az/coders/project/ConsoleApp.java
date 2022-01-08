package az.coders.project;


import az.coders.project.commands.Cmd;
import az.coders.project.controllers.Controller;
import az.coders.project.exceptions.CommandNotFoundException;

import java.util.Scanner;

public class ConsoleApp {

    private static final Scanner sc = new Scanner(System.in);
    private static final Controller controller = new Controller();

    public static void run() {

        System.out.println("ConsoleApp run!");

        while (true) {
            System.out.println("1. Online table.(SHOW)");
            System.out.println("2. Flight information(INFO).");
            System.out.println("3. Flights search and booking(BOOK).");
            System.out.println("4. Booking cancelling(CANCEL).");
            System.out.println("5. My flights(FLIGHTS).");
            System.out.println("6. Exit(EXIT).");
            System.out.print("Enter command: ");
            String cmd = sc.nextLine();

            if (controller.cmd(cmd.trim())) {
                try {
                    throw new CommandNotFoundException("Could not find command:" + cmd);
                } catch (CommandNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (cmd.equalsIgnoreCase(Cmd.EXIT.name())) {
                System.out.println("bye");
                break;
            } else if (cmd.equalsIgnoreCase(Cmd.SHOW.name())) {
                controller.show();
            } else if (cmd.equalsIgnoreCase(Cmd.INFO.name())) {
                controller.info();
            } else if (cmd.equalsIgnoreCase(Cmd.BOOK.name())) {
                controller.book();
            } else if (cmd.equalsIgnoreCase(Cmd.CANCEL.name())) {
                controller.cancel();
            } else if (cmd.equalsIgnoreCase(Cmd.FLIGHTS.name())) {
                controller.flights();
            }
        }

        System.out.println("ConsoleApp stop!");
    }
}
