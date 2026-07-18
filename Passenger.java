//Name: Lior Zussman
//Date: 13.7.2026
//This class creates the basis of the passengers  that will be boarding our flights

public class Passenger {
    private String fullName;
    private String passportNumber;
    private int age;
    private double baggageWeight;
    private boolean validAge;
    private boolean validweight;

    public Passenger(String fullName, String passportNumber, int age, double baggageWeight) {
        this.fullName = fullName;
        this.passportNumber = passportNumber;
        setAge(age);
        setBaggageWeight(baggageWeight);

    }

    //getters

    public String getFullName() {
        return fullName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public int getAge() {
        return age;
    }

    public double getBaggageWeight() {
        return baggageWeight;
    }

    //setters that first check that the number you input is valid

    public void setAge(int newAge) {
        if (newAge >= 0 && newAge <= 120) {
            age = newAge;
            validAge = true;
        } else {
            validAge = false;
            System.out.println("INVALID AGE");
        }
    }

    public void setBaggageWeight(double newBaggageWeight) {
        if (newBaggageWeight <= 32 && newBaggageWeight >= 0) {
            baggageWeight = newBaggageWeight;
            validweight = true;
        } else {
            validweight = false;
            System.out.println(" Invalid Baggage Weight");
        }
    }
    
   
    public String toString() {
         //checks if the age and weight is valid 
        if (validAge && validweight) {
            //if they both are valid prints the passenger's full info
            return "Passenger's full name is " + fullName + " and their age is " + age + ". Their passport number is "
                    + passportNumber + " and their baggage weight is " + baggageWeight;
        } else {
            return "INVALID PASSENGER";
        }
    }

    

    public boolean isOverweight() {
        return baggageWeight > 23;
    }

}