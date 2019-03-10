import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.List;
public class CommonUtils {
	public static String applySha256(String input) {
	    if (input != null) {
	      MessageDigest digest = null;

	      try {
	        digest = MessageDigest.getInstance("SHA-256");
	      } catch (NoSuchAlgorithmException e) {
	        return null;
	      }
				
	      
	      final byte bytes[] = digest.digest(input.getBytes());
	      final StringBuilder builder = new StringBuilder();
				
	      for (final byte b : bytes) {
	        String hex = Integer.toHexString(0xff & b);

	        if (hex.length() == 1) {
	          builder.append('0');
	        }
					
	        builder.append(hex);
	      }
				
	      return builder.toString();
	    }
		  
	    return null;
	  }

	
		// Applies ECDSA Signature and returns the result ( as bytes ).
	public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
		Signature dsa;
		byte[] output = new byte[0];
		try {
			dsa = Signature.getInstance("ECDSA", "BC");
			dsa.initSign(privateKey);
			byte[] strByte = input.getBytes();
			dsa.update(strByte);
			byte[] realSig = dsa.sign();
			output = realSig;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return output;
	}

	public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
		try {
			Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
			ecdsaVerify.initVerify(publicKey);
			ecdsaVerify.update(data.getBytes());
			return ecdsaVerify.verify(signature);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

}