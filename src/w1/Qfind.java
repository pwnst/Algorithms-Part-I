package w1;

import java.util.Arrays;

/**
 * Created by pwnst on 05.02.16.
 */
public class Qfind {
    int[] list;

    public Qfind(int n) {
        list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = i;
        }
    }

    public String toString() {
        return Arrays.toString(list);
    }

    public void union(int p, int q) {
        int pid = list[p];
        int qid = list[q];
        for (int i = 0; i < list.length; i++) {
            if (list[i] == pid) {
                list[i] = qid;
            }
        }
    }

    public static void main(String[] args) {
        Qfind qfind = new Qfind(10);
        String input = "1-3 3-5 2-6 1-9 4-9 3-0";
        String[] inputPairs = input.split("[ -]");
        for (int i = 0; i < inputPairs.length / 2; i++) {
            int first = Integer.decode(inputPairs[i*2]);
            int second = Integer.decode(inputPairs[i*2+1]);
            qfind.union(first, second);
        }
        System.out.println(qfind);
    }
}
