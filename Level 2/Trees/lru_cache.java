import java.util.*;

//Assumptions MRU(Tail);
//LRU(Head);

public class LRUCache {
    private class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node() {}

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    Map<Integer, Node> map = new HashMap<>();

    private Node head = null;
    private Node tail = null;
    private int capacity = 0;
    private int size = 0;

    public LRUCache(int capacity) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.capacity = capacity;
    }

    private void removeNode(Node node) {
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            if (node == this.head) {
                Node next = this.head.next;
                next.prev = null;
                this.head = next;
            } else if (node == this.tail) {
                Node pre = this.tail.prev;
                pre.next = null;
                this.tail = pre;
            } else {
                Node pre = node.prev;
                Node next = node.next;
                pre.next = next;
                next.prev = pre;
            }
            //in mostRecent() tail case is handled when tail==node
            //but still check for tail
            node.next = null;
            node.prev = null;

        }
        this.size--;
    }

    private void addLast(Node node) {
        if (this.size == 0) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
        this.size++;
    }

    private void mostRecent(Node node) {
        if (this.size == 1) {
            return;
        }

        removeNode(node);
        addLast(node);
    }
    //return value/state(node.val), (ii) make recent
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            mostRecent(node);
            return node.val;
        } else {
            return -1;
        }
    }

    // if
    //update val in Node also in map , make it most recent
    //remove() from the place , and add at Tail(MRU) i.e addLast();
    //else
    //not contains , make new Node , add to DLL
    // (i) if DLL is empty , DLL is == CAP ,
    //if size==cap head is LRU , remove(head);
    // (ii) add K:V to map also
    //case when cap is full , we need to remove LRU
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            mostRecent(node);
        } else {

            if (size == capacity) {
                int rKey = this.head.key;
                removeNode(head);
                map.remove(rKey);
            }
            Node node = new Node(key, value);
            addLast(node);
            map.put(key, node);
        }
    }
    public static void main(String[]args) throws Exception {
        LRUCache cache = new LRUCache(1);
        cache.put(2, 1);
        cache.get(2);
        cache.put(3, 2);
        cache.get(2);
        cache.get(3);
        // System.out.println(cache.get(1));
    }

}


//["LRUCache","put","get","put","get","get"]
// [[1],[2,1],[2],[3,2],[2],[3]]