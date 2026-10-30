package sortingbenchmark;
import java.util.Arrays; 
import java.util.Random;
public class SortingBenchmark {
public static void main(String[] args) {
    int size = 100000;
    int[] originalArray = new int[size];
    Random rand = new Random();

    for (int i = 0; i < size; i++) {
        originalArray[i] = rand.nextInt(1000000);
    }

    int[] randomCase = Arrays.copyOf(originalArray, size);
    
    int[] fullySortedCase = Arrays.copyOf(originalArray, size);
    Arrays.sort(fullySortedCase);
    
    int[] reverselySortedCase = new int[size];
    for (int i = 0; i < size; i++) {
        reverselySortedCase[i] = fullySortedCase[size - 1 - i];
    }
    
    int[] partiallySortedCase = Arrays.copyOf(fullySortedCase, size);
    for (int i = 0; i < size; i += 5) {
        int temp = partiallySortedCase[i];
        partiallySortedCase[i] = partiallySortedCase[i + 1 < size ? i + 1 : i];
        partiallySortedCase[i + 1 < size ? i + 1 : i] = temp;
    }

    System.out.println("-----------------------------------------------------------------------------------------");
    System.out.printf("%-15s | %-15s | %-15s | %-15s | %-15s\n", 
            "Algorithm", "Random (μs)", "Reversely (μs)", "Partially (μs)", "Fully Sorted (μs)");
    System.out.println("-----------------------------------------------------------------------------------------");

    runAndMeasure("Selection Sort", randomCase, reverselySortedCase, partiallySortedCase, fullySortedCase, 1);
    runAndMeasure("Insertion Sort", randomCase, reverselySortedCase, partiallySortedCase, fullySortedCase, 2);
    runAndMeasure("Bubble Sort", randomCase, reverselySortedCase, partiallySortedCase, fullySortedCase, 3);
    runAndMeasure("Merge Sort", randomCase, reverselySortedCase, partiallySortedCase, fullySortedCase, 4);
    runAndMeasure("Quick Sort", randomCase, reverselySortedCase, partiallySortedCase, fullySortedCase, 5);

    System.out.println("-----------------------------------------------------------------------------------------");
}

public static void runAndMeasure(String name, int[] randArr, int[] revArr, int[] partArr, int[] fullArr, int type) {
    long t1 = measureTime(randArr, type);
    long t2 = measureTime(revArr, type);
    long t3 = measureTime(partArr, type);
    long t4 = measureTime(fullArr, type);

    System.out.printf("%-15s | %-15d | %-15d | %-15d | %-15d\n", name, t1, t2, t3, t4);
}

public static long measureTime(int[] original, int type) {
    int[] testArray = Arrays.copyOf(original, original.length);
    long startTime = System.nanoTime();

    if (type == 1) {
        selectionSort(testArray);
    } else if (type == 2) {
        insertionSort(testArray);
    } else if (type == 3) {
        bubbleSort(testArray);
    } else if (type == 4) {
        mergeSort(testArray, 0, testArray.length - 1);
    } else if (type == 5) {
        quickSort(testArray, 0, testArray.length - 1);
    }

    long endTime = System.nanoTime();
    return (endTime - startTime) / 1000; 
}

public static void selectionSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }
        int temp = arr[minIdx];
        arr[minIdx] = arr[i];
        arr[i] = temp;
    }
}

public static void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

public static void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        boolean swapped = false;
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                swapped = true;
            }
        }
        if (!swapped) break;
    }
}
public static void mergeSort(int[] arr, int l, int r) {
    if (l < r) {
        int m = l + (r - l) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }
}

public static void merge(int[] arr, int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;
    int[] L = new int[n1];
    int[] R = new int[n2];
    for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
    for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];
    int i = 0, j = 0, k = l;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) { arr[k] = L[i]; i++; }
        else { arr[k] = R[j]; j++; }
        k++;
    }
    while (i < n1) { arr[k] = L[i]; i++; k++; }
    while (j < n2) { arr[k] = R[j]; j++; k++; }
}

public static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pi = randomizedPartition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

public static int randomizedPartition(int[] arr, int low, int high) {
    Random rand = new Random();
    int pivotIndex = low + rand.nextInt(high - low + 1);
    int temp = arr[pivotIndex];
    arr[pivotIndex] = arr[high];
    arr[high] = temp;
    return partition(arr, low, high);
}

public static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = (low - 1);
    for (int j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    return i + 1;
}
}
