import java.util.Arrays;
import java.util.Scanner;

public class task7_minheapsort {

    // A recursive method to maintain the min-heap property of an array
    private static void minheap(int[] arr, int rootIndex, int heapSize) {
        // Calculate the indices of the left and right children of the current node
        int leftchild_idx = 2 * rootIndex + 1;
        int rightchild_idx = 2 * rootIndex + 2;

        // Find the index of the smallest element among the current node and its children
        int smallestIndex = rootIndex;
        if (leftchild_idx < heapSize && arr[leftchild_idx] < arr[smallestIndex]) {
            smallestIndex = leftchild_idx;
        }
        if (rightchild_idx < heapSize && arr[rightchild_idx] < arr[smallestIndex]) {
            smallestIndex = rightchild_idx;
        }

        // If the smallest element is not the current node, swap them and call minheap recursively on the new node
        if (smallestIndex != rootIndex) {
            int temp = arr[rootIndex];
            arr[rootIndex] = arr[smallestIndex];
            arr[smallestIndex] = temp;
            minheap(arr, smallestIndex, heapSize);
        }
    }

    // A method to sort an array using min-heap sort
    public static void minheapSort(int[] arr) {
        int n = arr.length;

        // Build a min-heap from the array
        for (int i = n / 2 - 1; i >= 0; i--) {
            minheap(arr, i, n);
        }

        // Extract the minimum element from the heap and rebuild the heap until the array is sorted
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            minheap(arr, 0, i);
        }
    }

    // The main method, which initializes an array, sorts it using min-heap sort, and prints the sorted array
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 4, 2};

        minheapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}