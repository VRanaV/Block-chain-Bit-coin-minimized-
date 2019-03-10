
public class implement {
	 public static void main ( String args[]){
		  Blockchain blockchain = new Blockchain(9);
		    blockchain.addBlock(blockchain.newBlock("Youssef"));
//		    blockchain.addBlock(blockchain.newBlock("loves"));
//		    blockchain.addBlock(blockchain.newBlock("Rana"));
		  
		   
		    
//		    System.out.println("Blockchain valid ? " + blockchain.isBlockChainValid());
	    System.out.println(blockchain);
		  
	  
	}
}
