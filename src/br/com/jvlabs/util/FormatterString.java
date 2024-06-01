package br.com.jvlabs.util;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.text.MaskFormatter;



public class FormatterString {

	private static String[] listImagem = {"jpg","jpeg","bmp","gif","png"};
	private String string;
	private List<String> listString;

	public FormatterString() {
		this.string = "";
		this.listString = new ArrayList<String>();
	}

	public String generateNamedUrl(String name) {
		return this.toString(name).removeAccents()
				.removeCharacters()
				.removeHtmlTags()
				.replace(" ", "-")
				.formatString()
				.toLowerCase();
	}

	public FormatterString(String string) {
		this.string = string;
		this.listString = new ArrayList<String>();
	}
	public FormatterString(List<String> string) {
		this.string = "";
		this.listString = string;
	}

	public void setString(String string){
		this.string = string;
	}
	public void setListString(List<String> strings){
		this.listString = strings;
	}

	public FormatterString removeMaskForFile() {
		string = string.replace("-", "");
		string = string.replace("(", "");
		string = string.replace(")", "");
		string = string.replace(":", "");
		string = string.replace("/", "");
		string = string.replace("%", "");
		string = string.replace(";", "");
		string = string.replace(" ", "");
		return this;
	}
	public FormatterString removeMask() {
		string = string.replace(".", "");
		string = string.replace("-", "");
		string = string.replace("(", "");
		string = string.replace(")", "");
		string = string.replace(":", "");
		string = string.replace("/", "");
		string = string.replace(" ", "");
		string = string.replace("[", "");
		string = string.replace("]", "");
		return this;
	}
	public FormatterString replace(String from, String to) {
		string = string.replace(from, to);
		return this;
	}

	public FormatterString convert(String from, String to){
		this.string = string.replace(from, to);
		return this;
	}

	public FormatterString upper(){
		if(this.string != null)
			this.string = this.string.toUpperCase();

		return this;
	}

	public FormatterString lower() {
		if (this.string != null)
			this.string = this.string.toLowerCase();
		return this;
	}


	public FormatterString removeAccents() {
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		string = string.replaceAll("[^\\p{ASCII}]", "");
		return this;
	}

	public FormatterString removeHtmlAccents() {
//		string = StringEscapeUtils.unescapeHtml(string);
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		string = string.replaceAll("[^\\p{ASCII}]", "");
		return this;
	}

	public FormatterString removeCharacters() {
	    String[] characters = {"\\.", ",","-", "%", ":",  ";", "\\(", "\\)", "�", "\\|", "\\\\", "�", "\\?", "&","/", "/", "!", "@", "`", "'", "\""};

	    for (int i = 0; i < characters.length; i++) {
	    	string = string.replaceAll(characters[i], "");
	    }

	    string = string.replaceAll("^\\s+", "");
	    string = string.replaceAll("\\s+$", "");
	    string = string.replaceAll("\\s+", " ");
		return this;
	}



	public FormatterString onlyDigits() {
		string = string.replaceAll("\\D", " ");
		return this.removeSpace();
	}
	public FormatterString formatLogin() {
		string = string.trim();
		int espacos = string.indexOf(" ");

		if(espacos > 0) {
			String[] split = string.split(" ");
			string = split[0] + split[split.length-1];
		}
		return this;
	}

	public FormatterString removeMaskEmailWae() {
		string = string.trim();

		int indexOfSpace = string.indexOf(" ");
		if(indexOfSpace>0) {
			string.substring(0, indexOfSpace);
		}
		return this;
	}

