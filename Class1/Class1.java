package Class1;

public class Class1 {
    public int getDepth(int[][] m) {
        if (m.length == 0) {
            return 0;
        }

        int top = 0, bottom = m.length - 1;

        // Binary search will terminate when top and bottom are neighbour
        while(top + 1 < bottom) {
            int mid = top + (bottom - top) / 2;
            if (containsOne(m, mid)) {
                // mid might be the solution therefore top = mid + 1 is wrong
                top = mid;
            } else {
                // mid won't be the solution therefore bottom = mid - 1
                bottom = mid - 1;
            }
        }

        // post-processing
        // check bottom first because top might not be the solution
        return containsOne(m, bottom) ? bottom : top;

    }
    private boolean containsOne(int[][] m, int row) {
        for (int i = 0; i < m[row].length; i++) {
            if (m[row][i] == 1) {
                return true;
            }
        }

        return false;
    }
}
