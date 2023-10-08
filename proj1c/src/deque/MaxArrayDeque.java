package deque;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    public static void main(String[] args) {
        MaxArrayDeque<String> lld1 = new MaxArrayDeque<String>(new LexicographicComparator());

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        System.out.println(lld1.max());
    }
    public Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c){
        comparator = c;
    }

    public T max(){
        if(size == 0){
            return null;
        }
        T maxElement = items[(font + 1 + capacity) % capacity];
        for (T item : this) {
            int cmp = comparator.compare(item, maxElement);
            if (cmp > 0) {
                maxElement = item;
            }
        }
        return maxElement;
    }

    public T max(Comparator<T> c){
        if(size == 0){
            return null;
        }
        T maxElement = items[0];
        for (T item : this) {
            int cmp = c.compare(item, maxElement);
            if (cmp > 0) {
                maxElement = item;
            }
        }
        return maxElement;
    }
}
