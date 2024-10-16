import java.io.PrintWriter;
import java.io.FileNotFoundException;
/**
 * This class is for parking lot objects and contains methods related to them.
 *
 * @Kyle Cochrane
 * @
 */
public class ParkingLot
{
    private int parkID ;   // the id of the parking lot.
    private char size;     // the size of the parking lot (it can be “S”, “M” or “L”)
    private int count;     //count of number of vehicle objects in parking lot
    private Vehicle[] vehicles; //array of vehicles
    private boolean empty;         //a boolean used to detect if there are no vehicles of type requested in parking lot.
    private double averageCarFee, averageMotorcycleFee, averageBicycleFee; //a storage for the average fees of each vehicle type

    /**
     * Constructor for objects of class ParkingLot
     */
    public ParkingLot(int parkID, char size) //constructor method to initialise state of parking lot
    {
        vehicles = new Vehicle[3];
        averageCarFee = 0;
        averageMotorcycleFee = 0;
        averageBicycleFee = 0;
        this.parkID = parkID;
        this.size = size;
    }

    public String getVehicleYInfo(int y) //get vehicle
    {
        if(vehicles[y] != null) //if vehicle exists, return its string
        {
            return vehicles[y].toString();
        }
        return ""; //else return nothing
    }

    public void setParkID(int parkID)
    {
        this.parkID = parkID;

    }

    public int getParkID()
    {
        return parkID;
    }

    public void setParkSize(char size) //setting parking lot size, must be s, m, or l//
    {
        this.size=size; 
    }

