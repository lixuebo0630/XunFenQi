package com.xunfenqi.net.network;

/**
 * @author Xuebo Li
 * 
 *         2015-7-27 上午10:57:57
 * 
 * @version
 * 
 * @description
 * 
 * @revise
 * 
 */

public class RSAUtil {

//	public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCOaSEKV1RhJFgJaEuiNZY6Wgt"
//			+ "KsFosrS92tqBYi6UqYYMIgxzxBgvas822fU+N/c2SVwO38ZFl5nUxkbhRM+pjEjF"
//			+ "KntWYXXlcM6/JNqgsOOdywtgFtffMcXrXKhKHorfpgIk1REzm4SIzdMZwLuXuukj"
//			+ "nZiffwHMsyIbS1hDzwIDAQAB";
	public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCS4vwj+2izd9WTKICiL9Ly4ohoX2uhLN6o1kKb"
			+ "Cni/VoiWV4x0nf03rWvOJC5QwNr1omkowpm8Jj4LowSg1BD/yQiyVGBaoolsLtELnYHhqpNCKftW"
			+ "IoJkSJ/Z4qMZ9X2f2CNTCmx5BATEy6/nCOcmeCAZ+aTPEzD5yunfeU5ufQIDAQAB";

	public static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALRmNW/DOFVkkfkT"
			+ "0UAfRDsI4NNjPpvBN5UQzTP32qeUr6cmkWB10E0nLcJO83zFRzpATYIdoSSi5Ih5"
			+ "XKliEUP3+9U6OguP+gkJsHLH0Z4FdkaDMLNzWEp0zMeFVfmtOvMrOAQlKLk0j7Tx"
			+ "fXZBCMeSeTyAUbjASikitrkizdqNAgMBAAECgYAKvleKW2b8duvWmJ6KohIPLf0t"
			+ "uahd4EjuIL0HzkgAWPOJEdvwkbOhFKc9/jO0B4+xYWj8ScVyCB1yH0AsekGq3/0W"
			+ "P+uGYnMIx8zoEvbFNthkqSQQVRX/QcAjZYvS1UPH5nyX1emJLlbAnxnyMqF722Ma"
			+ "JUmJmtTEXBnutlXzhQJBANrBiNcCO8PMUWB/G7blKjyMAwu7MF1cqWycHe4F2sm9"
			+ "tGzSsJJ6HAG9FlRE+TtNUyCdNR5UdrwCsbLCIh53P3sCQQDTHOXvRMdWjBZRplym"
			+ "WWL0CKoUspyGcvs8TwVwRaq40KpcPW0ewlnHliXOT45/St6UKoF8o8ymqDM7VSbG"
			+ "HWuXAkBseZ/TjoX4V67avO0PSqAhGgTAongfM73H37pvt3OAqpN3ixqH6AlPS2Zx"
			+ "Lyqvn25jM9GNUrD2Fhfr3pxwow1VAkEAu77ocdhd2ocs1iI4JSw7R0FpWIzbjOo2"
			+ "qhSwhn8+a6wOxYdPQtqek1PhfU/dLzE7LtN6YokdWF0farW4jNnfMwJAa8ZflVuj"
			+ "Gs/cdYAJJpzqb6WC/a0/UFZGF7zNTW8NeXfE24UW+4WMD8CehCo4EUl8tXAHoUSb"
			+ "b+B2T/wbd7G3gQ==";

	public static String RSAEncodeSign(String data) throws Exception {

		String sign = RSACoder.sign(data.getBytes(), privateKey);

		return NetWorkUtils.string2Json(sign);

	}

	/**
	 * 对结果进行验签
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static boolean verifyResult(String data, String signvalue) {

		try {

			return RSACoder.verify(data.getBytes(), publicKey, signvalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

}
