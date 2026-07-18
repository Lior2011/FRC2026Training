//Name: Lior Zussman
//Date: 14.7.2026
//This class creates the basis of the flights in our airline that passengers will board

public class Flight {
    private String flightNum;
    private String origin;
    private String destination;
    private int maxCapacity;
    private Passenger[] passengers;
    private int currentPassengers;
    private int delayMinutes;

    public Flight(String flightNum, String origin, String destination, int maxCapacity, int delayMinutes) {
        this.flightNum = flightNum;
        this.origin = origin;
        this.destination = destination;
        this.maxCapacity = maxCapacity;
        passengers = new Passenger[maxCapacity];
        currentPassengers = 0;
        this.delayMinutes = delayMinutes;
    }

    //set delay (bonus)
    public void setDelay(int minutes) {
        if (minutes >= 0) {
            delayMinutes = minutes;
        }
    }

    //getters

    public int getDelay() {
        return delayMinutes;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public String getFlightDestination() {
        return destination;
    }

    
    
    public boolean addPassenger(Passenger p) {

        if (currentPassengers == maxCapacity) {
            //checks if passenger can be added
            return false;
        }

        for (int i = 0; i < currentPassengers; i++) {
            //checks if passenger exists
            if (passengers[i].getPassportNumber().equals(p.getPassportNumber())) {
                return false;
            }
        }
        // based on that returns either true or false

        passengers[currentPassengers] = p;
        currentPassengers++;

        return true;
    }

    public double getOccupancyPercent() {
        return (double) currentPassengers / maxCapacity * 100;
    }

    public boolean isFull() {
        if (currentPassengers == maxCapacity) {
            return true;
        } else {
            return false;
        }
    }

    
    
    public Passenger findPassenger(String passportId) {
        //gets an Id and then searches through every passenger's Id 
        for (int i = 0; i < currentPassengers; i++) {
            if (passengers[i].getPassportNumber().equals(passportId)) {
               // prints the passenger that matches that Id
                return passengers[i];
            }

        }
        //or prints null
        return null;
    }

    //searches first if passenger exists
    // then moves every passenger in the array forward
    // then removes the final one (the passenger we requested to delete)
    public boolean removePassenger(String passportId) {
        for (int i = 0; i < currentPassengers; i++) {
            if (passengers[i].getPassportNumber().equals(passportId)) {
                for (int j = i; j < currentPassengers - 1; j++) {
                    passengers[j] = passengers[j + 1];
                }
                passengers[currentPassengers - 1] = null;
                currentPassengers--;
                return true;

            }

        }
        return false;
    }

    //adds every passenger's baggage weight to get the total
    public double getTotalBaggageWeight() {
        double total = 0;
        for (int i = 0; i < currentPassengers; i++) {
            total += passengers[i].getBaggageWeight();

        }
        return total;
    }

    //prints the whole flight info
    public String toString() {
        return "The flight number is " + flightNum + "The flight route is " + origin + " to " + destination
                + " and the flight occupancy is " + getOccupancyPercent() + "%" + " and the delay is " + delayMinutes
                + " minutes ";
    }

}