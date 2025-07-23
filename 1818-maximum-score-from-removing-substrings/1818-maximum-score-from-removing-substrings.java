class Solution {
    public int maximumGain(String s, int x, int y) {
        int totalScore = 0;
        String highPP =  x > y ? "ab" : "ba";
        String lowPP = highPP.equals("ab") ? "ba" : "ab";
 
        String stringAFP = removeSubstring(s, highPP);
        int removedPairsCount = (s.length() - stringAFP.length()) / 2;

        totalScore += removedPairsCount * Math.max(x, y);

        String stringASP = removeSubstring(stringAFP, lowPP);
        removedPairsCount = (stringAFP.length() - stringASP.length()) / 2;

        totalScore += removedPairsCount * Math.min(x, y);

        return totalScore;
    }

    private String removeSubstring(String input, String targetPair) {
        Stack<Character> charStack = new Stack<>();

        for(int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == targetPair.charAt(1) && !charStack.isEmpty() && charStack.peek() == targetPair.charAt(0)) {
                charStack.pop();
            } else {
                charStack.push(currentChar);
            }
        }

        StringBuilder remainingChars = new StringBuilder();
        while (!charStack.isEmpty()) {
            remainingChars.append(charStack.pop());
        } 
        return remainingChars.reverse().toString();
    }
}