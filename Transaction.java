import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
	
	public String transactionId; 
	public PublicKey sender; 
	public PublicKey reciepient; 
	public float value; 
	public byte[] signature; 
	
	public List<TransactionInput> inputs = new ArrayList<TransactionInput>();
	public List<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
	private static int sequence = 0; 
	
	
	public Transaction(PublicKey from, PublicKey to, float value,  List<TransactionInput> inputs) {
		this.sender = from;
		this.reciepient = to;
		this.value = value;
		this.inputs = inputs;
	}
	
	public boolean processTransaction() {
		
		if(verifySignature() == false) {
			System.out.println("#Transaction Signature failed to verify");
			return false;
		}
				
		//Gathers transaction inputs (Making sure they are unspent):
		for(TransactionInput i : inputs) {
			i.UTXO = NmCoin.UTXOs.get(i.transactionOutputId);
		}

		//Checks if transaction is valid:
		if(getInputsValue() < NmCoin.minimumTransaction) {
			System.out.println("Transaction Inputs too small: " + getInputsValue());
			//System.out.println("Please enter the amount greater than " + NmCoin.minimumTransaction);
			return false;
		}
		
		//Generate transaction outputs:
		float leftOver = getInputsValue() - value; //get value of inputs then the left over change:
		transactionId = calulateHash();
		outputs.add(new TransactionOutput( this.reciepient, value,transactionId)); //send value to recipient
		outputs.add(new TransactionOutput( this.sender, leftOver,transactionId)); //send the left over 'change' back to sender		
				
		//Add outputs to Unspent list
		for(TransactionOutput o : outputs) {
			NmCoin.UTXOs.put(o.id , o);
		}
		
		//Remove transaction inputs from UTXO lists as spent:
		for(TransactionInput i : inputs) {
			if(i.UTXO == null) continue; //if Transaction can't be found skip it 
			NmCoin.UTXOs.remove(i.UTXO.id);
		}
		
		return true;
	}
	
	public float getInputsValue() {
		float total = 0;
		for(TransactionInput i : inputs) {
			if(i.UTXO == null) continue; //if Transaction can't be found skip it, This behavior may not be optimal.
			total += i.UTXO.value;
		}
		return total;
	}
	
	public void generateSignature(PrivateKey privateKey) {
		String data = CommonUtils.getStringFromKey(sender) + CommonUtils.getStringFromKey(reciepient) + Float.toString(value)	;
		signature = CommonUtils.applyECDSASig(privateKey,data);		
	}
	
	public boolean verifySignature() {
		String data = CommonUtils.getStringFromKey(sender) + CommonUtils.getStringFromKey(reciepient) + Float.toString(value)	;
		return CommonUtils.verifyECDSASig(sender, data, signature);
	}
	
	public float getOutputsValue() {
		float total = 0;
		for(TransactionOutput o : outputs) {
			total += o.value;
		}
		return total;
	}
	
	private String calulateHash() {
		sequence++; 
		return CommonUtils.applySha256(
				CommonUtils.getStringFromKey(sender) +
				CommonUtils.getStringFromKey(reciepient) +
				Float.toString(value) + sequence
				);
	}
}