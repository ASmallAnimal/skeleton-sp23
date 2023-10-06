import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque<Integer> lld1 = new ArrayDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -2, -1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -2, -2, -1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -2, -2, -2, -1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -2, -2, -2, -2, -1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -2, -2, -2, -,2 -,2 -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -2, -2, -2, -2, -2, -1, 0, 1, 2).inOrder();
    }

    @Test
    public void removeFirstAndRemoveLastTest() {
        Deque<Integer> lld1 = new ArrayDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -2, -1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -2, -2, -1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -2, -2, -2, -1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -2, -2, -2, -2, -1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -2, -2, -2, -2, -2, -1, 0, 1, 2]
        lld1.removeFirst();   // [-2, -2, -2, -2, -2 -1, 0, 1, 2]
        lld1.removeLast();    // [-2, -2, -2, -2, -2 -1, 0, 1]
        lld1.removeFirst();   // [-2, -2, -2, -2, -1, 0, 1]
        lld1.removeFirst();   // [-2, -2, -2 -1, 0, 1]
        lld1.removeLast();    // [-2, -2, -2 -1, 0]
        lld1.removeLast();    // [-2, -2, -2 -1]

        assertThat(lld1.toList()).containsExactly( -2, -2, -2, -1).inOrder();
    }
}
