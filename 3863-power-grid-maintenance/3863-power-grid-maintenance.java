class DSU {
    
    private int[] parent;

    public DSU(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++){
            parent[i] = i;
        }
    }

    public int find(int x){
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    public void join(int u, int v){
        parent[find(v)] = find(u);
    }

}

class Solution {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
         
         //c = 5, connections = [[1,2], [2,3], [3,4], [4,5]], queries = [[1,3], [2,1], [1,1], [2,2], [1,2]]

         //o/p = [3,2,3]

         //union find with reverse processing query.
         //union find data structure to calculate connected components - familiarized with principles, implementation, and path compression optimization of U-F.
         //merge nodes that have a connected relationship, then use the root node of each set as a handle of the connected component. 
         //query operation of the u-f determine which connected component a particular node belongs to.
         //maintaining the minimum identifier of the currently online power stations.
         //processing queries in reverse order.
         //pre-process the final state of each power plant's online status - checking whether this is the first time the power plant was taken offline, and only then make the corresponding adjustments.


         //for each power plant i, we can count the number of times it is taken offline, using offlineCounti.
         //reverse process the queries, decrement corresponding offlineCounti by one every time we encounter an offline operation.
         //power plant s satisfies offlineCounts = 1, this gives the moment when the power plant was first taken offline in the original order of operations.


        DSU dsu = new DSU(c + 1);

        for (int[] p : connections) {
            dsu.join(p[0], p[1]);
        }

        boolean[] online = new boolean[c + 1];
        int[] offlineCounts = new int[c + 1];
        Arrays.fill(online, true);
        Map<Integer, Integer> minimumOnlineStations = new HashMap<>();
        for (int[] q : queries) {
            int op = q[0];
            int x = q[1];
            if (op == 2){
                online[x] = false;
                offlineCounts[x]++;
            }
        }
        for (int i = 1; i <= c; i++){
            int root = dsu.find(i);
            if (!minimumOnlineStations.containsKey(root)){
                minimumOnlineStations.put(root, -1);
            }

            int station = minimumOnlineStations.get(root);
            if (online[i]) {
                if (station == -1 || station > i){
                    minimumOnlineStations.put(root, i);
                }
            }
        }

        List <Integer> ans = new ArrayList<>();
        for (int i = queries.length - 1; i >= 0; i--){
            int op = queries[i][0];
            int x = queries[i][1];
            int root = dsu.find(x);
            int station = minimumOnlineStations.get(root);

            if (op == 1){
                if (online[x]) {
                    ans.add(x);
                } else {
                    ans.add(station);
                }
            }

            if (op == 2){
                if (offlineCounts[x] > 1){
                    offlineCounts[x]--;
                } else {
                    online[x] = true;
                    if (station == -1 || station > x){
                        minimumOnlineStations.put(root, x);
                    }
                }
            }
        }

        Collections.reverse(ans);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}