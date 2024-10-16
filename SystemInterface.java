import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

/**
 * This class is a system interrface that the user interacts with to create and manipulate parking lots and vehicles
 *
 * @Kyle Cochrane
 * 
 */
public class SystemInterface
{
    private int option;
    private ParkingLot[] parkingLots; //private variable for parking lots
    private double averageCarFee, averageMotorcycleFee, averageBicycleFee; //double variable that stores that total average car, motorcycle, and bicycle fees of all 3 lots.

    public SystemInterface() //constructor method to initialise objects
    {
        parkingLots = new ParkingLot[10]; //setting array size to 10 for 10 parking lots
        averageCarFee = 0;
        averageMotorcycleFee = 0;
        averageBicycleFee = 0;
    }

    private void run()
    {
        System.out.println("Hello, user. This is the system interface for the parking lot management program developed by Kyle Cochrane.");
        System.out.println("Various actions can be taken by the user, including creating a parking lot, adding a vehicle into a parking lot, and more.");
        System.out.println("To select which action you wish to take, please follow the guidelines below:");
        do
        {
            chooseOption();
            switch(option)
            {
                case 1: 
                    addParkingLot();
                    break;
                case 2:
                    addVehicle();
                    break;
                case 3:
                    deleteParkingLot();
                    break;
                case 4:
                    deleteVehicle();
                    break;
                case 5:
                    getParkingLots();
                    break;
                case 6:
                    getListOfVehiclesInLot();
                    break;
                case 7:
                    getListOfVehiclesByType();
                    break;
                case 8:
                    getAverageParkingFees();
                    break;
                case 9:
                    readFromFile();
                    break;
                case 10:
                    writeToFile();
                    break;
                case -1:
                    System.out.println("Thank you for using the program!");
                    System.out.println("Program terminated.");
                    System.out.println("-------------------------------------------------------------------");
                    break;
                default:
                    break;
            }
        }
        while(option!=-1); //while option between  -1  and 8, and isnt -1
    }

    public static void main(String[] args)
    {
        SystemInterface systemUI = new SystemInterface();
        systemUI.run();
    }

    private void chooseOption() //method for choosing an action to undertake//
    {
        System.out.println("If you wish to create a new parking lot, enter 1.");
        System.out.println("If you wish to add a vehicle into a parking lot, enter 2.");
        System.out.println("If you wish to delete a parking lot, enter 3.");
        System.out.println("If you wish to remove a vehicle from a parking lot, enter 4.");
        System.out.println("If you wish to request a list of parking lots and their associated information, enter 5.");
        System.out.println("If you wish to request a list of vehicles parked in a parking lot, enter 6.");
        System.out.println("If you wish to request a list of parked vehicles of a certain vehicle type, enter 7.");
        System.out.println("If you wish to view a summary of average parking fees for all vehicles types parked at all parking lots, enter 8.");
        System.out.println("If you wish to import an external txt file, enter 9.");
        System.out.println("If you wish to export the data into an external txt file, enter 10.");
        System.out.println("If you are finished with the System Interface, enter -1.");
        Scanner keyboard = new Scanner(System.in);

        int chosenOption;
        String chooseOption;
        chooseOption = keyboard.nextLine();

        try
        {
            chosenOption = Integer.parseInt(chooseOption); //try parse string to integer
            do{
                if((chosenOption<=10) && (chosenOption>=-1) && (chosenOption !=0)) //while between -1 and 8 but not 0 as 0 doesnt choose a method
                {
                    option = chosenOption; 
                    break; //loops until valid option chosen.
                }
                else
                {
                    System.out.println("You have not entered a valid option. Please try again.");
                    System.out.println("-------------------------------------------------------------------");
                    chosenOption = keyboard.nextInt();
                }
            }while(true);            
        }
        catch(NumberFormatException e)
        {
            System.out.println("You must enter an integer for the option.");
            System.out.println("-------------------------------------------------------------------");
        }
    }

