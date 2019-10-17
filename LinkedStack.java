public class LinkedStack {
    
     private class Node {

        public Integer element;
        public Node next;

        public Node(Integer element) {
            this.element = element;
            next = null;
        }
    }
    
    private Node head;    
    private Node tail;
    private int count;

    public LinkedStack() {
        head = null;
        tail = null;
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

    public Integer top() {
        return trailer.prev.element;
    }

    public void push(Integer elem) {        
        trailer.prev.next = elem;
        ele.prev = trailer.prev;
        trailer.prev = elem;
        elem.next = trailer;
        count++;
    }

    public Integer pop() {
        Integer elem = trailer.prev.element;
        elem.prev.next = trialer;
        trailer.prev = elem.prev;
        count--;
        return elem;
    }

