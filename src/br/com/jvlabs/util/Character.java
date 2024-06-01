package br.com.jvlabs.util;

public enum Character {
	SPACE(" "),
	HYPHEN("-"),
	UNDERSCORE("_"),
	COLON(":"),
	COMMA(","),
	ELLIPSIS("..."),
	EXCLAMATIONMARK("!"),
	QUESTIONMARK("?"),
	SEMICOLON(";"),
	SLASH("/"),
	BACKSLASH("\\"),
	CARET("^"),
	DOT("."),
	MONEY("R\\$"),
	AMPERSAND("&"),
	EQUAL("="),
	EMPTY("");

	private Character(String character) {
		this.character=character;
	}

	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}

	private String character;
}
