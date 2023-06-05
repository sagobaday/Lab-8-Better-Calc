
public class LinkedList {
    private Node head, curr;
    private int size;

    public LinkedList() {
        curr = null;
        head = null;
        size = 0;
    }

    public void add(Object o) {
        if (head == null) {
            head = new Node(o, null, null);
            curr = head;
        } else {
            curr.setNext(new Node(o, null, curr));
            curr = curr.getNext();
        }

        size++;
    }

    public void removeLast() {
        if (head != null) {
            if (curr.getPrev() != null) {
                curr = curr.getPrev();
                curr.setNext(null);
            } else {
                head = null;
                curr = null;
            }

            size--;
        }
    }

    public Object getLast() {
        if (curr != null)
            return curr.getData();
        else
            return null;
    }

    public void clear() {
        head = null;
        curr = null;
    }

    public Object[] toArray() {
        if (head != null) {
            Object[] objects = new Object[size];
            objects[0] = head.getData();
            int i = 1;
            Node arrayCurr = head.getNext();

            while(arrayCurr != null) {
                objects[i] = arrayCurr.getData();
                arrayCurr = arrayCurr.getNext();
                i++;
            }

            return objects;
        }

        return null;
    }
}