	public FormatterString toUTF8() {

		String utf8String = null;
		if (null != string && !string.equals("")) {
			try {
				byte[] stringBytesISO = string.getBytes("ISO-8859-1");
				utf8String = new String(stringBytesISO, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				utf8String = string;
			}
		} else {
			utf8String = string;
		}
		setString(utf8String);
		return this;
	}

	public FormatterString toISO88591() {
		String iso88591String = null;
		if (null != string && !string.equals("")) {
			try {
				byte[] stringBytesISO = string.getBytes("UTF-8");
				iso88591String = new String(stringBytesISO, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				iso88591String = string;
			}
		} else {
			iso88591String = string;
		}
		setString(iso88591String);
		return this;
	}

	public static String toI18n(Enum<?> en){
		ResourceBundle bundle = ResourceBundle.getBundle("messages");

		if (bundle.containsKey(en.getClass().getSimpleName()+"."+en)){
			return bundle.getString(en.getClass().getSimpleName()+"."+en);
		}else{
			return en.toString();
		}
	}

	public static String toI18n(String en){
		ResourceBundle bundle = ResourceBundle.getBundle("messages");

		if (bundle.containsKey(en)){
			return bundle.getString(en);
		}else{
			return en;
		}
	}

	public FormatterString removeSpace(){
		this.string = string.replaceAll(Character.SPACE.getCharacter(), Character.EMPTY.getCharacter());
		return this;
	}

	public static boolean isImagem(String nameImage) {
		String nameImageLower = nameImage.toLowerCase();
		String extensionImage = nameImageLower.substring(nameImageLower.indexOf(".")+1, nameImageLower.length());
		boolean isImage = false;
		for (String extension : listImagem){
			if(extension.equals(extensionImage)){
				isImage = true;
			}
		}
		return isImage;
	}

	public FormatterString splitString(String separador){
		StringTokenizer stringTokenizer = new StringTokenizer(string, separador);
		List<String> token = new ArrayList<String>();
		while(stringTokenizer.hasMoreTokens()){
			token.add((String)stringTokenizer.nextToken());
		}
		setListString(token);
		return this;

	}

	public FormatterString convertFirstCharacterToUpperCase() {
		string = string.toLowerCase();
		String wordNew = string.substring(0,1).toUpperCase();
		string = wordNew + string.subSequence(1, string.length());
		return this;
	}

	public FormatterString removeZeroInLeft() {
		this.string = string.replaceAll("^0*", "");
		return this;
	}

	public String formatString(){
		String retorno = this.string;
		return retorno;
	}

	public List<String> formatListString(){
		return this.listString;
	}
	public FormatterString toString(String novoValorStr) {
		string = novoValorStr;
		if(string == null)
			string = "";
		return this;
	}

	public FormatterString toListaDeString(List<String> listString) {
		this.listString = listString;
		return this;
	}

	public FormatterString removeCharacters(String character) {
		this.string = string.replaceAll(character, Character.EMPTY.getCharacter());
		return this;
	}


	public static String booleanToString(Boolean ativo) {
		if(ativo)
			return "T";
		else
			return "F";
	}

	public static String returnNomeFileToUrl(String url) {
		String nomeDoArquivo = url.substring(url.lastIndexOf("/") + 1, url.length());
		return nomeDoArquivo;
	}

	public FormatterString removeCharacteresToUrl() {
		if(string.contains("http://"))
			this.string = string.replaceAll("http://", Character.EMPTY.getCharacter());

		this.string = this.string.substring(0, this.string.indexOf("/"));

		if(string.contains(":8080"))
			this.string = string.replaceAll(":8080", Character.EMPTY.getCharacter());
		return this;
	}

    public FormatterString formatString( String mascara) {
		try {
			MaskFormatter mf = new MaskFormatter(mascara);
			mf.setValueContainsLiteralCharacters(false);
	        this.string = mf.valueToString(string);
		} catch (ParseException e) {}

        return this;
    }

    public String getNameWithoutExtension(String nome){
		return nome.substring(0, nome.lastIndexOf("."));
	}
	public static String getExtension(String nome){
		String extensao = nome.substring(nome.lastIndexOf("."));
		return extensao;
	}

	public static String format(String pattern, String value) {
		if(value == null || value.isEmpty())
			return "";

        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            return "";
        }
    }

	public FormatterString convertBrasPag(){
		String nossoNumeroAux = "";
		if (string.length() < 11) {
			for (int i = 0; i < 11 - string.length(); i++) {
				nossoNumeroAux += 0;
			}
			nossoNumeroAux += string;
		}
		nossoNumeroAux = nossoNumeroAux.replaceFirst("0", "5");
		setString(nossoNumeroAux);
		return this;
	}

	public FormatterString toSafeUTF8() {
		toUTF8();
		setString(string.replaceAll("/?", ""));
		return this;
	}
//	public FormatterString escapeHtml() {
//		string = StringEscapeUtils.escapeHtml(string);
//		return this;
//	}
//	public FormatterString unescapeHtml() {
//		string = StringEscapeUtils.unescapeHtml(string);
//		return this;
//	}
//	public FormatterString cpfWhithoutMask() {
//		string = StringUtils.leftPad(string, 11, '0');
//		return this;
//	}
	public FormatterString removeHtmlTags() {
		this.string = string.replaceAll("\\<[^>]*>", Character.EMPTY.getCharacter());
		return this;
	}
	public FormatterString trim() {
		string = string.trim();
		return this;
	}

	public static String removerCaracteres(String manipulacaoConteudo) {
		return new FormatterString().toString(manipulacaoConteudo).lower().removeCharacters().formatString();
	}

	public FormatterString capitalize() {
		String words = "";
		for (String word : this.string.toLowerCase().split(" ")) {
			if(word.length() < 3) {
				words += word;
			}else {
				words += word.substring(0,1).toUpperCase() + word.subSequence(1, word.length());
			}
			words += " ";
		}

		this.string = words.trim();
		return this;
	}
	public FormatterString limparVirgularAdicionais() {
		String words = "";
		for (String word : this.string.toLowerCase().split(",")) {
			if(!word.trim().isEmpty()) {
				if(word.endsWith(".")) {
					word = word.substring(0, word.length()-1);
				}
				words += word.trim() + " ";
			}
		}
		this.string = words.trim();
		return this;
	}
	public FormatterString gerarSenha() {
		String login = this.removeAccents().removeCharacters().lower().string;
		if(login.contains(" ")) {
			String[] split = login.toLowerCase().split(" ");
			login = split[0] + split[split.length-1];
		}
		this.string = login;
		return this;
	}

}
