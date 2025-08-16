class Solution {
    public int maximum69Number (int num) {
        int n = num, pos = 0, idx = -1;
        while (n > 0) {
            int d = n % 10;
            if (d == 6) idx = pos;
            n = n / 10;
            pos++;
        }
        if (idx != -1) {
            return num + 3 * (int)Math.pow(10, idx);
        }
        return num;
    }
}