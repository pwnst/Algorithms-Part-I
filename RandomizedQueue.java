import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pwnst on 13.02.16.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] list;
    private int index;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        list = (Item[]) new Object[1];
        size = 0;
        index = -1;
    }

    private void increaseSize() {
        Item[] newList = (Item[]) new Object[list.length*2];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    private void decreaseSize() {
        Item[] newList = (Item[]) new Object[list.length/4*3];
        for (int i = 0; i < newList.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    private void swap(int index1, int index2) {
        Item tmp = list[index1];
        list[index1] = list[index2];
        list[index2] = tmp;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        if (size() == list.length) {
            increaseSize();
        }
        list[index+1] = item;
        size++;
        index++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        if (size() < list.length/4*3) {
            decreaseSize();
        }
        int randomIndex = StdRandom.uniform(size());
        swap(index, randomIndex);
        Item item = list[index];
        list[index] = null;
        index--;
        size--;
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        int randomIndex = StdRandom.uniform(size());
        return list[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        if (this.isEmpty()) {
            throw new NullPointerException("Randomized Queue is empty");
        }
        Iterator<Item> itemIterator = new Iterator<Item>() {
            int iterIndex = -1;
            @Override
            public boolean hasNext() {
                return list[iterIndex+1] != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No next element");
                }
                Item item = list[iterIndex+1];
                iterIndex++;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove operations is not supported");
            }
        };
        return itemIterator;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("a");
        randomizedQueue.enqueue("b");
        randomizedQueue.enqueue("c");
        randomizedQueue.enqueue("d");
        randomizedQueue.enqueue("e");
        for (String s : randomizedQueue) {
            System.out.println(s);
        }
        System.out.println(randomizedQueue.size());
        System.out.println(randomizedQueue.isEmpty());
        System.out.println(randomizedQueue.dequeue());
/*        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());*/
        System.out.println(randomizedQueue.size());
        for (String s : randomizedQueue) {
            System.out.println(s);
        }
    }
}
