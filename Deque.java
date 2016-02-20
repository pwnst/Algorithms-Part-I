import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pwnst on 13.02.16.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    // construct an empty deque
    public Deque() {
        first = new Node<>();
        last = new Node<>();
        first.setPrev(last);
        last.setNext(first);
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        Node node = new Node(item);
        node.setNext(first);
        node.setPrev(first.getPrev());
        first.getPrev().setNext(node);
        first.setPrev(node);
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        Node node = new Node(item);
        node.setPrev(last);
        node.setNext(last.getNext());
        last.getNext().setPrev(node);
        last.setNext(node);
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Node<Item> removeNode = first.getPrev();
        removeNode.getPrev().setNext(first);
        first.setPrev(removeNode.getPrev());
        size--;
        return removeNode.getData();
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Node<Item> removeNode = last.getNext();
        removeNode.getNext().setPrev(last);
        last.setNext(removeNode.getNext());
        size--;
        return removeNode.getData();

    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        if (this.isEmpty()) {
            throw new NullPointerException("Deque is empty");
        }
        Iterator<Item> itemIterator = new Iterator<Item>() {
            Node<Item> current = first.getPrev();
            @Override
            public boolean hasNext() {
                return current != last;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No next element");
                }
                Item item = current.getData();
                current = current.getPrev();
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
        Deque<String> deque = new Deque<>();
        deque.addFirst("a");
        deque.removeLast();
        deque.removeLast();
        /*for (String item : deque) {
            System.out.println(item);
        }*/

    }

    private class Node<Item> {
        private Node<Item> next;
        private Node<Item> prev;
        private Item data;

        public Node() {
        }

        public Node(Item data) {
            this.data = data;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private Item getData() {
            return data;
        }

        private void setPrev(Node prev) {
            this.prev = prev;
        }

        private Node getNext() {
            return next;
        }

        private Node getPrev() {
            return prev;
        }
    }
}