    public void addParkingLot()
    {
        int existingLots = 0; // a counter if lot spaces are full, if counter hits 10, returns message saying all parking lot spaces are full
        int parkID = 0; //variables for parkId and size
        char size;
        boolean IDmatched; // a boolean used to control a loop for if ented ID matches already existing ID.
        System.out.println("You have chosen to create a new parking lot.");
        for(int x = 0; x<parkingLots.length; x++) //loop that increases fullLots accumulator for every existing lot
        {
            if (parkingLots[x] != null) {
                existingLots++;
            }
        }
        if(existingLots == 10) //if 10 parking lots exist
        {
            System.out.println("10 parking lots already exist.");
            System.out.println("-------------------------------------------------------------------");
            return; //exit method
        }
        for(int i = 0; i<parkingLots.length; i++) //loop to test if lots empty
        {
            if(parkingLots[i] == null)
            {
                Scanner keyboard = new Scanner(System.in);  
                while(true) //looping till a valid ID is entered
                {
                    System.out.println("Please enter a parking Lot ID."); 
                    String ID = keyboard.nextLine(); //entering as string then attempting to convert for park Id
                    try
                    {
                        parkID = Integer.parseInt(ID); //try parse string to integer
                        break; //if successfull parsed, exits loop
                    }
                    catch(NumberFormatException e) //if a number format exception is automatically thrown when attempting to parse, print this line
                    {
                        System.out.print("You have not entered a valid parking lot ID. Please enter a valid integer for the parking lot ID.");
                    }
                }
                do //an outer loop that repeats until entered ID matches no existing IDs,
                {
                    IDmatched = false; //set to false
                    for(int y = 0; y<parkingLots.length; y++) //inner loop to test if ID matches
                    {
                        if((parkingLots[y] != null) && (parkingLots[y].getParkID() == parkID)) //if parking lot y exists and entered parking ID matches lot y id, assign random id
                        {
                            IDmatched = true; //an id has been matched, so the do while loop must continue by setting this boolean to true
                            System.out.print(" ID #"+parkID+" already exists.");
                            parkID = (int) (Math.random() * 9000);
                            System.out.println(" Randomly assigned ID #"+parkID);
                            break; //if a random ID match, break out of inner loop to retest randomised ID against all existing ID's
                        }
                    }
                }while(IDmatched == true);
                while(true) //looping till a valid size is entered
                {
                    System.out.println("Please enter the size of the parking lot.");
                    System.out.println("Please enter either \"S\" for small, \"M\" for medium, or \"L\" for large.");
                    String lotSize= keyboard.nextLine(); //entering as string then attempting to convert for parking lot size
                    if(lotSize.length() == 1) //if entered string is length 1
                    {
                        size = lotSize.charAt(0);
                        while((size != 's') && (size != 'S')  && (size != 'm') && (size != 'M') && (size != 'l') && (size != 'L')) //while size isn't s, m or l,
                        {
                            System.out.println("You have not entered a valid parking lot size.");
                            System.out.println("Please enter either \"S\" for small, \"M\" for medium, or \"L\" for large.");

                            size = keyboard.next().charAt(0);
                        }
                        break; //once a valid size, break out of loop
                    }
                    else
                    {
                        System.out.println("You have not entered a valid size. Please enter a 1 character long piece of text as a size.");
                    }
                }
                ParkingLot pl = new ParkingLot(parkID, size);
                parkingLots[i] = pl;
                System.out.println("-------------------------------------------------------------------");
                break; //break out of loop if a parking lot was null
            }
        }
    }

