package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    public static void main(String[] args) {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        System.out.println(lld1);
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
    public int size;
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

    @Override
    public T getRecursive(int index) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListQueueIterator();
    }

    private class LinkedListQueueIterator implements Iterator<T>{
        public int wizPos;
        private Node wizPosPtr;
        public LinkedListQueueIterator(){
            wizPos = 0;
            wizPosPtr = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }
        @Override
        public T next() {
            T returnItem = wizPosPtr.item;
            wizPos += 1;
            wizPosPtr = wizPosPtr.next;
            return returnItem;
        }
    }
    public boolean equals(Object o) {
        // whether same type
        if(o instanceof LinkedListDeque obj){
            // whether same length
            if(obj.size() == size){
                // whether same element
                Node node_O = obj.sentinel.next;
                Node node_this = sentinel.next;
                for(int i = 0; i < size; i++){
                    if(node_O.item != node_this.item){
                        return false;
                    }
                    node_O = node_O.next;
                    node_this = node_this.next;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        // 如果使用String，在拼接字符串时会产生大量的大量的临时对象，同时会频繁地垃圾回收
        StringBuilder returnSB = new StringBuilder("{");
        Node node = sentinel.next;
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(node.item.toString());
            node = node.next;
            returnSB.append(", ");
        }
        returnSB.append(node.item);
        returnSB.append("}");
        return returnSB.toString();
    }
}
