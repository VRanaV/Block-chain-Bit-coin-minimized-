import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class NmCoin {
		private static List<Block> blockchain = new ArrayList<Block>();

	private static Block unpackBlock ;
	public static Map<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>();
	
	public static final int difficulty = 3;

	

	//public static final float minimumTransaction = 0.1f;
	public static wallet walletA;
	public static wallet walletB;
	//public static Transaction genesisTransaction;
	

	public static void main(String[] args) throws IOException, InterruptedException {
		}
	}
		