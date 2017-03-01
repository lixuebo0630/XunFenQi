package com.xunfenqi.net.network;


import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;


/**/

public class RSACoder extends Coder {

	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

	private static final String PUBLIC_KEY = "HHJRPULICKEYVALUE";
	private static final String PRIVATE_KEY = "HHJRPRIVATEKEY";

	/** */
	/**
	 * ��˽Կ����Ϣ�������ǩ��
	 * 
	 * @param data
	 *            �������
	 * @param privateKey
	 *            ˽Կ
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		// ������base64�����˽Կ
		byte[] keyBytes = decryptBASE64(privateKey);

		// ����PKCS8EncodedKeySpec����
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		// KEY_ALGORITHM ָ���ļ����㷨
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// ȡ˽Կ�׶���
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// ��˽Կ����Ϣ�������ǩ��
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);

		return encryptBASE64(signature.sign());
	}

	/** */
	/**
	 * У������ǩ��
	 * 
	 * @param data
	 *            �������
	 * @param publicKey
	 *            ��Կ
	 * @param sign
	 *            ����ǩ��
	 * 
	 * @return У��ɹ�����true ʧ�ܷ���false
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign)
			throws Exception {

		// ������base64����Ĺ�Կ
		byte[] keyBytes = decryptBASE64(publicKey);

		// ����X509EncodedKeySpec����
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		// KEY_ALGORITHM ָ���ļ����㷨
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// ȡ��Կ�׶���
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);

		// ��֤ǩ���Ƿ���
		return signature.verify(decryptBASE64(sign));
	}

	/** */
	/**
	 * ����<br>
	 * ��˽Կ����
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String key)
			throws Exception {
		// ����Կ����
		byte[] keyBytes = decryptBASE64(key);

		// ȡ��˽Կ
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// ����ݽ���
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/** */
	/**
	 * ����<br>
	 * �ù�Կ����
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key)
			throws Exception {
		// ����Կ����
		byte[] keyBytes = decryptBASE64(key);

		// ȡ�ù�Կ
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// ����ݽ���
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/** */
	/**
	 * ����<br>
	 * �ù�Կ����
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key)
			throws Exception {
		// �Թ�Կ����
		byte[] keyBytes = decryptBASE64(key);

		// ȡ�ù�Կ
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// ����ݼ���
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/** */
	/**
	 * ����<br>
	 * ��˽Կ����
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key)
			throws Exception {
		// ����Կ����
		byte[] keyBytes = decryptBASE64(key);

		// ȡ��˽Կ
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// ����ݼ���
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/** */
	/**
	 * ȡ�ù�Կ
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);

		// return encryptBASE64(key.getEncoded());
		return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCOaSEKV1RhJFgJaEuiNZY6Wgt"+
		"KsFosrS92tqBYi6UqYYMIgxzxBgvas822fU+N/c2SVwO38ZFl5nUxkbhRM+pjEjF"+
		"KntWYXXlcM6/JNqgsOOdywtgFtffMcXrXKhKHorfpgIk1REzm4SIzdMZwLuXuukj"+
		"nZiffwHMsyIbS1hDzwIDAQAB";
	}

	/**
	 * ȡ��˽Կ
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);

		// return encryptBASE64(key.getEncoded());

		return  "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMI5pIQpXVGEkWAl" +
		        "oS6I1ljpaC0qwWiytL3a2oFiLpSphgwiDHPEGC9qzzbZ9T439zZJXA7fxkWXmdTG" +
		        "RuFEz6mMSMUqe1ZhdeVwzr8k2qCw453LC2AW198xxetcqEoeit+mAiTVETObhIjN" +
		        "0xnAu5e66SOdmJ9/AcyzIhtLWEPPAgMBAAECgYEAvzwiUD6qM2lj+lcSrM7uvfyT" +
		        "JmXCqWPLsmOaYWpakuWXprqfqByb85QnEsroPaq9LiZtDfgnML5kKKxRfdxS2da3" +
		        "2O5xUxEquiLCHjHgNCyip6IxyQV6GcIbffLjl06tBYhyWOQC01HzWms8SPIkJB+u" +
				"18tIkRayni5T+hvk3QECQQDgqSc8C1bVGcdDvRWhee7/KZSrbM4IaNuYya5f87x9" +
				"y1oT4JQelMlWXMsH0pDFPIuRFTxoGn03dCnZWPK2aGsHAkEA3VGbHQ1JXIHnqO0Y" +
				"lLaNtzjYKf3+knO23qlWgtMbvg6YuA8GX2nZ17ISE1pmpeDW9H7xMrLFKaQmn7JG" +
				"CQoG+QJBAIub0kvX6A/pQxDvMb/02/YoysB0jtxe/QfHXNs3xcyOT93GU4VlhHBs" +
				"naawzVIkO77e6Xjv7RxGzbLJOZrs7IUCQQCnp6Ane98tEsNY9nL+hOQJ/ifSd7SX" +
				"YM0RE5qTdDS53vjVxZwAP4FkomKLAppr4k0z3c39p+svxkf0BeqEAnKRAkBkT4Tj" +
				"HCLKi39aPvTTwQQ+0tkTpqpdtjrALmFvNps57WwIAefxO9nBweYtgkz2ywoTJ5SH" +
				"Jf2Bmu8XCK90vo7M";
	}
	
	/**
	 * 苹果的公钥
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getOtherPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);

		// return encryptBASE64(key.getEncoded());
		return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0ZjVvwzhVZJH5E9FAH0Q7CODT"+
						"Yz6bwTeVEM0z99qnlK+nJpFgddBNJy3CTvN8xUc6QE2CHaEkouSIeVypYhFD9/vV"+
						"OjoLj/oJCbByx9GeBXZGgzCzc1hKdMzHhVX5rTrzKzgEJSi5NI+08X12QQjHknk8"+
						"gFG4wEopIra5Is3ajQIDAQAB";
	}
	
	/**
	 * 苹果的私钥
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getOtherPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		
		return  "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALRmNW/DOFVkkfkT" +
		        "0UAfRDsI4NNjPpvBN5UQzTP32qeUr6cmkWB10E0nLcJO83zFRzpATYIdoSSi5Ih5" +
		        "XKliEUP3+9U6OguP+gkJsHLH0Z4FdkaDMLNzWEp0zMeFVfmtOvMrOAQlKLk0j7Tx" +
		        "fXZBCMeSeTyAUbjASikitrkizdqNAgMBAAECgYAKvleKW2b8duvWmJ6KohIPLf0t" +
		        "uahd4EjuIL0HzkgAWPOJEdvwkbOhFKc9/jO0B4+xYWj8ScVyCB1yH0AsekGq3/0W" +
		        "P+uGYnMIx8zoEvbFNthkqSQQVRX/QcAjZYvS1UPH5nyX1emJLlbAnxnyMqF722Ma" +
				"JUmJmtTEXBnutlXzhQJBANrBiNcCO8PMUWB/G7blKjyMAwu7MF1cqWycHe4F2sm9" +
				"tGzSsJJ6HAG9FlRE+TtNUyCdNR5UdrwCsbLCIh53P3sCQQDTHOXvRMdWjBZRplym" +
				"WWL0CKoUspyGcvs8TwVwRaq40KpcPW0ewlnHliXOT45/St6UKoF8o8ymqDM7VSbG" +
				"HWuXAkBseZ/TjoX4V67avO0PSqAhGgTAongfM73H37pvt3OAqpN3ixqH6AlPS2Zx" +
				"Lyqvn25jM9GNUrD2Fhfr3pxwow1VAkEAu77ocdhd2ocs1iI4JSw7R0FpWIzbjOo2" +
				"qhSwhn8+a6wOxYdPQtqek1PhfU/dLzE7LtN6YokdWF0farW4jNnfMwJAa8ZflVuj" +
				"Gs/cdYAJJpzqb6WC/a0/UFZGF7zNTW8NeXfE24UW+4WMD8CehCo4EUl8tXAHoUSb" +
				"b+B2T/wbd7G3gQ==";
	}

	/** */
	/**
	 * ��ʼ����Կ
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {

		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);

		KeyPair keyPair = keyPairGen.generateKeyPair();

		// ��Կ
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		// ˽Կ
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap<String, Object>(2);

		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

}