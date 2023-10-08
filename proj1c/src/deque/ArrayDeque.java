package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T>{
    public static void main(String[] args) {
        Deque<String> lld1 = new ArrayDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        System.out.println(lld1);
    }
    protected int font;
    protected int back;
    protected int size;
    protected int capacity;
    protected T[] items;
    public ArrayDeque(){
        font = 0;
        back = 1;
        size = 0;
        capacity = 8;
        items = (T[])new Object[capacity];
    }
    public void fill(T t){
        for(int i = 0; i < capacity; i++){
            items[(font + i + 1 + capacity) % capacity] = t;
        }
        size = capacity;
    }

    public void set(T t, int index){
        items[(font + index + 1 + capacity) % capacity] = t;
    }

    public ArrayDeque(int c){
        font = 0;
        back = 1;
        size = 0;
        capacity = c;
        items = (T[])new Object[capacity];
    }
    private void resize(int newCapacity){
        T[] a = (T[])new Object[newCapacity];
        for(int i = 0; i < size; i++){
            a[(font + i+ 1 + newCapacity) % newCapacity] = items[(font + i + 1 + capacity) % capacity];
        }
        items = a;
        capacity = newCapacity;
    }
    @Override
    public void addFirst(T x) {
        if(size == capacity){
            resize(size + 20);
        }
        items[(font + capacity)%capacity] = x;
        font = (font-1)%capacity;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if(size == capacity){
            resize(size + 20);
        }
        items[back] = x;
        back = (back+1)%capacity;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> res = new ArrayList<>();
        for(int i = 0; i < size; i++){
            res.add(items[(font + i + 1 + capacity) % capacity]);
        }
        return res;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(size == 0)
            return null;
        if(size < (capacity / 4) && capacity > 8){
            resize(capacity / 2);
        }
        font += 1;
        T res = items[(font+ capacity) % capacity];
        items[(font+ capacity) % capacity] = null;
        size -= 1;
        return res;
    }

    @Override
    public T removeLast() {
        if(size == 0)
            return null;
        if(size < (capacity / 4) && capacity > 8){
            resize(capacity / 2);
        }
        back -= 1;
        T res = items[back];
        items[back] = null;
        size -= 1;
        return res;
    }

    @Override
    public T get(int index) {
        if(items[index] != null){
            return items[index];
        }
        return null;
    }

    public T getFirst(){
        return items[(font + 1 + capacity) % capacity];
    }

    @Override
    public T getRecursive(int index) {
        int i = 0;
        for(T item : this){
            i += 1;
            if(i > index){
                return item;
            }
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int wizPos;
        public ArrayDequeIterator(){
            wizPos = 0;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }
        @Override
        public T next() {
            T returnItem = items[(font + wizPos + 1 + capacity)%capacity];
            wizPos += 1;
            return returnItem;
        }
    }

    public boolean equals(Object o){
        // whether same type
        if(o instanceof ArrayDeque obj){
            // whether same length
            if(obj.size() == size){
                // whether same element
                int index = 0;
                for(int i = 0; i < size; i++){
                    index =(font + 1 + i + capacity)%capacity;
                    if(obj.items[index] != items[index]){
                        return false;
                    }
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
        int index = 0;
        for (int i = 0; i < size - 1; i += 1) {
            index =(font + 1 + i + capacity)%capacity;
            returnSB.append(items[index].toString());
            returnSB.append(", ");
        }
        returnSB.append(items[index + 1]);
        returnSB.append("}");
        return returnSB.toString();
    }
}
