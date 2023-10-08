import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(1);
        lld.addFirst(2);
        lld.addFirst(3);
        lld.addLast(4);
        lld.addLast(5);
        lld.addLast(6);
    }
    public class Node{
        Node prev;
        T item;
        Node next;
        public Node(){
            prev = null;
            item = null;
            next = null;
        }
        public Node(T item, Node node){
            this.item = item;
            this.prev = node;
            this.next = node.next;
        }
    }
    public Node sentinel;
    public int size = 0;
    public LinkedListDeque(){
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    @Override
    public void addFirst(T x) {
        Node newItem = new Node(x,sentinel);
        sentinel.next.prev = newItem;
        sentinel.next = newItem;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node newItem = new Node(x,sentinel.prev);
        newItem.prev.next = newItem;
        sentinel.prev = newItem;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node node = sentinel.next;
        while(node != sentinel){
            returnList.add(node.item);
            node = node.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.prev == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(size == 0)
            return null;
        size -= 1;
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return res;
    }

    @Override
    public T removeLast() {
        if(size == 0)
            return null;
        size -= 1;
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return res;
    }

    @Override
    public T get(int index) {
        if(index > size){
            System.out.println("out of size");
            return null;
        }
        Node node = sentinel.next;
        int i = 0;
        while(i < index){
            node = node.next;
            i++;
        }
        return node.item;
    }

    public T getRecursiveHelper(int index, Node node){
        if(index == 0){
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }

    @Override
    public T getRecursive(int index) {
        if(index > size){
            System.out.println("out of size");
            return null;
        }
        return getRecursiveHelper(index,sentinel.next);
    }
}
