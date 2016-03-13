package w4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by pwnst on 13.03.16.
 */
public class MxHeap<E extends Comparable<E>> {
    private E[] heap = (E[]) new Comparable[1];
    private int lastIndex;

    private void ensureCapacity() {
        if (lastIndex >= heap.length) {
            E[] newHeap = (E[]) new Comparable[heap.length * 2];
            for (int i = 0; i < heap.length; i++) {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }
    }

    private void swap(int index1, int index2) {
        E element = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = element;
    }



    private void swim(int index) {
        while (heap[index].compareTo(heap[(index-1)/2]) > 0) {
            if (index == 0) {
                break;
            }
            swap(index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    private boolean less(int index1, int index2) {
        if (heap[index1].compareTo(heap[index2]) < 1) {
            return true;
        }
        return false;
    }

    private void sink(int index) {
        while (index*2 < lastIndex - 1) {
            int compIndex = index * 2 + 1;
            if (compIndex < lastIndex - 2 && less(compIndex, compIndex+1)) {
                compIndex++;
            }
            if (!less(index, compIndex)) {
                break;
            }
            swap(index, compIndex);
            index = compIndex;
        }
    }

    public void add(E element) {
        ensureCapacity();
        heap[lastIndex++] = element;
        swim(lastIndex-1);
    }

    public void addNoSwim(E element) {
        ensureCapacity();
        heap[lastIndex++] = element;
    }

    public E removeMax() {
        E element = heap[0];
        swap(0, lastIndex-1);
        lastIndex--;
        sink(0);
        System.out.println(Arrays.toString(heap));
        return element;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lastIndex; i++) {
            sb.append(heap[i] + " ");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        MxHeap<Integer> a = new MxHeap<>();
        Scanner s = new Scanner("96 92 78 47 60 58 38 31 36 18");
        while (s.hasNext()) {
            a.addNoSwim(s.nextInt());
        }
        System.out.println(a);

/*        Scanner c = new Scanner("86 18 65");
        while (c.hasNext()) {
            a.add(c.nextInt());
        }*/
        a.removeMax();
        a.removeMax();
        a.removeMax();

        System.out.println(a);


    }

}
