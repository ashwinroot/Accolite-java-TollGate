import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Done by Ashwin Sankar
 * 
 * Hello All,
 * This is the Accolite Java Assignment
 * **************************************************************
 * 
 * There was some modification to the input to provide scalability. It can take 
 * more than one vehicle for processing. Sample input will be attached for reference.
 * 
 * For the purpose, there are 'n' vehicle classes and each having values that can
 * be initialised with console. Once initialised, the rate of each vehicle is assigned.
 * Rate provides the way of calculating the toll cost at each stop(with the base provided at each stop)
 * 
 *  8 types of tollgates are initialised with names and base rates.
 *  
 *  
 *  The formula I used for calculation of cost at each stop(entry / exit):
 *  
 *  Total cost = Entry/Exit base fare * rate based on type + additional cost;
 *  
 *  additional cost = no of tolls crossed * 10;
 *  
 *  Once the app runs, we can view the files as belows:
 *  		
 *  	-	tollgate.txt : contains log at every tollgate
 *  	-   transaction.txt : contains the info in the format asked
 *  	-   vehicle.txt : contains the details of the vehicles.
 *  
 */
public class App{
	
	String TransitInfo;
	static int numofVeh;
	static TollGate[] toll = new TollGate[8];
	private static Scanner n;
	public  static Transaction tobj= new Transaction();
	public static void main(String[] args) throws IOException
	{
		getTollgates();
		Scanner n =new Scanner(System.in);
		System.out.println("Enter number of vehicles and the Vehicle details: (in the order) Vehicle Number, Type of Vehicle, Name of Driver, Balance:  ");
		numofVeh = n.nextInt();
		Vehicle[] v = new Vehicle[numofVeh];
		for(int i=0;i<numofVeh;i++)
		{
			v[i] = new Vehicle();
			getVehicle(v[i]);
		}
		
		System.out.println("Enter the number of transition information: ");
	    readTransitInfo(v,n.nextInt());
		writeAllLog(v);
		System.out.println("Please open the log files(vehicles,tollgates,transaction) to view the complete.");
	}
	
	public static void getVehicle(Vehicle v)
	{
		Scanner n = new Scanner(System.in);
		String input  = n.nextLine();
		String[] data = input.split("\\s+");
        v.setRegNo(data[0]);
        v.setType(data[1]);
        v.setDriver(data[2]);
        v.setBalance(Integer.valueOf(data[3]));
        v.setCost(v.getType());
        
        
        
		//tType = n.next();
		
		
		
	}
	public static void getTollgates()
	{
		
		toll[0] = new TollGate("EN1", 10);
		toll[1] = new TollGate("EN2", 20);
		toll[2] = new TollGate("EN3", 30);
		toll[3] = new TollGate("EN4", 40);
		toll[4] = new TollGate("EX1", 100);
		toll[5] = new TollGate("EX2", 200);
		toll[6] = new TollGate("EX3", 300);
		toll[7] = new TollGate("EX4", 400); 
		
		
	}
	public static void readTransitInfo(Vehicle[] v, int count)
	{
		
		for(int k=0;k<count ;k++)
		{
		n = new Scanner(System.in);
		String input  = n.nextLine();
		String[] data = input.split("\\s+");
		String number = data[0];
		String point1 = data[1];
		
		for(int i=0 ;i<numofVeh ;i++)
		{
			int addAmt = 0;
			if(v[i].getRegNo().equals(number))
			{
			for(int j=0 ; j<8 ; j++)
			{
				
				if(toll[j].name.equals(point1))
				{
					if(v[i].lastStop!=null)  // to check the null point exception
					{
					if(v[i].lastStop.charAt(1) == 'N')
					{
						addAmt = checkClockwise(v[i], toll[j]);
						//System.out.println(addAmt);
					}
					}
					
					double cost = toll[j].charge*v[i].getCost() + addAmt;
					double x= v[i].getBalance()-cost;
					if(x < 0 )
					{
						System.out.println("Postpaid Service acitivated. Hereafter, "+v[i].getDriver() + "owes the Road Department.");
					}
					v[i].setBalance(x);
					String s = v[i].getRegNo() + " Charged at "+ toll[j].name + " for " + cost + "(Extra amount is) "+addAmt+".\n";
					v[i].lastStop = point1;
					//System.out.println(s);
					toll[j].addLog(s);
					String p = "\n Commuter name: " + v[i].getDriver() + ", Balance deducted: " + cost + ", remaining balance: " + v[i].getBalance();
					
					tobj.addTransac("\n Commuter name: " + v[i].getDriver() + ", Balance deducted: " + cost + ", remaining balance: " + v[i].getBalance());
					}
					
				 }
				}
			
		}
		}
		System.out.println(tobj.sendTransaction());
	
		
	}
	
	static int checkClockwise(Vehicle v, TollGate t)
	{
		int diff = 0;
		if(v.lastStop == null)
		{
			System.out.println("The Vehicle is not in the highway");
			return 0;
			
		}
		else if(v.lastStop.charAt(1) == 'N')
		{
			int x = v.lastStop.charAt(2);
			int y = t.name.charAt(2);
			diff = y-x;
			if(diff<=0)
				diff += 4;
		}
		else
		{
			System.out.println("The last stop was exit. The vehicle is not on the highway");
		}
		return diff*10;
	}
	static void writeAllLog(Vehicle[] v) throws IOException
	{
		FileWriter f = new FileWriter("tolllog.txt");
		for(int i=0 ;i< 8 ;i++)
		{
			f.write("\nLog at TollGate "+ toll[i].name + "\n");
			f.write(toll[i].log.toString() + "\n\n");
			
		}
		f.close();
		FileWriter s =new FileWriter("vehicle.txt");
		s.write("Number of Vehicles: "+numofVeh +" \n");
		for(int i=0;i<numofVeh ;i++)
		{
			s.write(v[i].getRegNo() + " " + v[i].getType() + " " + v[i].getDriver() + " " +v[i].getBalance() + "\n");
		}
		s.close();
		
		FileWriter z =new FileWriter("transaction.txt");
		z.write(tobj.sendTransaction());
		z.close();
	}

}
