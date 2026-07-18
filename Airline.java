//Name: Lior Zussman
//Date: 15.7.2026
//This class creates the basis of the airline that is in charge of the flights that the passengers will board
public class Airline {
    private Flight[] flights;
    private String airlineName;
    private int currentFlights;

    public Airline(String airlineName, int maxFlights) {
        this.airlineName = airlineName;
        flights = new Flight[maxFlights];
        currentFlights = 0;
    }

    
    public void printAllFlights() {
        for (int i = 0; i < currentFlights; i++) {
            System.out.println(flights[i]);
        }
    }
    
    //checks if airline is full and if it is you can't add flight
    // searches if flight already exists by it's number
    // if it passes the last two, the flight is created
    public boolean addFlight(Flight f) {

        if (currentFlights == flights.length) {
            return false;
        }
        for (int i = 0; i < currentFlights; i++) {

            if (flights[i].getFlightNum().equals(f.getFlightNum())) {
                return false;
            }

        }
        flights[currentFlights] = f;
        currentFlights++;
        return true;
    }

     
    
    
    //gets a number 
    public Flight getFlightByNumber(String number) {
        for (int i = 0; i < currentFlights; i++) {
            //then searches every flight until it matches the number
            if (flights[i].getFlightNum().equals(number)) {
                //returns the flight that matched
                return flights[i];
            }

        }
        //if it doesn't match it returns null
        return null;
    }

    public void printFlightsTo(String destination) {
        for (int i = 0; i < currentFlights; i++) {
            if (flights[i].getFlightDestination().equals(destination)) {
                System.out.println(flights[i]);
            }
        }
    }
    
    
    
    
    public Flight getFullestFlight() {
        if (currentFlights == 0) {
            //checks if there are even any flights
            return null;
        }
        Flight fullestFlight = flights[0];
        //then goes through everyone and compares them until it finds the fullest
        for (int i = 1; i < currentFlights; i++) {
            if (flights[i].getOccupancyPercent() > fullestFlight.getOccupancyPercent()) {
                fullestFlight = flights[i];
            }

        }
        //then it returns the fullest
        return fullestFlight;
    }


    
    
    
    
    
    public boolean movePassenger(String passportId, String fromFlight, String toFlight) {

        Flight source = null;
        Flight destination = null;
        //searches if the flights are the same flights
        if (fromFlight.equals(toFlight)) {
            return false;
        }
        
        for (int i = 0; i < currentFlights; i++) {
         //searches if the flight the passenger is on exists
            if (flights[i].getFlightNum().equals(fromFlight)) {
                source = flights[i];
            }
            //then searches if the flight we want to move him to exists
            if (flights[i].getFlightNum().equals(toFlight)) {
                destination = flights[i];
            }
        }

        if (source == null || destination == null) {
            return false;
        }

        //then searches through passengers to see if the passenger exists
        Passenger passenger = source.findPassenger(passportId);
        
        if (passenger == null) {
            return false;
        }
        //then adds passenger to the flight he wants to go to
        if (!destination.addPassenger(passenger)) {
            return false;
        }
        //then removes passenger from previous flight
        source.removePassenger(passportId);

        return true;
    }

}