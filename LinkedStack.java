public class LinkedStack {

    private Node header;
    private Node trailer;
    private int count;

    private class Node {
        public String element;
        public Node next;
        public Node prev;
        public Node(String e) {
            element = e;
            next = null;
            prev = null;
        }
    }

    public LinkedStack() {
        header = new Node("");
        trailer = new Node("");
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }

    public Integer size() {
        return count;
    }

    public Boolean isEmpty() {
        if(count == 0) {
            return true;
        }

        return false;
    }

    public void clear() {
        header = null;
        trailer = null;
        count = 0;
    }

    public String top() {
        return trailer.prev.element;
    }

    public void push(String element) {
        Node elem = new Node(element);
        trailer.prev.next = elem;
        elem.prev = trailer.prev;
        trailer.prev = elem;
        elem.next = trailer;
        count++;
    }

    public String pop() {
        Node elem = trailer.prev;
        elem.prev.next = trailer;
        trailer.prev = elem.prev;
        count--;
        return elem.element;
    }
}