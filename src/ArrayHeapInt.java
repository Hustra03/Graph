public class ArrayHeapInt {
    int heap[];
    int currentMaxIndex;

    ArrayHeapInt(int length) {
        this.heap = new int[length];
    }

    public void bubble(int value) {
        if (currentMaxIndex >= heap.length) {
            System.out.println("Array Maximum Size Reached");
            return;
        }
        int currentPosition = currentMaxIndex;
        heap[currentPosition] = value;

        int previousPostion;

        if ((currentPosition % 2) == 0) {
            previousPostion = (currentPosition - 2) / 2;
        } else {
            previousPostion = (currentPosition - 1) / 2;
        }

        while ((previousPostion >= 0) && (previousPostion <= heap.length - 1)
                && (heap[currentPosition] < heap[previousPostion])) {
            int tempValue = heap[currentPosition];
            heap[previousPostion] = heap[currentPosition];
            heap[currentPosition] = tempValue;

            currentPosition = previousPostion;
            if ((currentPosition % 2) == 0) {
                previousPostion = (currentPosition - 2) / 2;
            } else {
                previousPostion = (currentPosition - 1) / 2;
            }
        }

        currentMaxIndex += 1;

    }

    public int sink() {
        int returnValue = heap[0];

        if (currentMaxIndex <= 0) {
            System.out.println("All Elements Sunk");
            return returnValue;
        }
        heap[0] = heap[currentMaxIndex - 1];
        sinkElement(0, 0);
        currentMaxIndex -= 1;

        return returnValue;
    }

    public int increment(int incrementAmount) {
        heap[0] += incrementAmount;
        int level = sinkElement(0, 0);
        return level;
    }

    private int sinkElement(int currentIndex, int level) {

        int nextIndexLeft = (currentIndex) * 2 + 1;
        int nextIndexRight = (currentIndex) * 2 + 2;

        if (nextIndexLeft < currentMaxIndex) {
            if (heap[currentIndex] > heap[nextIndexLeft]) {
                if (nextIndexRight >= currentMaxIndex) {
                    level += 1;
                    int tempValue = heap[nextIndexLeft];
                    heap[nextIndexLeft] = heap[currentIndex];
                    heap[currentIndex] = tempValue;
                    level = sinkElement(nextIndexLeft, level);
                } 
                else
                {
                    if (heap[nextIndexRight] >= heap[nextIndexLeft]) {
                        
                    level += 1;
                    int tempValue = heap[nextIndexLeft];
                    heap[nextIndexLeft] = heap[currentIndex];
                    heap[currentIndex] = tempValue;
                    level = sinkElement(nextIndexLeft, level);
                    }
                }
            }
        }
        if (nextIndexRight < currentMaxIndex) {
            if (heap[currentIndex] > heap[nextIndexRight]) {

                level += 1;
                int tempValue = heap[nextIndexRight];
                heap[nextIndexRight] = heap[currentIndex];
                heap[currentIndex] = tempValue;
                level = sinkElement(nextIndexRight, level);
            }

        }
        return level;
    }
}
