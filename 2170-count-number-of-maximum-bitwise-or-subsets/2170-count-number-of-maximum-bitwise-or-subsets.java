class Solution {
    public int countMaxOrSubsets(int[] nums) {

        int maxOrV = 0;
        for (int num : nums) {
            maxOrV |= num;
        }

        return countSubsets(nums, 0, 0, maxOrV);


    }

    private int countSubsets(int[] nums, int index, int currentOr, int targetOr){
        if (index == nums.length) {
            return (currentOr == targetOr) ? 1 : 0;
        }

        int countWithout = countSubsets (nums, index + 1, currentOr, targetOr);

        int countWith = countSubsets(nums, index + 1, currentOr | nums[index], targetOr);

        return countWithout + countWith;
    }
}