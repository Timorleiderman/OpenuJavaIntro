public class DHeap {

    private int _d;

    public DHeap(int d) {
        if (d <= 1) {
            throw new IllegalArgumentException("d must be greater than 1");
        }
        _d = d;
    }

    public void sort(int arr[]) {

        if (arr == null || arr.length == 0) {
            return; // Handle null or empty array
        }

        buildDMaxHeap(arr);

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    /**
     * 
     * @param i - node index
     * @param k - k^th child
     * @return the index of the left child
     */
    private int child(int k, int i) {
        return _d * i + k + 1;
    }

    public void buildDMaxHeap(int arr[]) {
        int n = arr.length;
        for (int i = (n - 2)/ _d; i >= 0; i--) {
            heapify(arr, i, n);
        }
    }

    /**
     * 
     * @param arr - array
     * @param i - index
     * @param n - size
     */
    public void heapify(int arr[], int i, int n) {
        
        // int n = arr.length;
        int largest_idx = i;
        for (int k = 0; k < _d; k++) {
            int ch_idx = child(k, i);
            if (ch_idx < n && arr[ch_idx] > arr[largest_idx]) {
                if (arr[ch_idx] > arr[largest_idx]) {
                    largest_idx = ch_idx;
                }
            }
        }
        if (largest_idx != i){
                swap(arr, largest_idx, i);
                heapify(arr, largest_idx, n);
        }
    }

    public void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public boolean validHeap(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < _d; k++) {
                int ch_idx = child(k, i);
                if (ch_idx < n && arr[ch_idx] > arr[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
