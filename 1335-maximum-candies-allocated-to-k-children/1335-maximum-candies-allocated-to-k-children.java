class Solution {
    public int maximumCandies(int[] candies, long k) {
        //p for pile
        //sp, number of candies each k can get = largest element in the array = 6.
        //cannot combine candies from multiple p.
        //upper bound = 6. start at 6 and go down to 0.
        //number of candies each child receive be x.
        //x=6, total number of candies < k(3)*6 = 18. total number of candies = 15. therefore, no valid distribution exist.

            int maxCandiesInPile = 0;
            for(int i = 0; i < candies.length; i++){
                maxCandiesInPile = Math.max(maxCandiesInPile, candies[i]);
            }
            int left = 0;
            int right = maxCandiesInPile;

            while(left < right){
                int mid = (left + right + 1) / 2;

                if(canAllocateCandies(candies, k, mid)){
                    left = mid;
                }else{
                    right = mid - 1;
                }
            }
            return left;
    }
    private boolean canAllocateCandies(int[] candies, long k, int numOfCandies){
        long maxNumOfChildren = 0;
        for(int pileIndex = 0; pileIndex < candies.length; pileIndex++){
            maxNumOfChildren += candies[pileIndex] / numOfCandies;
        }
        return maxNumOfChildren >= k;
    }
}