class Solution {
    public boolean isValid(String word) {
        if (word.length() < 3) {
            return false;

        }

        boolean hasVowel = false;
        boolean hasConstonant = false;
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                char ch = Character.toLowerCase(c);
                if (
                    ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'
                ) {
                    hasVowel = true;
                }else {
                    hasConstonant = true;
                }
            } else if (!Character.isDigit(c)){
                return false;
            }
        }
        return hasVowel && hasConstonant;
    }
}