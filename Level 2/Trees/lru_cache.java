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


//Better and Easy Implementation
//Use 2 dummy nodes head and tail so we dont need to update head or tail when rem node is
//node is head/tail head/tail or add
//Important Assumptions
//Most Recently Used MRU - Right after head (head.next node)
//Least Recently Used LRU - Right Before Tail (Tail.prev)

class LRUCache {

    Map < Integer, Node > map = new HashMap < > ();
    private Node head = new Node(0, 0);
    private Node tail = new Node(0, 0);
    private int capacity;
    private int size = 0;

    public LRUCache(int capacity) {
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public void mostRecent(Node node) { //remove from its place and add just after head
        if (size == 1) {
            return;
        }

        removeNode(node); //remove from prev pos
        addNode(node); // add just after head
    }

    public void addNode(Node node) { //always add just after head (MRU)
        Node next = head.next; //catch
        //4 links to update in DLL 2 nxt and 2 prev
        head.next = node;
        node.next = next;
        node.prev = head;
        next.prev = node;
        this.size++; //size inc
    }

    public void removeNode(Node node) { //either when making MRU or when size is > cap
        Node prev = node.prev; //catch prev
        Node next = node.next; //catch next
        node.next = node.prev = null; //unlink self
        prev.next = next; //link prev and next nodes
        next.prev = prev;
        this.size--; //size dec
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            mostRecent(node);
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) { //if exists , size check not needed
            Node node = map.get(key);
            node.val = value;
            map.put(key, node);
            mostRecent(node);
        } else {
            Node node = new Node(key, value);
            if (this.size == capacity) {
                Node rn = tail.prev;
                map.remove(rn.key);
                removeNode(rn); //LRU - size check already in remove fun()
            }
            map.put(key, node);  //size++ already in add fun()
            addNode(node); //it is already MRU , no need to update
        }
    }

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

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */