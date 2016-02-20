package w3;

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

    public static void partition(Comparable[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int i = lo+1;
        int j = hi;
        int k = lo;
        while (j >= i) {
            while (less(array[i], array[k])) {
                i++;
            }
            while (less(array[k], array[j])) {
                j--;
            }
            swap(array, i, j);
        }
        swap(array, k, i);
        partition(array, k, i);
        partition(array, i+1, j);
    }

    private static boolean less(Comparable a, Comparable b) {
        if (a.compareTo(b) < 0) {
            return true;
        }
        return false;
    }

    public static void sort(Comparable[] array) {
        shuffle(array);
        partition(array, 0, array.length-1);
    }

    public static void main(String[] args) {
        Integer[] c = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12};
        Integer[] a = new Integer[]{1};
        Integer[] b = new Integer[]{2,1};
        sort(b);
        //System.out.println(less(c[1], c[2]));
    }
}
