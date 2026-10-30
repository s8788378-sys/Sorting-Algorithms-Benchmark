Sorting-Algorithms-Benchmark

Student Information

Name:شذى طارق بشير اللالي

 University ID: 3166034
 
 Section: __________
 
Description

Java program that benchmarks 5 sorting algorithms (Selection, Insertion, Bubble, Merge, Quick) on arrays of N = 100,000 elements, 

in 4 cases: Random, Reversed, Partially Sorted (~80%), Fully Sorted.

Time is measured with System.nanoTime() and converted to microseconds (µs). Only the sorting time is measured (the array copy is excluded).

Results (µs)

Algorithm | Random | Reversed | Partially Sorted | Fully Sorted

Selection Sort | 9,697,699 | 10,761,692 | 4,336,639 | 4,317,796

Insertion Sort | 2,002,455 | 3,258,867 | 370 | 312

Bubble Sort | 37,643,117 | 22,296,091 | 315 | 145

Merge Sort | 41,216 | 14,884 | 15,443 | 11,119

Quick Sort | 37,454 | 14,242 | 17,934 | 13,696

Chart

<img width="1161" height="632" alt="image" src="https://github.com/user-attachments/assets/f28e1e0c-4fcd-45bf-9ff5-c26444d1d755" />




Analysis

 O(N²) vs O(N log N): Selection, Insertion, and Bubble Sort have quadratic time complexity (O(N²)), making them very slow on 
 random data for N = 100,000. In contrast, Merge and Quick Sort use O(N log N) time, completing in milliseconds.
 
 Best algorithm for sorted / partially sorted data: Insertion Sort and Bubble Sort perform exceptionally fast (< 400 µs) on sorted or partially sorted data due to early exit and minimal swaps, approaching O(N) best-case efficiency.
 
 Quick Sort behavior: Worst case happens when the pivot is the min/max, e.g., first/last element on sorted data leading to O(N²); this code uses median-of-three to avoid it and maintain stable performance.

<img width="1169" height="625" alt="image" src="https://github.com/user-attachments/assets/321d8cca-13fb-43fc-8007-6950c5b2322d" />
 
