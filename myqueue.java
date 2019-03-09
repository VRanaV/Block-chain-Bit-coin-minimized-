import java.util.LinkedList;
import java.util.Queue;


public class myqueue {
    private Queue<String> queue = new LinkedList<String>();

    public myqueue(){

    }
        
    public void addToQueue(String x){
    	queue.add(x);
    }

    public String pop_up(){
        return queue.poll();
    }


}