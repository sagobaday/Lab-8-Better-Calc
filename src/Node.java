
public class Node {
    private Object data;
    private Node next, prev;

    public Node(Object data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getPrev() {
        return prev;
    }

    public Object getData() {
        return data;
    }
}
