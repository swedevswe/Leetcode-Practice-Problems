

class Solution {
    int earliest = Integer.MAX_VALUE;
    int latest = Integer.MIN_VALUE;
    Set<String> seen = new HashSet<>();

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        List<Integer> players = new ArrayList<>();
        for (int i = 1; i <= n; i++) players.add(i);
        dfs(1, players, firstPlayer, secondPlayer);
        return new int[]{earliest, latest};
    }

    private void dfs(int round, List<Integer> players, int f, int s) {
        // Serialize state to avoid recomputation
        String key = round + ":" + players.toString();
        if (seen.contains(key)) return;
        seen.add(key);

        int size = players.size();
        for (int i = 0; i < size / 2; i++) {
            int a = players.get(i);
            int b = players.get(size - 1 - i);
            if ((a == f && b == s) || (a == s && b == f)) {
                earliest = Math.min(earliest, round);
                latest = Math.max(latest, round);
                return;
            }
        }

        List<List<Integer>> nextStates = generateNextRounds(players, f, s);
        for (List<Integer> next : nextStates) {
            next.sort(Comparator.naturalOrder());
            dfs(round + 1, next, f, s);
        }
    }

    private List<List<Integer>> generateNextRounds(List<Integer> players, int f, int s) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), players, 0, players.size() - 1, f, s);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> path, List<Integer> players, int left, int right, int f, int s) {
        if (left > right) {
            result.add(new ArrayList<>(path));
            return;
        }

        if (left == right) {
            path.add(players.get(left)); // free pass
            backtrack(result, path, players, left + 1, right, f, s);
            path.remove(path.size() - 1);
            return;
        }

        int p1 = players.get(left);
        int p2 = players.get(right);

        if ((p1 == f && p2 == s) || (p1 == s && p2 == f)) {
            // if they meet, don't continue this path
            return;
        }

        // If one of them is f or s, they must win
        if (p1 == f || p2 == f) {
            path.add(f);
            backtrack(result, path, players, left + 1, right - 1, f, s);
            path.remove(path.size() - 1);
        } else if (p1 == s || p2 == s) {
            path.add(s);
            backtrack(result, path, players, left + 1, right - 1, f, s);
            path.remove(path.size() - 1);
        } else {
            // try both players winning
            path.add(p1);
            backtrack(result, path, players, left + 1, right - 1, f, s);
            path.remove(path.size() - 1);

            path.add(p2);
            backtrack(result, path, players, left + 1, right - 1, f, s);
            path.remove(path.size() - 1);
        }
    }
}
    