package chat_Application;

import java.security.*;
import java.util.logging.*;
import javax.crypto.Cipher;
import javax.crypto.spec.*;
import java.util.Base64;
import java.util.Scanner;
public class AESExample {
	private static final String Algo = "AES";
	private static byte[] keyValue;

	public AESExample(String key ) 
	{
		keyValue = key.getBytes();
		
	}
	public String encrypt(String Data) throws Exception 
	{
		Key key = generateKey();
		Cipher c = Cipher.getInstance(Algo);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte [] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}
	public String decrypt(String encryptedValue) throws Exception 
	{
		
		Key key = generateKey();
		Cipher c = Cipher.getInstance(Algo);
		c.init(Cipher.DECRYPT_MODE, key);
		byte [] decoderValue =  Base64.getDecoder().decode(encryptedValue);
		byte[] decValue = c.doFinal(decoderValue);
		return  new String(decValue);
	}
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue,Algo);
        return new SecretKeySpec(keyValue, Algo);
    }

}
