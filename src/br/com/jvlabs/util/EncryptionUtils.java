package br.com.jvlabs.util;

import java.security.MessageDigest;
import java.util.Random;

public class EncryptionUtils {

	private static final String SENHA_PADRAO_USUARIO = "abc123";

	public static String md5(String senha) {
		if(senha==null)
			return null;

		StringBuilder s = new StringBuilder();
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(senha.getBytes());
		    byte[] bytes = md.digest();

		    for (int i = 0; i < bytes.length; i++) {
		        int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
		        int parteBaixa = bytes[i] & 0xf;
		        if (parteAlta == 0) s.append('0');
		        s.append(Integer.toHexString(parteAlta | parteBaixa));
		    }
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	    return s.toString();
	}

	public static String md5Reventos(String senha) {
		byte buf[] = senha.trim().getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(buf);
			byte[] digest = algorithm.digest();
			for (int i = 0; i < digest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & ((int) digest[i])));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hexString.toString();
	}

    public static String gerarSenha(int len){
        char[] chart ={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        char[] senha= new char[len];

        int chartLenght = chart.length;
        Random rdm = new Random();

        for (int x=0; x<len; x++)
        senha[x] = chart[rdm.nextInt(chartLenght)];

        return new String(senha);
    }

	public static String senhaPadraoParaUser() {
		return md5(SENHA_PADRAO_USUARIO);
	}

}
