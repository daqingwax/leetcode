//doubly linked list with map
class LRUCache {
    
    int n;
    Map<Integer, Node> map;
    Node head;
    Node tail;
    class Node{
        int key;
        int val;
        Node pre;
        Node next;
        Node(int key, int val){
            this.val = val;
            this.key = key;
        }
    }
    public void removeNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
        //map.remove(node.key);
    }
    
    public void addNode(Node node){
        head.next.pre = node;
        node.next = head.next;
        node.pre = head;
        head.next = node;
    }
    
    public LRUCache(int capacity) {
        this.n = capacity;
        map = new HashMap<>();
        head = new Node(-1, 0);
        tail = new Node(-1, 0);
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addNode(node);
            return(node.val);
        } else{
            return(-1);
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addNode(node);
        } else{
            if(map.size() >= this.n){
                Node node = tail.pre;
                map.remove(node.key);
                removeNode(node);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addNode(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