    public void addVehicle()
    {
        System.out.println("You have chosen to create a new vehicle.");
        int parkID;
        int vehicleID, entryTime, parkingDuration; //user entered vehicle info
        String vehicleType; //user entered vehicle Type
        boolean IDmatched; // a boolean used to control a loop for if ented ID matches already existing ID.
        Scanner user = new Scanner(System.in);
        while(true) //looping till a valid ID is entered
        {
            System.out.println("Please enter a vehicle ID.");
            String ID =user.nextLine(); //entering as string then attempting to convert for vehicle ID
            try
            {
                vehicleID = Integer.parseInt(ID); //try parse string to integer
                break; //if successfull parsed, exits loop
            }
            catch(NumberFormatException e) //if a number format exception is automatically thrown when attempting to parse, print this line
            {
                System.out.print("You have not entered a valid vehicle ID. Please enter a valid integer for the vehicleID.");
            }
        }
        do //an outer loop that repeats until entered ID matches no existing IDs,
        {
            IDmatched = false; //set to false
            for(int y = 0; y<parkingLots.length; y++) //loop through every parking lot
            {
                if(parkingLots[y] != null)
                {
                    if (parkingLots[y].vehicleIDExists(vehicleID)) 
                    { //if vehicles ID exists 
                        IDmatched = true; //an id has been matched, so the do while loop must continue by setting this boolean to true
                        System.out.print(" ID #"+vehicleID+" already exists.");
                        vehicleID = (int) (Math.random() * 9000);
                        System.out.println(" Randomly assigned ID #"+vehicleID);
                    }
                }
            }
        }while(IDmatched == true);
        while(true) //looping until a valid vehicle type is entered
        {
            System.out.println("Please enter a vehicle Type.");
            System.out.println("Vehicle type must be either \"car\", \"motorcycle\", or \"bicycle\".");
            vehicleType = user.nextLine();
            if((vehicleType.equalsIgnoreCase("car")) || (vehicleType.equalsIgnoreCase("motorcycle")) || (vehicleType.equalsIgnoreCase("bicycle"))) //has to be car, motorcycle, or bicycle
            {
                vehicleType = vehicleType.toUpperCase();//to set a matching type to uppercase
                break;
            }
            System.out.println("You have not entered a valid vehicle type.");
        }
        while(true) //looping until valid entry time entered
        {
            System.out.println("Please enter the vehicles entry time"); //getting entry time
            System.out.println("Entry time must be a whole number between 0 and 23, with 0 representing 12am and 23 representing 11pm.");
            String entry = user.nextLine();
            try
            {
                entryTime = Integer.parseInt(entry); //try parse string to integer
                if((entryTime >= 0) && (entryTime<= 23)) //entry time has to be between 0 and 23
                {
                    break;
                }
                else
                {
                    System.out.println("Entry time must be a whole number between 0 and 23, with 0 representing 12am and 23 representing 11pm.");
                }
            }
            catch(NumberFormatException e) //if a number format exception is automatically thrown when attempting to parse, print this line
            {
                System.out.print("You have not entered a valid entry time. Please enter a valid integer for the entry time.");
            }
        }
        while(true) //looping until valid parking duration entered
        {
            System.out.println("Please enter the vehicle's expected duration in the parking lot.");
            System.out.println("The exit time of the vehicle must not exceed 12am, meaning the duration and the entry time combined must not exceed 24.");
            String duration = user.nextLine();
            try
            {
                parkingDuration = Integer.parseInt(duration); //try parse string to integer
                if((parkingDuration < 1) || (parkingDuration + entryTime <= 24)) //if vehicle leaves lot at 12am max
                {
                    break;
                }
                else
                {
                    System.out.println("The exit time of the vehicle must not exceed 12am. Enter a duration with a max value of "+(24-entryTime)+" to ensure the exit time occurs within the same day. Parking duration should be at least one hour."); //tells user the max duration they can enter
                }
            }
            catch(NumberFormatException e) //catches automatically thrown numberformatexception on failed parse
            {
                System.out.print("You have not entered a valid parking duration. Please enter a valid integer for the parking duration.");
            }
        }
        int parkingLot = 0;//counter for lots looped through
        int lotsWithNoSpace = 0;//counter for lots without space
        for (int i = 0; i < parkingLots.length; i++) //looping through ID of all parking lots
        {
            if(parkingLots[i] != null)
            {
                parkingLot++;
                if(parkingLots[i].testForSpace() == false) //if no space
                {
                    lotsWithNoSpace++;
                    continue; //go to next lot
                }
                else
                {
                    System.out.println(parkingLots[i].addVehicleToLot(vehicleID, vehicleType, entryTime, parkingDuration));
                    System.out.println("-------------------------------------------------------------------");
                    return; //return to exit method if vehicle is added
                }
            }
        }
        if(parkingLot == 0)
        {
            System.out.println("There are no parking Lots to parking the vehicle into.");
        }
        if(parkingLot == lotsWithNoSpace)
        {
            System.out.println("There is no space in parking lots.");
            System.out.println("-------------------------------------------------------------------");
            return; //to exit out of method is no space in parking lot
        }
    }

