class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
       
 
        Arrays.sort(players);
        Arrays.sort(trainers);
        
        int i = 0, j = 0, matches = 0;

        while (i < players.length && j < trainers.length) {
            if (players[i] <= trainers[j]) {
                matches++;
                i++;
                j++;
            } else {
                j++; // try a stronger trainer
            }
        }

        return matches;
    }
}
