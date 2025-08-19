class Solution {
    public long zeroFilledSubarray(int[] nums) {
       long streak = 0, count = 0;
       for (int num : nums){
        streak = (num == 0) ? streak + 1 : 0;
        count = count + streak;
       } 
       return count;
    }
}