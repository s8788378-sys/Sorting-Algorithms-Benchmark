Sorting Algorithms Benchmark Report
👤 Personal Information
Name: شذى طارق بشير اللالي
Student ID: 3166034
Department: Computer Science / Programming
Course: Data Structures & Algorithms 2
📊 Benchmark Results (N = 100,000)
All execution times are measured in microseconds (μs):
Algorithm Random (μs) Reversely (μs) Partially (μs) Fully Sorted (μs)
Selection Sort 9,697,699 10,761,692 4,336,639 4,317,796
Insertion Sort 2,002,455 3,258,867 370 312
Bubble Sort 37,643,117 22,296,091 315 145
Merge Sort 41,216 14,884 15,443 11,119
Quick Sort 37,454 14,242 17,934 13,696
📈 Analysis & Discussion
1. O(N^2) vs O(N log N) Comparison
Quadratic Algorithms (O(N^2)): Selection Sort, Insertion Sort, and Bubble Sort exhibit high execution times on random data, making them inefficient for large datasets (N = 100,000).
Logarithmic Algorithms (O(N log N)): Merge Sort and Quick Sort significantly outperform quadratic algorithms, running in milliseconds rather than seconds.
2. Best Algorithms for Specific Data States
Fully & Partially Sorted Data: Insertion Sort and Bubble Sort perform exceptionally fast (< 400 μs) due to early exit and minimal swaps, achieving near O(N) best-case efficiency.
Random Data: Quick Sort and Merge Sort offer the best performance, processing 100,000 elements in less than 0.05 seconds.
3. Quick Sort Behavior & Pivot Selection
Standard Quick Sort with a fixed pivot suffers from O(N^2) worst-case performance on sorted or reversely sorted data, leading to a StackOverflowError due to deep recursion.
Implementing Randomized Pivot Selection avoids the worst-case scenario, maintaining average-case O(N log N) time complexity across all input configurations.
