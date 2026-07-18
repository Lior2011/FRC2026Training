//Name: Lior Zussman
//Date: 17.7.2026
//This class is the final program our airline can use to manage their flight and passenger plan

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Airline airline = new Airline("Markia", 10);
        int choice;
        //do loop that runs until we stop it 
        do {
            //prints all options
            System.out.println("1. Add flight");
            System.out.println("2. Add passenger to flight");
            System.out.println("3. Show all flights");
            System.out.println("4. Search passenger by passport");
            System.out.println("5. Move passenger between flights");
            System.out.println("6. Busiest flight");
            System.out.println("7. Update flight delay");
            System.out.println("0. Exit");
           
            //promts user to choose a number
            System.out.print("Choice: ");
            if (input.hasNextInt()) {
                //if user inputs an int then choice becomes that int
                choice = input.nextInt();
                input.nextLine();
            } 
            else {
                //if user doesn't input an int prints an error 
                System.out.println("Invalid choice. Please enter a number.");
                input.nextLine(); 
                // resets choice
                choice = -1; 
            }

            switch (choice) {
                
                case 1:
                    //if the user inputs "1"
                    //add flight
                    //asks user the flight info
                    System.out.print("Flight number: ");
                    String number = input.nextLine();

                    System.out.print("Origin of the Flight: ");
                    String origin = input.nextLine();

                    System.out.print("Destination of the flight: ");
                    String destination = input.nextLine();

                    System.out.print("Maximum capacity of the flight: ");
                    
                    //if the input isn't an int then print invalid
                    while (!input.hasNextInt()) {
                        System.out.println("Invalid capacity. Please enter a whole number.");
                        input.nextLine();
                    }

        
                    int capacity = input.nextInt();
                    while (capacity <= 0) {
                        System.out.println("Capacity must be greater than 0. Maximum capacity of the flight:");
                        capacity = input.nextInt();
                    }

                    input.nextLine();

                    //creates a flight based the info the user inputted
                    Flight flight = new Flight(number, origin, destination, capacity, 0);
                    
                    //adds flight and prints if it was succesfull
                    if (airline.addFlight(flight)) {
                        System.out.println(" Flight added successfully.");
                    } else {
                        System.out.println("Could not add flight.");
                    }

                    break;

                case 2:
                    //if the user inputs "2"
                    //add passenger
                    
                    //asks user which flight to add the passenger to
                    System.out.println("Flight number:");
                    String flightNumber = input.nextLine();
                    
                    //searches through flights if the inputed number is a flight's number
                    Flight newFlight = airline.getFlightByNumber(flightNumber);
                   
                    //if it isn't, we get "Flight not found"
                    if (newFlight == null) {
                        System.out.println("Flight not found.");
                        break;
                    }

                    //asks for passenger info
                    System.out.println("Passenger Full Name:");
                    String name = input.nextLine();

                    System.out.println("Passenger's Passport Number:");
                    String pasNum = input.nextLine();

                    
                    System.out.println("Passenger's Age:");
                    //if user doesn't input int, print invalid
                    while (!input.hasNextInt()) {
                        System.out.println("Invalid age. Please enter a whole number.");
                        input.nextLine();
                    }
                    int passengerAge = input.nextInt();

                    //if user doesn't input double/int prints invalid
                    System.out.println("Passenger's Baggage Weight:");
                    while (!input.hasNextDouble()) {
                        System.out.println("Invalid baggage weight. Please enter a number.");
                        input.nextLine();
                    }

                    double baggageWeight = input.nextDouble();
                    input.nextLine();

                    //based on the info the user inputed, creates passenger
                    Passenger passenger = new Passenger(name, pasNum, passengerAge, baggageWeight);
                    
                    //tries adding passenger and prints if successful or not
                    if (newFlight.addPassenger(passenger)) {
                        System.out.println(" Passenger added successfully.");
                    } else {
                        System.out.println("Could not add passenger.");
                    }

                    break;

                case 3:
                    //if the user inputs "3"
                    
                    //prints every flight in the airline
                    airline.printAllFlights();
                    break;

                case 4:
                    //if the user inputs "4"

                    //requests the flight the upassenger is on
                    System.out.println("Flight number:");
                    String newFlightNumber = input.nextLine();

                    Flight newFlight1 = airline.getFlightByNumber(newFlightNumber);

                    //checks if flight exists
                    if (newFlight1 == null) {
                        System.out.println("Flight not found.");
                        break;
                    }

                    //asks for passenger's passport to find passenger
                    System.out.println("Passenger passport number:");
                    String passport = input.nextLine();

                    Passenger newPassenger = newFlight1.findPassenger(passport);

                    //if the passport number doesn't match with anyone, print not found
                    if (newPassenger == null) {
                        System.out.println("Passenger not found.");
                    } 
                    
                    else {
                        //if it does match, print the passenger's info
                        System.out.println("Passenger found:");
                        System.out.println(newPassenger.toString());
                    }

                    break;

                case 5:
                    //if the user inputs "5"
                    //move passenger

                    //asks for the requested passenger's passport number
                    System.out.println("Passenger passport number:");
                    String newPassport = input.nextLine();
                    
                    //asks from which flight to which flight
                    System.out.println("From flight:");
                    String fromFlight = input.nextLine();

                    System.out.println("To flight:");
                    String toFlight = input.nextLine();

                   //if everything is valid, the passenger is valid
            
                    if (airline.movePassenger(newPassport, fromFlight, toFlight)) {
                        System.out.println("Passenger moved successfully.");
                    }
                    
                    else {
                        //if not the user gets this:
                        System.out.println("Could not move passenger.");
                    }
                    break;

                case 6:
                    //if the user inputs "6"
                    
                    //gets fullest flight in the airline
                    Flight fullest = airline.getFullestFlight();
                    
                    //if there are no flights
                    if (fullest == null) {
                        System.out.println("No flights exist.");
                    } 
                    
                    else {
                        //prints the fullest
                        System.out.println("The fullest flight is:");
                        System.out.println(fullest);
                    }

                    break;

                case 7:
                    //if the user inputs "7"
                    //bonus I chose was flight delay


                    //asks for the flight that will be getting delayed
                    System.out.println("Flight number:");
                    String flightNum = input.nextLine();

                    Flight f = airline.getFlightByNumber(flightNum);

                    //if the flight doesn't exist
                    if (f == null) {
                        System.out.println("Flight not found.");
                        break;
                    }

                    //asks for delay
                    System.out.println("Delay in minutes:");

                   //makes sure user inputed an int
                    while (!input.hasNextInt()) {
                        System.out.println("Enter a valid number.");
                        input.nextLine();
                    }

                    int delay = input.nextInt();

                    //if delay is negative
                    while (delay < 0) {
                        System.out.println("Delay cannot be negative. Enter again:");
                        delay = input.nextInt();
                    }
                    input.nextLine();

                    //sets delay and lets user know
                    f.setDelay(delay);

                    System.out.println("Delay updated.");

                    break;

                case 0:
                    //if the user inputs "0"
                    //ends program
                    System.out.println("Bye, have a great day!");
                    break;

                default:
                    //if the user inputs a number that isn't an option
                    System.out.println("Invalid choice.");
                    break;
            }

        } 
        //checks if user inputed 0 to know when to exit the program
        while (choice != 0);
        input.close();

    }
}
