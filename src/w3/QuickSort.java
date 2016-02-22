package w3;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by atretyak on 19.02.16.
 */
public class QuickSort {
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

    public static int partition(Comparable[] array, int lo, int hi) {
        int i = lo;
        int j = hi+1;
        while (true) {
            while (less(array[++i], array[lo])) {
                if (i == hi) {
                    break;
                }
            }
            while (less(array[lo], array[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(array, i, j);
        }
        swap(array, lo, j);
        return j;
    }

    private static boolean less(Comparable a, Comparable b) {
        if (a.compareTo(b) < 0) {
            return true;
        }
        return false;
    }

    public static void sort(Comparable[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(array, lo, hi);
/*        sort(array, lo, j-1);
        sort(array, j+1, hi);*/
    }

    public static void sort(Comparable[] array) {
        shuffle(array);
        sort(array, 0, array.length-1);
    }

    public static void main(String[] args) {
        Integer[] c = new Integer[]{1,2,22,33,3,4,5,6,7,8,9,10,11,12};
        Integer[] a = new Integer[]{1};
        Integer[] b = new Integer[]{2,1};
        String[] d = "B A B B A A B B B B B B".split(" ");
        sort(d);
        System.out.println(Arrays.toString(d));
    }
}
