package w3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

/**
 * Created by atretyak on 22.02.16.
 */
public class Quick3 {
    private static void swap(Comparable[] array, int a, int b) {
        Comparable tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    private static void shuffle(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            Random r = new Random();
            int index = r.nextInt(array.length-i);
            swap(array, i, index+i);
        }
    }

    public static void sort(Comparable[] array) {
        shuffle(array);
        sort(array, 0, array.length-1);
    }

    public static void sort(Comparable[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo;
        int i = lo;
        int gt = hi;
        Comparable v = array[lo];
        while (i <= gt) {
            if (array[i].compareTo(v) > 0) {
                swap(array, i, gt--);
            }
            else if (array[i].compareTo(v) < 0) {
                swap(array, i++, lt++);
            }
            else {
                i++;
            }
        }
        sort(array, lo, lt-1);
        sort(array, gt+1, hi);
    }

    public static void main(String[] args) {
        Integer[] c = new Integer[]{1,2,22,33,3,4,5,6,7,8,9,10,11,12};
        Integer[] a = new Integer[]{1};
        Integer[] b = new Integer[]{2,1};
        sort(c);
        System.out.println(Arrays.toString(c));
    }
}