    public char getParkSize()
    {
        size = Character.toUpperCase(size); //for user entered lowercase letters to uppercase
        return size;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public int getCount()
    {
        return count;
    }

    public void decreaseCount() //method for decreasing count of vehicles in parking lot when deleting vehicles
    {
        count --;
    }

    public void increaseCount() //method for increasing count of vehicles in parking lot when adding vehicles
    {
        count ++;
    }

    private void setAverageCarFee(double averageCarFee) //mutator method for average car fee of lot
    {
        this.averageCarFee = averageCarFee;
    }

    public double getAverageCarFee() //an accessor method for system ui to grab average vehicle fee of car types
    {
        return averageCarFee;
    }

    private void setAverageMotorcycleFee(double averageMotorcycleFee) //mutator method for average motorcycle fee of lot
    {
        this.averageMotorcycleFee = averageMotorcycleFee;
    }

    public double getAverageMotorcycleFee() //an accessor method for system ui to grab average vehicle fee of motorcycle types
    {
        return averageMotorcycleFee;
    }

    private void setAverageBicycleFee(double averageBicycleFee) //mutator method for average bicycle fee of lot
    {
        this.averageBicycleFee = averageBicycleFee;
    }

    public double getAverageBicycleFee() //an accessor method for system ui to grab average vehicle fee of bicycle types
    {
        return averageBicycleFee;
    }

    public void setEmpty(boolean empty)
    {
        this.empty = empty;
    }

    public boolean getEmpty()
    {
        return empty;
    }

    public int getVehiclesLength() //method that returns length of vehicle array for each parking lot
    {
        return vehicles.length;
    }

    public boolean vehicleIDExists(int vehicleID) //method which tests if entered ID matches existing ID in lot
    {
        boolean idExists = false; //initialise boolean to false
        for(int x = 0; x<vehicles.length; x++) //loop through every vehicle 
        {
            if((vehicles[x] != null) && (vehicleID == vehicles[x].getVehicleID())) //if vehicle exists and entered ID matches vehicle ID
            {
                idExists = true; //set id exists to true
            }
        }
        return idExists; //return this
    }

    public boolean testForSpace() //method to test for space
    {
        if((getParkSize() == 'S') && (vehicles[0] == null)) //if parking lot is small and vehicle 1 empty
        {
            return true;
        }
        else if((getParkSize() == 'M') && ((vehicles[0] == null) || (vehicles[1] == null)))// if parking lot is medium and vehicle 1 or 2 empty
        {
            return true;
        }
        else if((getParkSize() == 'L') && ((vehicles[0] == null) || (vehicles[1] == null) || (vehicles[2] == null))) //if parking lot is large and vehicle 1, 2, or 3 is empty
        {
            return true;
        }
        return false;
    }

    public String addVehicleToLot(int vehicleID, String vehicleType, int entryTime, int parkingDuration)
    {
        Vehicle tempVehicle = new Vehicle(vehicleID, vehicleType, entryTime, parkingDuration);
        int vehicleAdded = 0; //will be 1 if a vehicle added
        String outputMessage= ""; //returning the output message on vehicle added
        outputMessage += "Vehicle ID = " +tempVehicle.getVehicleID()+"\n"; //append to message
        outputMessage += "Vehicle's Type = " +tempVehicle.getVehicleType()+"\n";
        outputMessage += "Entry Time = " +tempVehicle.getEntryTime()+"\n";
        outputMessage += "Parking Duration = " +tempVehicle.getParkingDuration()+"\n";
        outputMessage += "Vehicle fee = " +tempVehicle.getParkingFee();
        increaseCount(); //increase count of vehicles in lot.
        for(int i = 0; i<vehicles.length; i++) //looping through vehicles, not really necessary but would be better for large scale applications
        {
            if(vehicles[i] == null) //if vehicle null, set vehicles to temp vehicle
            {
                vehicles[i] = tempVehicle;
                vehicleAdded++;
                break;
            }
        }
        return outputMessage;
    }

    public String getShortVehicleInfo() //getting info for vehicles when user requests lists of parking lots. Number of vehicles in lot determines output
    {
        String outputMessage = "";
        for(int i = 0; i<vehicles.length; i++)
        {
            if(vehicles[i] != null)
            {
                outputMessage += "\n\tVehicle "+vehicles[i].getVehicleID()+" is of type: "+vehicles[i].getVehicleType(); //if vehicle exists print this then create new line
            }
        }
        return outputMessage;
    }

    public String deleteVehicleFromLot(int vehicleID) //method to delete vehicle from parking lot
    {
        String outputMessage = "no"; //string to print vehicle deleted message
        boolean matched; //boolean to detect if vehicle was deleted
        for(int i = 0; i<vehicles.length; i++)
        {
            if((vehicles[i] != null) && (vehicleID == vehicles[i].getVehicleID())) //if id matches vehicle ID
            {
                vehicles[i] = null; //null the vehicle
                outputMessage = "yes"; //set output to yes
                decreaseCount(); //decreases count of vehicles in lot
            }
        }
        return outputMessage;
    }

    public String getVehicleInfo() //getting info for vehicles when user requests list of vehicles in a parking lot. Number of vehicles in lot determines output
    {
        String outputMessage ="";
        for(int i = 0; i<vehicles.length; i++)
        {
            if(vehicles[i] != null) //if vehicle exists
            {
                outputMessage += "Vehicle "+vehicles[i].getVehicleID()+" is of type "+vehicles[i].getVehicleType()+" and entered the parking lot at time "+vehicles[i].getEntryTime()+"\n"; //if vehicle i exists print this then create new line
            }
        }
        if(outputMessage.equals("")) //if no vehicles in lot 
        {
            outputMessage += "No Vehicles parked.\n";
        }
        outputMessage += "-------------------------------------------------------------------\n"; //new line break at end
        return outputMessage;
    }

    public String getVehicleInfoByType(String vehicleType) //a method that returns the string of vehicle info for type
    {
        String outputMessage ="";
        for(int i = 0; i<vehicles.length; i++)
        {
            if((vehicles[i] != null) && (vehicles[i].getVehicleType().equalsIgnoreCase(vehicleType))) //if vehicles exists and its type matches the entered type
            {
                outputMessage += "\nVehicle "+vehicles[i].getVehicleID()+" matches that vehicle type and it is parked at parking lot#"+getParkID(); //print this 
                outputMessage = outputMessage.trim(); //trim extra lines of outputMessage. Mainly to cut of extra newline at start if the line being printed is the 1st line of type being printed in system interface i.e getting rid of white line at start of printing
            }
        }
        return outputMessage;
    }
    
    public void calculateAverageFees()
    {
        int carCount= 0;
        int motorcycleCount = 0;
        int bicycleCount = 0; //count variable that divides the sum based on number of vehicle of type
        double averageCarFee = 0;
        double averageMotorcycleFee = 0;
        double averageBicycleFee = 0;
        double carTotalFee = 0;
        double motorcycleTotalFee = 0;
        double bicycleTotalFee = 0; //double variables to store and calculate average of each type
        for(int i = 0; i<vehicles.length; i++) //looping through all vehicles of lot
        {
            if((vehicles[i] != null && ((vehicles[i].getVehicleType()).equalsIgnoreCase("car")))) //if vehicle i exists and type is car
            {
                carTotalFee += vehicles[i].getParkingFee(); //adding vehicle i fee to total fee for car type
                carCount++; //increase counter of number of cars
            }
            if((vehicles[i] != null && ((vehicles[i].getVehicleType()).equalsIgnoreCase("motorcycle")))) //if vehicle i exists and type is motorcycle
            {
                motorcycleTotalFee += vehicles[i].getParkingFee(); //adding vehicle i fee to total fee for motorcycle type
                motorcycleCount++; //increase counter of number of motorcycles
            }
            if((vehicles[i] != null && ((vehicles[i].getVehicleType()).equalsIgnoreCase("bicycle")))) //if vehicle i exists and type is bicycle
            {
                bicycleTotalFee += vehicles[i].getParkingFee(); //adding vehicle i fee to total fee for bicycle type
                bicycleCount++; //increase counter of number of bicycles
            }
        }
        if(carCount != 0)//branch to ensure Nans arent printed
        {
            averageCarFee = carTotalFee/carCount; //average type fee = total type fee / number of vehicles with a type fee
            setAverageCarFee(averageCarFee); //setting total car fee
        }
        else
        {
            setAverageCarFee(0);
        }
        if(motorcycleCount != 0)//branch to ensure Nans arent printed
        {
            averageMotorcycleFee = motorcycleTotalFee/motorcycleCount; //average type fee = total type fee / number of vehicles with a type fee
            setAverageMotorcycleFee(averageMotorcycleFee); //setting total motorcycle fee
        }
        else
        {
            setAverageMotorcycleFee(0);
        }
        if(bicycleCount != 0)//branch to ensure Nans arent printed
        {
            averageBicycleFee = bicycleTotalFee/bicycleCount; //average type fee = total type fee / number of vehicles with a type fee
            setAverageBicycleFee(averageBicycleFee); //setting total bicycle fee
        }
        else
        {
            setAverageBicycleFee(0);
        }
    }

    

}
