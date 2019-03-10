import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Vector;

public class multirecive extends  Thread {
	
	protected byte[] buf = new byte[256];
	DatagramPacket packet = new DatagramPacket(buf, buf.length);
	private int port = 500;
	private String group= "224.0.0.0";
	protected MulticastSocket socket;
	//= new MulticastSocket(port);
    protected  byte[]data;
    private Vector<String> addresses = new Vector();
    private String IP;
    
    public multirecive(String ip)
    {
        this.IP=ip;
    }

    public void run() {

    	addresses.add(IP);
    	
      
        try {
            socket = new MulticastSocket(port);
            socket.joinGroup(InetAddress.getByName(group));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

       
        try {
            sleep(1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while (true)
        {
        	   System.out.println("Waiting to recive data:  ");
               
            try {
                socket.receive(packet);
                System.out.println("YES Recived data from:  " + packet.getAddress().toString()+ ":" + packet.getPort()+ 
                		".. with length .." + packet.getLength());
                

            } catch (IOException e) {
                e.printStackTrace();
            }

            data=new byte[packet.getLength()];
            System.arraycopy(packet.getData(), packet.getOffset(), data, 0, packet.getLength());

            String received = new String(data);
            System.out.println("IP Recived : " + received);


            System.out.println("ALL addresses IN SERVER JOINED");
            for(int i=0;i<addresses.size();i++)
            {
                System.out.println(addresses.get(i));
                System.out.println(addresses.get(i).length());
            }
            System.out.println("!!!!!");

            if(!addresses.contains(received))
            {
                addresses.add(received);
               // System.out.println("HERE");
                SocketSend socketSender = new SocketSend
                		(received);
                socketSender.start();
            }
        }
    }
}