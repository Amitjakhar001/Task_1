
import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap {
    private List<Integer> heap;
    private int numChildren;

    public PowerOfTwoMaxHeap(int numChildren) {
        this.heap = new ArrayList<>();
        this.numChildren = numChildren;
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }

        int max = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }

        return max;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / numChildren;

        while (index > 0 && heap.get(index) > heap.get(parentIndex)) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / numChildren;
        }
    }

    private void heapifyDown(int index) {
        int maxChildIndex = getMaxChildIndex(index);

        while (maxChildIndex != -1 && heap.get(index) < heap.get(maxChildIndex)) {
            swap(index, maxChildIndex);
            index = maxChildIndex;
            maxChildIndex = getMaxChildIndex(index);
        }
    }

    private int getMaxChildIndex(int index) {
        int leftMostChildIndex = index * numChildren + 1;
        int rightMostChildIndex = index * numChildren + numChildren;
        int maxChildIndex = -1;
        int maxValue = Integer.MIN_VALUE;

        for (int i = leftMostChildIndex; i <= rightMostChildIndex && i < heap.size(); i++) {
            if (heap.get(i) > maxValue) {
                maxValue = heap.get(i);
                maxChildIndex = i;
            }
        }

        return maxChildIndex;
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public static void main(String[] args) {
        // Create a power of two max heap with 4 children per parent
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(4);

        // Insert elements into the heap
        heap.insert(50);
        heap.insert(30);
        heap.insert(70);
        heap.insert(20);
        heap.insert(60);
        heap.insert(40);
        heap.insert(80);
        heap.insert(10);
        heap.insert(90);

        // Pop and print the maximum elements from the heap
        while (!heap.isEmpty()) {
            int max = heap.popMax();
            System.out.println(max);
        }
    }
}


