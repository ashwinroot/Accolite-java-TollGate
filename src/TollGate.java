
public class TollGate {
		String name;
		StringBuffer log =new StringBuffer();
		int charge;
		
		public TollGate(String name, int charge) {
			this.name = name;
			this.charge = charge;
			
		}
		
		void addLog(String msg)
		{
			log.append(msg);
		}
		void printLog()
		{
			System.out.println("Log content of "+ name + ":\n"+ log.toString());
		}
		int getCharge(String Name)
		{
			if(this.name == Name)
				return charge;
			else
				return 0;
		}
}
