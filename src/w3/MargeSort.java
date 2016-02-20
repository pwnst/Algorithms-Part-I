package w3;

import java.util.Arrays;

/**
 * Created by atretyak on 16.02.16.
 */
public class MargeSort {
    private static void sorting(Comparable[] a, Comparable[] aux, int low, int high) {
        if (high <= low) {return;}
        int mid = low + (high - low) / 2;
        sorting(a, aux, low, mid);
        sorting(a, aux, mid+1, high);
        marge(a, aux, low, high, mid);
    }

    public static void marge(Comparable[] a, Comparable[] aux, int low, int high, int mid) {
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }
        int i = low;
        int j = mid+1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            }
            else if (j > high) {
                a[k] = aux[i++];
            }

            else if (aux[j].compareTo(aux[i]) > 0) {
                a[k] = aux[i++];
            }
            else {
                a[k] = aux[j++];
            }
        }
    }

    public static void sort(Comparable[] array) {
        Comparable[] tmp = new Comparable[array.length];
        sorting(array, tmp, 0, array.length-1);

    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{63, 59, 34, 48, 84, 54, 43, 40, 16};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
