// Approach (3 sentences):
// We use a HashMap to store key-node pairs for O(1) access and a doubly linked list to maintain the order of recently used elements, 
// with the most recently used at the head and least at the tail. 
// On get(), if the key exists, we move the node to the head; on put(), we insert a new node at the head or update the value if it exists, 
// and remove the least recently used node if capacity is exceeded. 
// The linked list handles ordering, while the HashMap ensures constant-time lookup and updates.

// Time Complexity (TC):
// get(key) → O(1)
// put(key, value) → O(1)

// Space Complexity (SC):
// O(capacity) for storing up to capacity nodes in the HashMap and doubly linked list.

class LRUCache {
    class Node{
        Node prev;
        int key;
        int val;
        Node next;

        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;
    private int capacity;
    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.capacity = capacity;
        head.next = tail;
        tail.prev= head;
        map = new HashMap<>();
    }

    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            remove(node);
            addToHead(node);
            node.val = value;
        } else{
            if(capacity == map.size()){
                map.remove(tail.prev.key);
                remove(tail.prev);
            }
            Node node = new Node(key, value);
            map.put(key,node);
            addToHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
