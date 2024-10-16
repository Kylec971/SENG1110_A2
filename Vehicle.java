
/**
 * This is the class for the vehicle objects that are stored in parking lots
 *
 * @Kyle Cochrane
 * 
 */
public class Vehicle
{

    private int vehicleID;          // the id of the vehicle.
    private String vehicleType;     // the type of the vehicle. It can be only “Car”, “Motorcycle” or “Bicycle”.
    private int entryTime;         // the time when the vehicle entered the parking lot.
    private int parkingDuration;    // the expected parking duration of the vehicle.
    private double parkingFee;      // the calculated parking fee. Represents units in dollars.

    public Vehicle(int vehicleID, String vehicleType, int entryTime, int parkingDuration) //constructor for vehicle objects
    {
        this.vehicleID = vehicleID;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.parkingDuration = parkingDuration;
        calculateParkingFee();
    }

    public void setVehicleID(int vehicleID)
    {
        this.vehicleID = vehicleID;
    }

    public int getVehicleID()
    {
        return vehicleID;
    }

    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType()
    {
        return vehicleType;
    }

    public void setEntryTime(int entryTime)
    {
        this.entryTime = entryTime;
    }

    public int getEntryTime()
    {
        return entryTime;
    }

    public void setParkingDuration(int parkingDuration)
    {
        this.parkingDuration = parkingDuration;
    }

    public int getParkingDuration()
    {
        return parkingDuration;
    }

    public void setParkingFee(double parkingFee)
    {
        this.parkingFee = parkingFee;
    }

    public double getParkingFee()
    {
        return parkingFee;
    }
    
    public String toString() //a to string method for vehicles, for the purpose of printing to text file
    {
        return ("ID "+ vehicleID + "\n" +
               "Type "+ vehicleType + "\n" +
               "Entry "+ entryTime + "\n" +
               "Duration "+ parkingDuration + "\n" +
               "Fee " + parkingFee + "\n");
    }

