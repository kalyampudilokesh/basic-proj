import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordGeneratorTest {
	
	private final Password testPassword = new Password("Secret");
	private final CharacterSet firstCharSet = new CharacterSet(true, false, false, false);
	private final CharacterSet secondCharSet = new CharacterSet(false, true, true, true);
	private final PasswordGenerator passwordGenerator = new PasswordGenerator(true, false, false, false);
	//	private final Password generatedPassword = passwordGenerator.createPassword(4);
	
	@Test
	void testPasswordToString() {
		assertEquals("Secret", testPassword.toString());
	}

	@Test
	void testFirstCharSet() {
		assertEquals(firstCharSet.getCharacterSet(), CharacterSet.UPPERCASE_CHARS);
	}

	@Test
	void testSecondCharSet() {
		assertEquals(secondCharSet.getCharacterSet(), CharacterSet.LOWERCASE_CHARS + CharacterSet.DIGITS + CharacterSet.SPECIAL_CHARS);
	}
	
	@Test
	void testPasswordGeneratorCharSet() {
		assertEquals(passwordGenerator.charSet.getCharacterSet(), CharacterSet.UPPERCASE_CHARS);
	}
	
	@Test
	void testPasswordGeneratorCharSetLength() {
		assertEquals(passwordGenerator.charSet.getCharacterSet().length(), 26);
	}
}