    public void deleteParkingLot()//method to delete parking lots
    {
        Scanner user = new Scanner(System.in);       
        int count = 0; //accumulat used in if condition to detect if a parking lot id match was found
        while(true)
        {
            System.out.println("Enter the ID of the parking lot you wish to delete.");
            String parklotID = user.nextLine();
            try
            {
                int parkID = Integer.parseInt(parklotID); //converting to int variable
                for(int i = 0; i<parkingLots.length; i++) //looping through all parking lot ID's
                {
                    if(parkID == parkingLots[i].getParkID()) //if entered ID matches parking Lot ID
                    {
                        parkingLots[i] = null; //null the parking lot
                        count++;
                        System.out.println("Parking lot #"+parkID+" has been deleted.");
                        System.out.println("-------------------------------------------------------------------");
                        return;
                    }
                }
                if(count == 0) //if no parking lot id matched
                {
                    System.out.println("The entered parking Lot ID #"+parkID+" is not an existing parking lot ID.");
                    System.out.println("-------------------------------------------------------------------");
                    return;
                }
            }
            catch(NumberFormatException e) //catches automatically thrown numberformat exception
            {
                System.out.println("You have not entered a valid parking lot ID. Please enter a valid integer for the parking lot ID.");
            }
        }
    }

    public void deleteVehicle() //method to delete vehicles
    {
        Scanner user = new Scanner(System.in);       
        int count = 0; //accumulat used in if condition to detect if vehicleID match was found   
        while(true)
        {
            boolean vehicleDeleted = false; //a boolean to check if a vehicle was deleted
            System.out.println("Please enter the ID of the vehicle you wish to delete.");
            String vehicleId = user.nextLine();
            try
            {
                int vehicleID = Integer.parseInt(vehicleId); //converting to int variable
                for(int i = 0; i<parkingLots.length; i++) //looping through all parking lot's
                {
                    if(parkingLots[i] != null) //if current parking lot not null, run delete method on it.
                    {
                        String deletionResult = parkingLots[i].deleteVehicleFromLot(vehicleID);
                        if(deletionResult.equals("yes")) //if did delete vehicle
                        {
                            System.out.println("Vehicle #"+vehicleID+" has been deleted.");
                            System.out.println("-------------------------------------------------------------------");
                            vehicleDeleted = true;
                            return; //exit loop after successful deletion
                        }
                    }
                }
                if(vehicleDeleted == false)
                {
                    System.out.println("The entered vehicleID does not match any existing vehicle ID.");
                    System.out.println("-------------------------------------------------------------------");
                    return; //exit loop if entered ID doesn't match any existing vehicle ID
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("You have not entered a valid vehicle ID. Please enter a valid integer for the vehicle ID."); 
            }
        }
    }

    public void  getParkingLots() //method to list parking lots + vehicles in them
    {
        int count = 0; //counter variable to check if no parking lots exist, will increase by one if any parking lot does
        for(int i = 0; i<parkingLots.length; i++) //loop that prints on a lot basis
        {            
            if(parkingLots[i] != null)
            {
                System.out.println("-------------------------------------------------------------------");
                System.out.print("Parking lot #"+parkingLots[i].getParkID() + " has size " +parkingLots[i].getParkSize() +" and " +parkingLots[i].getCount()+" vehicle(s).");
                System.out.println(parkingLots[i].getShortVehicleInfo());
                count++;
            }
        }
        if(count == 0) //if there was no parking lots
        {
            System.out.println("There are no parking lot's available"); //print this
        }
        System.out.println("-------------------------------------------------------------------");
    }

    public void getListOfVehiclesInLot() //method to print list of vehicles in lot
    {
        Scanner user = new Scanner(System.in);
        while(true)
        {
            System.out.println("Please enter the ID of the parking lot you wish to request a list of vehicles in.");
            String parkLotID = user.nextLine();
            boolean found = false; //a boolean that detects whether a matching parking lot was found
            try
            {
                int parkID = Integer.parseInt(parkLotID);
                for(int i = 0; i<parkingLots.length; i++) //looping to match ID to an element of parking lot array
                {
                    if((parkingLots[i] != null) && (parkingLots[i].getParkID() == parkID)) //if parking lot not null and id matches user entered ID
                    {
                        String vehicleInfo = parkingLots[i].getVehicleInfo();
                        vehicleInfo = vehicleInfo.trim(); //to remove end of line
                        System.out.println(vehicleInfo);
                        found = true;
                        return;
                    }
                }
                if(found == false)
                {
                    System.out.println("No parking lot found with the ID: " + parkID);
                    System.out.println("-------------------------------------------------------------------");
                    return; //exit upon false parking lot ID
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("You have not entered a valid parking lot ID. Please enter a valid integer for the parking lot id.");
            }
        }
    }

    public void getListOfVehiclesByType() //method to print list of vehicles by type
    {
        Scanner user = new Scanner(System.in);
        String lotTypeInfo = ""; //a string that stores the results of the type print method for each lot
        String totalTypeInfo = ""; // a string that stores the total output to be printed
        System.out.println("Please enter the type of vehicle's you wish to request a list of.");
        String vehicleType = user.nextLine();
        while((!(vehicleType.equalsIgnoreCase("car"))) && (!(vehicleType.equalsIgnoreCase("motorcycle"))) && (!(vehicleType.equalsIgnoreCase("bicycle"))))
        {
            System.out.println("Entered vehicle type is not one of the approved vehicle types. Please enter an approved vehicle type.");
            vehicleType = user.nextLine();
        } 
        for(int i = 0; i<parkingLots.length; i++)//loop through all parking lots.
        {
            if(parkingLots[i] != null) //if parking lot exists
            {
                lotTypeInfo = parkingLots[i].getVehicleInfoByType(vehicleType); //adding 
                totalTypeInfo += "\n"+lotTypeInfo;
            }
        }
        if(totalTypeInfo.isEmpty()) //if total type info empty
        {
            System.out.println("No vehicles with that vehicle type.");
        }
        else
        {
            totalTypeInfo = totalTypeInfo.trim();
            System.out.println(totalTypeInfo);
        }   
        System.out.println("-------------------------------------------------------------------");
    }

    public void getAverageParkingFees() //method to print average fees
    {
        for(int i = 0; i<parkingLots.length; i++) //looping through all parking lots to calculate their average
        {
            if(parkingLots[i] != null) //if parking lot exists, calculate it's averages
            {
                parkingLots[i].calculateAverageFees();
            }
        }
        printTotalFees(); //calculate total average fees plus print them
        for(int i = 0; i<parkingLots.length; i++) //looping through parking lots
        {
            if(parkingLots[i] != null) //if lot exists, print output for it
            {
                if(parkingLots[i].getCount() != 0) //if there is a vehicle in lot
                {
                    System.out.println("Parking lot #"+parkingLots[i].getParkID()+" :"); //parking lot# parkingLot1ID:
                    System.out.println("\tAverage parking fees of \"car\" vehicles is $"+parkingLots[i].getAverageCarFee()); //total average fee for car type parking lot 1
                    System.out.println("\tAverage parking fees of \"motorcycle\" vehicles is $"+parkingLots[i].getAverageMotorcycleFee()); //total average fee for motorcycle type parking lot 1
                    System.out.println("\tAverage parking fees of \"bicycle\" vehicles is $"+parkingLots[i].getAverageBicycleFee()); //total average fee for bicycle type parking lot 1
                    System.out.println("-------------------------------------------------------------------");
                }
                else
                {
                    System.out.println("\tNo vehicles in parking lot#"+parkingLots[i].getParkID()); //no vehicles exist in lot 1 print this
                    System.out.println("-------------------------------------------------------------------");
                }
            }
        }        
    }

    private void calculateAverageFees()
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
        for(int i=0; i<parkingLots.length; i++) //loop through all lots
        {
            if(parkingLots[i] != null) //if lot exists, addaverage fee's to overall type average fee 
            {
                carTotalFee += parkingLots[i].getAverageCarFee();
                motorcycleTotalFee += parkingLots[i].getAverageMotorcycleFee();
                bicycleTotalFee += parkingLots[i].getAverageBicycleFee();
                if(parkingLots[i].getAverageCarFee() != 0) //only add to count if there was an amount to add to average fee
                {
                    carCount++;
                }
                if(parkingLots[i].getAverageMotorcycleFee() != 0) //only add to count if there was an amount to add to average fee
                {
                    motorcycleCount++;
                }
                if(parkingLots[i].getAverageBicycleFee() != 0) //only add to count if there was an amount to add to average fee
                {
                    bicycleCount++;
                }
            }
        }
        if(carCount != 0) //if count not 0 to avoid Nan
        {
            averageCarFee = carTotalFee/carCount; //average car fee = total fee divided by number of lots included in calculation
            setAverageCarFee(averageCarFee); //setting total car fee
        }
        else
        {
            setAverageCarFee(0);
        }
        if(motorcycleCount != 0) //if count not 0 to avoid Nan
        {
            averageMotorcycleFee = motorcycleTotalFee/motorcycleCount; //average motorcycle fee = total fee divided by number of lots included in calculation
            setAverageMotorcycleFee(averageMotorcycleFee); //setting total motorcycle fee
        }
        else
        {
            setAverageMotorcycleFee(0);
        }
        if(bicycleCount != 0) //if count not 0 to avoid Nan
        {
            averageBicycleFee = bicycleTotalFee/bicycleCount; //average bicycle fee = total fee divided by number of lots included in calculation
            setAverageBicycleFee(averageBicycleFee); //setting total bicycle fee
        }
        else
        {
            setAverageBicycleFee(0);
        }
    }

    private void readFromFile() //method that reads from File
    {
        Scanner keyboard = new Scanner(System.in);
        Scanner inputStream = null;

        System.out.println("Please enter the name of the text file you wish to import (Don't include the extension)");
        String fName = keyboard.next();

        String fileName = fName+".txt"; //adding user entered name to file Name

        try
        {
            inputStream = new Scanner(new File(fileName));
            System.out.println("File "+fileName+" has been imported.");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening the file " + fileName);
            return;
        }
        while(inputStream.hasNextLine())
        {
            String nextLine = inputStream.nextLine();

            if(nextLine.equals("----------"))
            {
                continue; //skip loop
            }
            if(nextLine.equals("ParkingLot")) //if a parking lot exists
            {
                nextLine = inputStream.nextLine(); //to skip the -------- before ID
                int parkID = 0;
                char parkSize = ' ';
                int parkingLotCounter = 0; //counts if parkingLots were added, if 0 returns message parking lots full              

                String idLine = inputStream.nextLine();
                if(idLine.startsWith("ID "))
                {
                    parkID = Integer.parseInt(idLine.substring(idLine.lastIndexOf(' ')+1)); //get ID by taking last word
                }
                String sizeLine = inputStream.nextLine();
                if(sizeLine.startsWith("Size "))
                {
                    parkSize = sizeLine.charAt(5); //get size by taking last word
                }
                for(int i = 0; i<parkingLots.length; i++) //loop through lots for empty lots tp add space
                {
                    if(parkingLots[i] == null)
                    {
                        ParkingLot pl = new ParkingLot(parkID, parkSize);
                        parkingLots[i] = pl;
                        parkingLotCounter++;
                        nextLine = inputStream.nextLine();
                        nextLine = inputStream.nextLine();
                        nextLine = inputStream.nextLine();
                        nextLine = inputStream.nextLine();

                        while(nextLine.contains("ID")) //looping through vehicles. Based on text file and loop strucutre, loop looks for dashed line or another line with ID, with the dashed line indicating a new parking lot
                        {
                            int vehicleId = 0; //set all variables 
                            int entryTime = 0;
                            int parkingDuration = 0; 
                            String vehicleType = "";
                            String vIdLine = nextLine; //get ID line
                            vehicleId = Integer.parseInt(vIdLine.substring(vIdLine.lastIndexOf(' ')+1)); //get ID by taking last word
                            String typeLine =inputStream.nextLine();
                            vehicleType += typeLine.substring(typeLine.lastIndexOf(' ')+1); //get type by taking last word
                            String entryLine = inputStream.nextLine();
                            entryTime = Integer.parseInt(entryLine.substring(entryLine.lastIndexOf(' ')+1)); //get entry time by taking last word
                            String durationLine = inputStream.nextLine();
                            parkingDuration = Integer.parseInt(durationLine.substring(durationLine.lastIndexOf(' ')+1)); //get duration by taking 2nd word
                            inputStream.nextLine(); //skipping over fee line
                            parkingLots[i].addVehicleToLot(vehicleId, vehicleType, entryTime, parkingDuration);
                            if(inputStream.hasNextLine())
                            {
                                nextLine = inputStream.nextLine(); //skipping over blank space that appears after every vehicle
                                nextLine = inputStream.nextLine();
                            }
                            else
                            {
                                break;
                            }
                        }
                        break;
                    } 
                } 
                if(parkingLotCounter == 0) //if no parking lots were added, it was because all parking lot spaces were full
                {
                    System.out.println("There is no room to import parking lots.");
                }
            }
        }
        inputStream.close();
    }

    private void writeToFile() //method that writes to file
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please enter the name of the text file you wish to create (Don't include the extension)");
        String fName = keyboard.next();

        String fileName = fName+".txt"; //adding user entered name to create a text file

        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter(fileName); //if successful create file
            System.out.println("File creation successful."); //let user know
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening the file "+fileName);
            return; //if can't create and open file, return to options menu
        }
        for(int i = 0; i<parkingLots.length; i++) //loop through all parking lots
        {  
            if(parkingLots[i] != null) //if parking lots exist
            {
                outputStream.println("----------");
                outputStream.println("ParkingLot");
                outputStream.println("----------");
                outputStream.println("ID "+parkingLots[i].getParkID());
                outputStream.println("Size "+parkingLots[i].getParkSize());
                outputStream.println("----------");
                outputStream.println("Vehicles");
                outputStream.println("----------");
                for(int y = 0; y<parkingLots[i].getVehiclesLength(); y++) //loop through all vehicles of lot
                {
                    if(!(parkingLots[i].getVehicleYInfo(y).isEmpty())) //if vehicle y has a toString to print, print it
                    {
                        outputStream.println(parkingLots[i].getVehicleYInfo(y));
                    }
                }
            }
        }
        outputStream.close();
    }