    public void calculateParkingFee() //method to calculate parking fee of vehicle
    {
        double rate; //rate for calculations
        double fee; //total fee entered into parking duration mutator method
        double peakTimeFee = 0;
        int firstTwoHours = 0; //controller variable for first 2 hours time loop, also used to store 
        int remainingParkingDuration; //variable to store hours after first 2 hours for calculation purposes
        if(getVehicleType().equalsIgnoreCase("CAR")) //if vehicle type car
        {
            rate = 6;
            if(getParkingDuration()>2) 
            {

                if(((entryTime >= 6) && (entryTime < 10)) || (((entryTime >=15) && (entryTime < 19)))) //if entered during peak period 6-10am or 3pm-7pm
                {
                    rate = rate*1.30;
                    while(firstTwoHours<2)
                    {
                        peakTimeFee = peakTimeFee + rate; //adding a hourly amount for each of the first 2 hours
                        firstTwoHours++;
                    }
                    remainingParkingDuration = getParkingDuration() - 2; //getting remaining hours
                    rate = (rate/13)*9; //resetting rate + 10% discount for hours over 2
                    fee = (rate*remainingParkingDuration) + peakTimeFee; //rate of hours past 2nd hour multiplied by remaining hours after first 2 hours
                    setParkingFee(fee);
                }
                else
                {
                    rate = rate*0.95;
                    while(firstTwoHours<2)
                    {
                        peakTimeFee = peakTimeFee + rate; //adding a hourly amount for each of the first 2 hours
                        firstTwoHours++;
                    }
                    remainingParkingDuration = getParkingDuration() - 2; //getting remaining hours
                    rate = (rate/9.5)*9; //resetting rate + 10% discount for hours over 2
                    fee = (rate*remainingParkingDuration) + peakTimeFee; //rate of hours past 2nd hour multiplied by remaining hours after first 2 hours
                    setParkingFee(fee);
                }
            }
            else
            {
                if(((entryTime >= 6) && (entryTime < 10)) || (((entryTime >=15) && (entryTime < 19)))) //if entered during peak period 6-10am or 3pm-7pm
                {
                    rate = rate*1.30; //duration is only 2 hours or less here
                    fee = rate*getParkingDuration();
                    setParkingFee(fee);
                }
                else
                {
                    rate = rate*0.95; //5% discount for first 2 hours if entered outside peak times
                    fee = rate*getParkingDuration();
                    setParkingFee(fee);
                }
            }
        }
        else if(getVehicleType().equalsIgnoreCase("MOTORCYCLE")) //if vehicle type motorcycle
        {
            rate = 4;
            if(getParkingDuration()>2) 
            {
                if(((entryTime >= 6) && (entryTime < 10)) || (((entryTime >=15) && (entryTime < 19)))) //if entered during peak period 6-10am or 3pm-7pm
                {
                    rate = rate*1.30;
                    while(firstTwoHours<2)
                    {
                        peakTimeFee = peakTimeFee + rate; //adding a hourly amount for each of the first 2 hours
                        firstTwoHours++;
                    }
                    remainingParkingDuration = getParkingDuration() - 2; //getting remaining hours
                    rate = (rate/13)*9; //resetting rate + 10% discount for hours over 2
                    fee = (rate*remainingParkingDuration) + peakTimeFee; //rate of hours past 2nd hour multiplied by remaining hours after first 2 hours
                    setParkingFee(fee);
                }
                else
                {
                    rate = rate*0.95;
                    while(firstTwoHours<2)
                    {
                        peakTimeFee = peakTimeFee + rate; //adding a hourly amount for each of the first 2 hours
                        firstTwoHours++;
                    }
                    remainingParkingDuration = getParkingDuration() - 2; //getting remaining hours
                    rate = (rate/9.5)*9; //resetting rate + 10% discount for hours over 2
                    fee = (rate*remainingParkingDuration) + peakTimeFee; //rate of hours past 2nd hour multiplied by remaining hours after first 2 hours
                    setParkingFee(fee);
                }
            }
            else
            {
                if(((entryTime >= 6) && (entryTime < 10)) || (((entryTime >=15) && (entryTime < 19)))) //if entered during peak period 6-10am or 3pm-7pm
                {
                    rate = rate*1.30; //duration is only 2 hours or less here
                    fee = rate*getParkingDuration();
                    setParkingFee(fee);
                }
                else
                {
                    rate = rate*0.95; //5% discount for first 2 hours if entered outside peak times
                    fee = rate*getParkingDuration();
                    setParkingFee(fee);
                }
            }
        }
        else if(getVehicleType().equalsIgnoreCase("BICYCLE")) //if vehicle type bicycle
        {
            rate = 2;
            if(getParkingDuration()>2) 
            {
                if(((entryTime >= 6) && (entryTime < 10)) || (((entryTime >=15) && (entryTime < 19)))) //if entered during peak period 6-10am or 3pm-7pm
                {
                    rate = rate*1.30;
                    while(firstTwoHours<2)
                    {
                        peakTimeFee = peakTimeFee + rate; //adding a hourly amount for each of the first 2 hours
                        firstTwoHours++;
                    }
                    remainingParkingDuration = getParkingDuration() - 2; //getting remaining hours
                    rate = (rate/13)*9; //resetting rate + 10% discount for hours over 2
                    fee = (rate*remainingParkingDuration) + peakTimeFee; //rate of hours past 2nd hour multiplied by remaining hours after first 2 hours
                    setParkingFee(fee);
                }
                else
                {
                    rate = rate*0.95;
                    while(firstTwoHours<2)
                    {
                        peakTimeFee = peakTimeFee + rate; //adding a hourly amount for each of the first 2 hours
                        firstTwoHours++;
                    }
                    remainingParkingDuration = getParkingDuration() - 2; //getting remaining hours
                    rate = (rate/9.5)*9; //resetting rate + 10% discount for hours over 2
                    fee = (rate*remainingParkingDuration) + peakTimeFee; //rate of hours past 2nd hour multiplied by remaining hours after first 2 hours
                    setParkingFee(fee);
                }
            }
            else
            {
                if(((entryTime >= 6) && (entryTime < 10)) || (((entryTime >=15) && (entryTime < 19)))) //if entered during peak period 6-10am or 3pm-7pm
                {
                    rate = rate*1.30; //duration is only 2 hours or less here
                    fee = rate*getParkingDuration();
                    setParkingFee(fee);
                }
                else
                {
                    rate = rate*0.95; //5% discount for first 2 hours if entered outside peak times
                    fee = rate*getParkingDuration();
                    setParkingFee(fee);
                }
            }
        }
    }
}
