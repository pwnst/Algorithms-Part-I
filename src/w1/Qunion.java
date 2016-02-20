package w1;

import java.util.Arrays;

/**
 * Created by pwnst on 05.02.16.
 */
public class Qunion {
    int[] list;
    int[] size;

    public Qunion(int n) {
        list = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = i;
            size[i] = 1;
        }
    }

    public int findRoot(int index) {
        while (list[index] != index) {
            index = list[index];
        }
        return index;
    }

    private boolean connected(int p, int q) {
        int pRoot = list[findRoot(p)];
        int qRoot = list[findRoot(q)];
        return pRoot == qRoot;
    }

    public String toString() {
        return Arrays.toString(list);
    }

    public void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if (size[pRoot] >= size[qRoot]) {
            size[pRoot] += size[qRoot];
            list[qRoot] = pRoot;
        }
        else {
            size[qRoot] += size[pRoot];
            list[pRoot] = qRoot;
        }
    }

    public static void main(String[] args) {
        Qunion qunion = new Qunion(10);
        String input = "6-9 8-2 5-9 0-6 9-1 5-4 3-7 8-7 4-3";
        String[] inputPairs = input.split("[ -]");
        for (int i = 0; i < inputPairs.length / 2; i++) {
            int first = Integer.decode(inputPairs[i*2]);
            int second = Integer.decode(inputPairs[i*2+1]);
            qunion.union(first, second);
        }
        System.out.println(qunion);

    }

}
