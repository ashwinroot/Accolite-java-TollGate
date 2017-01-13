
public class Transaction {
   public static StringBuffer transacHistory = new StringBuffer();
   void addTransac(String s)

  {
	  transacHistory.append(s);
  }
   String sendTransaction()
   {
	   return transacHistory.toString();
   }

}
