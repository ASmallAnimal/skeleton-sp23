import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T>{
    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
    }
    private int font;
    private int back;
    private int size;
    private int capacity;
    private T[] items;

    public ArrayDeque(){
        font = 0;
        back = 1;
        size = 0;
        capacity = 8;
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

    public int getCapacity(){
        return capacity;
    }
}
