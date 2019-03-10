import java.io.IOException;
import java.net.*;

public class multisend extends  Thread {
    private MulticastSocket socket;
    {
        try {
            socket = new MulticastSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String group="224.0.0.0"; //INEtADDRESSSSS
 
    private byte[] mybuffer;
    private int port = 500;
    private String myIP;

    multisend(String ip)
    {
    	
    	//System.out.println("HERE");
        myIP = ip;
        port = 500;
    }

    public void run() {
    
        try {
            socket.joinGroup(InetAddress.getByName(group));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                mybuffer = new byte[256];
                mybuffer = myIP.getBytes();


                DatagramPacket packet;
                packet = new DatagramPacket(mybuffer, mybuffer.length, InetAddress.getByName(group), port);
                socket.send(packet);
                System.out.println("Data is sent!!")
                ;

                try {
                    sleep(6500);
                } catch (InterruptedException e) {}

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
