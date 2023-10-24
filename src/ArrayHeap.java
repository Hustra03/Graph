public class ArrayHeap {
    Path heap[];
    int currentMaxIndex;

    ArrayHeap(int length) {
        this.heap = new Path[length];
        this.currentMaxIndex=0;
    }

    public void bubble(Path value) {
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
                && (heap[currentPosition].getDist() < heap[previousPostion].getDist())) {
            Path tempValue = heap[currentPosition];
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

    public Path sink() {
        Path returnValue = heap[0];

        if (currentMaxIndex <= 0) {
            System.out.println("All Elements Sunk");
            return returnValue;
        }
        heap[0] = heap[currentMaxIndex - 1];
        sinkElement(0, 0);
        currentMaxIndex -= 1;

        return returnValue;
    }

    private int sinkElement(int currentIndex, int level) {

        int nextIndexLeft = (currentIndex) * 2 + 1;
        int nextIndexRight = (currentIndex) * 2 + 2;

        if (nextIndexLeft < currentMaxIndex) {
            if (heap[currentIndex].getDist() > heap[nextIndexLeft].getDist() ) {
                if (nextIndexRight >= currentMaxIndex) {
                    level += 1;
                    Path tempValue = heap[nextIndexLeft];
                    heap[nextIndexLeft] = heap[currentIndex];
                    heap[currentIndex] = tempValue;
                    level = sinkElement(nextIndexLeft, level);
                } 
                else
                {
                    if (heap[nextIndexRight].getDist()  >= heap[nextIndexLeft].getDist() ) {
                        
                    level += 1;
                    Path tempValue = heap[nextIndexLeft];
                    heap[nextIndexLeft] = heap[currentIndex];
                    heap[currentIndex] = tempValue;
                    level = sinkElement(nextIndexLeft, level);
                    }
                }
            }
        }
        if (nextIndexRight < currentMaxIndex) {
            if (heap[currentIndex].getDist()  > heap[nextIndexRight].getDist() ) {

                level += 1;
                Path tempValue = heap[nextIndexRight];
                heap[nextIndexRight] = heap[currentIndex];
                heap[currentIndex] = tempValue;
                level = sinkElement(nextIndexRight, level);
            }

        }
        return level;
    }
}