    private void printTotalFees() //calculates and prints total fees
    {
        calculateAverageFees();
        System.out.println("Average parking fees for all vehicle types across all parking lots: ");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Average parking fees of \"car\" vehicles is $"+getAverageCarFee());
        System.out.println("Average parking fees of \"motorcycle\" vehicles is $"+getAverageMotorcycleFee());
        System.out.println("Average parking fees of \"bicycle\" vehicles is $"+getAverageBicycleFee());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Breakdown by parking lot: ");
        System.out.println("-------------------------------------------------------------------");
    }

    private void setAverageCarFee(double averageCarFee) //mutator method for averageCarFee
    {
        this.averageCarFee = averageCarFee;
    }

    private void setAverageMotorcycleFee(double averageMotorcycleFee) //mutator method for averagMotorcycleFee
    {
        this.averageMotorcycleFee = averageMotorcycleFee;
    }

    private void setAverageBicycleFee(double averageBicycleFee) //mutator method for averageBicycleFee
    {
        this.averageBicycleFee = averageBicycleFee;
    }

    private double getAverageCarFee() //accessor method for averageCarFee
    {
        return averageCarFee;
    }

    private double getAverageMotorcycleFee() //accessor method for averageMotorcycleFee
    {
        return averageMotorcycleFee;
    }

    private double getAverageBicycleFee() //accessor method for average Motorcycle Fee
    {
        return averageBicycleFee;
    }

}
