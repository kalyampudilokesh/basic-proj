


public class CharacterSet {
	
	public static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
	public static final String DIGITS = "1234567890";
	public static final String SPECIAL_CHARS = "!@#$%^&*()-_=+\\/~?";
	
	private final StringBuilder characterPool;

	public CharacterSet(boolean includeUppercase, boolean includeLowercase, boolean includeDigits, boolean includeSpecialChars) {
		
		characterPool = new StringBuilder();
		
		if (includeUppercase) characterPool.append(UPPERCASE_CHARS);
		
		if (includeLowercase) characterPool.append(LOWERCASE_CHARS);
		
		if (includeDigits) characterPool.append(DIGITS);
		
		if (includeSpecialChars) characterPool.append(SPECIAL_CHARS);
		
	}
	
	public String getCharacterSet() {
		return characterPool.toString();
	}
}

