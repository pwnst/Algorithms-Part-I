package w2;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by pwnst on 13.02.16.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.decode(args[0]);
        String[] input = StdIn.readAllStrings();

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        for (int i = 0; i < input.length; i++) {
            randomizedQueue.enqueue(input[i]);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}
