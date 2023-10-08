import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {
    @Test
    public void testEqualLinkedListDeque() {
        Deque<String> lld1 = new LinkedListDeque<>();
        Deque<String> lld2 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void testEqualArrayDeque() {
        Deque<String> lld1 = new ArrayDeque<>();
        Deque<String> lld2 = new ArrayDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void testLinkedListQueueToString(){
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        String expected = "{front, middle, back}";
        assertThat(lld1.toString()).isEqualTo(expected);
    }

    @Test
    public void ArrayQueueToString(){
        Deque<String> lld1 = new ArrayDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        lld1.addFirst("front");
        lld1.addFirst("middle");
        lld1.addFirst("back"); // {back, middle, front, front, middle, back}
        lld1.addLast("back");
        lld1.addFirst("front");
        lld1.addLast("back");
        lld1.addFirst("front");
        lld1.addLast("back");
        lld1.addFirst("front"); // {front, front, front, back, middle, front, front, middle, back, back, back, back}
        String expected = "{front, front, front, back, middle, front, front, middle, back, back, back, back}";
        assertThat(lld1.toString()).isEqualTo(expected);
    }
}
