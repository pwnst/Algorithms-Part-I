package w3;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by pwnst on 20.02.16.
 */
public class Quick3{
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
        sort(array, 0, array.length -1);
    }

    public static void sort(Comparable[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int l = lo;
        int i = lo;
        int j = hi;
        Comparable v = array[lo];
        while (i < j) {
            int comp = array[i].compareTo(v);
            if (comp > 0) {
                swap(array, i, j--);
            }
            else if (comp < 0) {
                swap(array, l++, i++);
            }
            else {
                i++;
            }
        }
        sort(array, lo, l-1);
        sort(array, j+1, hi);
    }

    public static void main(String[] args) {
        Integer[] d = new Integer[]{2,1,12,44,22,12,3,4,6,7,88,1};
        sort(d);
        System.out.println(Arrays.toString(d));
    }
}
