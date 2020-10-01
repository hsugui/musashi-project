package blockchainclasses;

import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Base64;

public class StringUtil {
	//For SHA256 application to a String
	public static String applySha256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			//Application of SHA-256 to the input
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			
			StringBuffer hexString = new StringBuffer(); //Hash as hexidecimal
			
			for(int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				
				if(hex.length() == 1)
					hexString.append("0");
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//Applies ECDSA Signature and returns the result ( as bytes ).
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
		
		//Verifies a String signature 
		public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
			try {
				Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
				ecdsaVerify.initVerify(publicKey);
				ecdsaVerify.update(data.getBytes());
				return ecdsaVerify.verify(signature);
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		//Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"  
		public static String getDificultyString(int difficulty) {
			return new String(new char[difficulty]).replace('\0', '0');
		}

		public static String getStringFromKey(Key key) {
			return Base64.getEncoder().encodeToString(key.getEncoded());
		}
		
		//Tacks in array of transactions and returns a merkle root.
		public static String getMerkleRoot(ArrayList<Transaction> transactions) {
			int count = transactions.size();
			ArrayList<String> previousTreeLayer = new ArrayList<>();
			
			for(Transaction transaction : transactions) {
				previousTreeLayer.add(transaction.transactionId);
			}
			
			ArrayList<String> treeLayer = previousTreeLayer;
			
			while(count > 1) {
				treeLayer = new ArrayList<String>();
				for(int i = 1; i < previousTreeLayer.size(); i++) {
					treeLayer.add(applySha256(previousTreeLayer.get(i-1) + previousTreeLayer.get(i)));
				}
				
//alternative for the merkle root
//				for(int left=0; left < previousTreeLayer.size(); left += 2) {
//					 int right = Math.min(left + 1, previousTreeLayer.size() — 1);
//					 treeLayer.add(applySha256(previousTreeLayer.get(left) + previousTreeLayer.get(right)));
//					 }
				
				count = treeLayer.size();
				previousTreeLayer = treeLayer;
			}
			
			String merkleRoot = (treeLayer.size() == 1) ? treeLayer.get(0) : "";
			
			return merkleRoot;
		}
	
}