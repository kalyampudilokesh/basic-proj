public class Password {
    private String value;
    private int length;

    public Password(String value) {
        this.value = value;
        this.length = value.length();
    }

    private int getCharType(char c) {
        int charType;

        // Char is Uppercase Letter
        if (c >= 'A' && c <= 'Z') {
            charType = 1;
        }
        // Char is Lowercase Letter
        else if (c >= 'a' && c <= 'z') {
            charType = 2;
        }
        // Char is Digit
        else if (c >= '0' && c <= '9') {
            charType = 3;
        }
        // Char is Symbol
        else {
            charType = 4;
        }

        return charType;
    }

    private int calculatePasswordStrength() {
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;
        int score = 0;

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            int type = getCharType(c);

            if (type == 1) hasUppercase = true;
            if (type == 2) hasLowercase = true;
            if (type == 3) hasDigit = true;
            if (type == 4) hasSymbol = true;
        }

        if (hasUppercase) score += 1;
        if (hasLowercase) score += 1;
        if (hasDigit) score += 1;
        if (hasSymbol) score += 1;

        if (value.length() >= 8) score += 1;
        if (value.length() >= 16) score += 1;

        return score;
    }

    public String calculateScore() {
        int score = calculatePasswordStrength();

        if (score == 6) {
            return "This is a very good password :D check the Useful Information section to make sure it satisfies the guidelines";
        } else if (score >= 4) {
            return "This is a good password :) but you can still do better";
        } else if (score >= 3) {
            return "This is a medium password :/ try making it better";
        } else {
            return "This is a weak password :( definitely find a new one";
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
