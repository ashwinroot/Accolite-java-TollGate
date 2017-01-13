
public class Vehicle {
	
		 private String ReqNo;
		//Vehicle type as in enum	 
		 private String TypeVehicle;    
	     public String getType(){
	        return TypeVehicle; 
	     }
	     public void setType(String v){
		        this.TypeVehicle = v;
		     }
	     
  
	     private String NameDriver;
	     private double balance;
	     private double cost;
	     public String lastStop;
	     
	     
	     //getter and setter method
	     public String getRegNo()
	     {
	    	 return this.ReqNo;
	     }     
	     public void setRegNo(String s)

	     {
	    	 this.ReqNo = s;
	    	 
	     }
	     public String getDriver()
	     {
	    	 return this.NameDriver;
	     }
	     public void setDriver(String n)
	     {
	    	 this.NameDriver = n;
	     }
	     public double getBalance()
	     {
	    	 return this.balance;
	     }
	     public void setBalance(double p)
	     {
	    	 this.balance = p;
	    	 System.out.println("");
	     }
	     
	     public void setCost(String x)
	     {
	    	 switch(x)
	    	 {
	    	 case "BUS":
	    		 this.cost = 0.3;
	    	 case "CAR":
	    		 this.cost = 0.2;
	    	 case "TRUCK":
	    		 this.cost = 0.5;
	    	 }
	    	 
	     }
	     public Vehicle(String number,String type, String name, double bal) {
			// TODO Auto-generated constructor stub
	    	 setRegNo(number);
	    	 setType(type);
	    	 setDriver(name);
	    	 setBalance(bal);
	    	 setCost(this.TypeVehicle);
	    	 System.out.println("Vehicle successfully created.");
		}
	    
	     public Vehicle() {
			// TODO Auto-generated constructor stub
		}
		public void printVehicle()
	     {
	    	 System.out.println("Number\t:"+getRegNo()+ "\nType\t:"+ getType() + "\nDriver\t:" + getDriver() + "\nBalance\t:" + getBalance() );
	    	 
	     }
	     public Vehicle getVehicle()
	     {
	    	 return this;
	     }
	     public void charge(double x)
	     {
	    	 this.balance -= x;
	    	 System.out.println("Current Balance is " + balance);
	    	 
	     }
	     public double getCost()
	     {
	    	 return this.cost;
	     }
	    
}
