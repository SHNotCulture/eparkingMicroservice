package com.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;

public class _3DesUtil {
	/**
	 * 密钥算法
	 * @version 1.0
	 * @author
	 */
	public static final String KEY_ALGORITHM = "DESede";
		
	/**
	 * 加密/解密算法/工作模式/填充方式
	 * @version 1.0
	 * @author
	 */	
	public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

	//public static  final String base3DesKey = "OAtFeXbIFXn7Kopu/airl1fOCJj4NGv3";
	/**
	 * 转换密钥
	 * @param key 二进制密钥
	 * @return key 密钥
	 * 
	 */	
	public static Key toKey(byte[] key) throws Exception{
		//实例化DES密钥材料
		DESedeKeySpec dks = new DESedeKeySpec(key);
		//实例化秘密密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		//生成秘密密钥
		return keyFactory.generateSecret(dks);
	}
	public  static String encrypt(String param,String base3DesKey){
		BASE64Encoder encoder = new BASE64Encoder();
		BASE64Decoder decoder = new BASE64Decoder();
		try{

			byte[] bkey = decoder.decodeBuffer(base3DesKey);
			byte[] bEnc = encrypt(param.getBytes(), bkey);//3DES加密
			param = encoder.encode(bEnc);
		}
		catch (Exception e){

		}
		return  param;
	}
	public  static String decrypt(String param,String base3DesKey){
		BASE64Decoder decoder = new BASE64Decoder();
		try{
			byte[] bkey = decoder.decodeBuffer(base3DesKey);
			byte[] waitDecData= decoder.decodeBuffer(param);
			byte[] bDecResult =_3DesUtil.decrypt(waitDecData,bkey);//3DES解密
			param=new String(bDecResult);
		}
		catch (Exception e){

		}
		return  param;
	}
	/**
	 * 解密
	 * @param data 待解密数据
	 * @param key 密钥
	 * @return byte[] 解密数据
	 */	
	public static byte[] decrypt(byte[] data, byte[] key)throws Exception{
		//还原密钥
		Key k = toKey(key);
		/**
		 * 实例化
		 * 使用PKCS7Padding填充方式，按如下代码实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		//初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		//执行操作
		return cipher.doFinal(data);
	}


	
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception{
		//还原密钥
		Key k = toKey(key);
		/**
		 * 实例化
		 * 使用PKCS7Padding填充方式，按如下代码实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		//初始化，设置为加密密模式
		//cipher.init(Cipher.ENCRYPT_MODE, k);
		cipher.init(Cipher.ENCRYPT_MODE,  k);

		//执行操作
		return cipher.doFinal(data);
	}

	private static SecureRandom AlgorithmParameterSpec(int i, int j, int k,
			int l, int m, int n, int o, int p) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 生成密钥
	 * 
	 * @return byte[] 二进制密钥
	 */	
	public static byte[] initKey() throws Exception{
		/**
		 * 实例化
		 * 使用128位或192位长度密钥
		 * KeyGenerator.getInstance(KEY_ALGORITHM,"BC");
		 */
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		/**
		 * 初始化
		 *使用128位或192位长度密钥，按如下代码实现
		 *kg.init(128);
		 *kg.init(192);
		 */
		kg.init(168);
		//生成秘密密钥
		SecretKey secretKey = kg.generateKey();
		//获得密钥的二进制编码形式
		return secretKey.getEncoded();
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub  
//	   BASE64Decoder dec=new BASE64Decoder();
//	   BASE64Encoder enc = new BASE64Encoder();
		//byte [] initkey=initKey();
		//System.out.println(initkey.length);
	}
	
	//10进制数组转换16进制数组
	public static String printbytes(String tip, byte[] b) {
		String ret = "";
		String str;
		// System.out.println("b "+b);
		System.out.print(tip);
		for (int i = 0; i < b.length; i++) {

			str = Integer.toHexString((int) (b[i] & 0xff));

			if (str.length() == 1)
				str = "0" + str;
			System.out.print(str + " ");
			ret = ret + str + " ";
		}
		System.out.println();

		// 02 00 07 00 00 60 70 01 17 35 03 C8
		return ret;
	}	
}
