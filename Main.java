import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Main {


	public static void main(String[] args) {
				String myIP = "255.255.0.0";
                multisend sender = new multisend("224.0.0.0");
                multisend sender1 = new multisend(myIP);
                multirecive reciever = new multirecive(myIP);
                multirecive reciever1 = new multirecive("224.0.0.0");
                sender.start();
                reciever.start();
                sender1.start();
                reciever1.start();
               // serverSocketReceiver.start();
               // SocketSender s= new SocketSender(myIP);
                //s.start();

             
        
    

	}
}
