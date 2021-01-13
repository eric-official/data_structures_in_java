// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package pvl7_group14;

public class PVL7_Group14 implements Graph_I {
    private int size;
    private int[][] matrix;
    private List<Integer> longestPath = new List<Integer>();
    private int count;
    private List<Integer> n_gons = new List<Integer>();

    public PVL7_Group14(int n) {
        this.size = n;
        matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    @Override
    public Boolean setEdge(int from, int to) {
        if (from == to || from > this.size || to > this.size || this.matrix[from][to] == 1) return false;
        this.matrix[from][to] = 1;
        this.matrix[to][from] = 1;
        return true;
    }

    @Override
    public List<List<Integer>> getEdges() {
        List<List<Integer>> res = new List<List<Integer>>();
        for (int i = 0; i < this.size; i++) {
            List<Integer> temp = new List<Integer>();
            for (int j = 0; j < this.size; j++) {
                if (matrix[i][j] != 0) {
                    temp.append(j);
                }
            }
            res.append(temp);
        }
        return res;
    }

    @Override
    public List<List<Integer>> getNGons(int n) {
        List<List<Integer>> res = new List<List<Integer>>();
        int[] used = new int[n + 2];
        for (int j = 0; j < n + 2; j++) {
            used[j] = -1;
        }
        for (int i = 0; i < this.matrix.length; i++) {
            tryngonathere(n, i, used, res);
        }
        return res;
    }


    private void tryngonathere(int len, int start, int[] used, List<List<Integer>> res) {
        int[] useme = used.clone();
        int place = 0;
        while (useme[place] != -1) {
            place++;
        }
        if (place > len) {
            return;
        }
        useme[place] = start;

        if (useme[len] == useme[0]) {
            List<Integer> in = new List<Integer>();
            for (int i = 0; i < len; i++) {
                in.append(useme[i]);
            }
            Node<List<Integer>> test = res.getFront();
            while (test != null) {
                List<Integer> neue = test.getData();
                boolean issame = false;
                issame = comparelists(in, neue);
                if (issame) {
                    return;
                }
                test = test.getNext();

            }
            res.append(in);
            return;
        }
        for (int h = 0; h < this.matrix.length; h++) {
            if (start == h) {
                continue;
            }
            if (this.matrix[start][h] == 1) {
                tryngonathere(len, h, useme, res);
            }
        }
    }

    private boolean comparelists(List<Integer> in, List<Integer> neue) {
        if (in.getSize() != neue.getSize()) {
            return false;
        } else {
            int[] proof = new int[in.getSize()];
            Node it = in.getFront();
            for (int i = 0; i < in.getSize(); i++) {
                proof[i] = (int) it.getData();
                it = it.getNext();
            }

            Node current = neue.getFront();
            for (int j = 0; j < neue.getSize(); j++) {
                int tester = (int) current.getData();
                if (isinarray(tester, proof) != true) {
                    return false;
                }
                current = current.getNext();
            }
            return true;
        }

    }

    private boolean isinarray(int tester, int[] proof) {
        for (int i = 0; i < proof.length; i++) {
            if (tester == proof[i]) {
                return true;
            }
        }
        return false;
    }

    public void setlongestpath(List<Integer> path) {
        if (path.size < longestPath.size) {
            return;
        }
        longestPath = path;
    }

    public void findlongestpath(int place, int end, List<Integer> path) {
        List<Integer> temp = path.deepCopy();
        if (place == end) {
            temp.append(place);
            setlongestpath(temp);
            return;
        } else {
            temp.append(place);

            if (temp.size > this.size) {
                return;
            } else {
                for (int i = 0; i < this.size; i++) {
                    if (temp.contains(i)) {
                        continue;
                    }
                    if (matrix[place][i] == 1) {
                        findlongestpath(i, end, temp);
                    }
                }
            }
        }
    }

    @Override
    public List<Integer> getLongestPath(int from, int to) {
        List<Integer> path = new List<Integer>();
        findlongestpath(from, to, path);
        return this.longestPath;
    }

    boolean isSafe(int v, int graph[][], int path[], int pos) {
        if (graph[path[pos - 1]][v] == 0)
            return false;

        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;

        return true;
    }

    boolean hamCycleUtil(int graph[][], int path[], int pos) {
        if (pos == this.size) {

            if (graph[path[pos - 1]][path[0]] == 1)
                return true;
            else
                return false;
        }

        for (int i = 1; i < this.size; i++) {

            if (isSafe(i, graph, path, pos)) {
                path[pos] = i;

                if (hamCycleUtil(graph, path, pos + 1) == true) {
                    return true;
                }
                path[pos] = -1;
            }
        }
        return false;
    }

    @Override
    public Boolean hasFullCircle() {
        int[] path = new int[this.size];
        for (int i = 0; i < this.size; i++)
            path[i] = -1;

        path[0] = 0;
        if (hamCycleUtil(matrix, path, 1) == false) {
            return false;
        }
        return true;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void printmatrix() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static void main(String[] args) {
        PVL7_Group14 graph = new PVL7_Group14(4);
        graph.setEdge(0, 1);
        graph.setEdge(0, 2);
        graph.setEdge(0, 3);
        graph.setEdge(1, 3);
        graph.setEdge(2, 3);

        graph.getLongestPath(2, 1).printContent();

        List<List<Integer>> me = graph.getEdges();
        Node<List<Integer>> hihi = me.getFront();
        while (hihi != null) {
            List<Integer> now = hihi.getData();
            now.printContent();
            hihi = hihi.getNext();
        }
    }
}
