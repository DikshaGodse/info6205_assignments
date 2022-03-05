package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import org.ini4j.Ini;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Interface to define the behavior of a Benchmark.
 *
 * @param <T> the underlying type which is passed into (or supplied) to the run/runFromSupplier methods.
 */
public interface Benchmark<T> {
    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param t the value that will in turn be passed to function f.
     * @param m the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function f.
     */
    default double run(T t, int m) {
        return runFromSupplier(() -> t, m);
    }

    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param supplier a Supplier of a T
     * @param m        the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function f.
     */
    double runFromSupplier(Supplier<T> supplier, int m);

    public static void main(String[] args) {

        double result = 0;
        for (int i =100; i <= 3200; i=i*2) {
            Random random = new Random();
            List<Integer> randomList = new ArrayList<Integer>();
            for (int tempJ = 0; tempJ < i; tempJ++) {
                randomList.add(random.nextInt(i));
            }
            InsertionSort insertionSort = new InsertionSort(new Config(new Ini()));
            Benchmark<Integer> benchmark = new Benchmark_Timer<>("",
                    blank -> {
                        insertionSort.sort(randomList);
                    });
            result = benchmark.run(0, 10);
            System.out.println("Random Array: Insertion sort with "+i+" elements takes "+result+" ms");
        }
        System.out.println("-------------------------------------------------------------------------------------");
        for (int tempI =100; tempI <= 3200; tempI=tempI*2) {
            Random random = new Random();
            List<Integer> orderedList = new ArrayList<Integer>();
            for (int j = 0; j < tempI; j++) {
                orderedList.add(tempI);
            }
            InsertionSort insertionSort = new InsertionSort(new Config(new Ini()));
            Benchmark<Integer> benchmark = new Benchmark_Timer<>("",
                    blank -> {
                        insertionSort.sort(orderedList);
                    });
            result = benchmark.run(0, 10);
            System.out.println("Ordered Array: Insertion sort with "+tempI+" elements takes "+result+" ms");
        }
        System.out.println("-------------------------------------------------------------------------------------");
        for (int i =100; i <= 3200; i=i*2) {
            Random random = new Random();
            List<Integer> partiallyOList = new ArrayList<Integer>();
            for (int j = 0; j < i; j++) {
                if (random.nextInt(50) < 30) {
                    partiallyOList.add(j);
                } else {
                    partiallyOList.add((random.nextInt(i)));
                }
            }
            InsertionSort insertionSort = new InsertionSort(new Config(new Ini()));
            Benchmark<Integer> benchmark = new Benchmark_Timer<>("",
                    blank -> {
                        insertionSort.sort(partiallyOList);
                    });
            result = benchmark.run(0, 10);
            System.out.println("Partially Ordered Array: Insertion sort with "+i+" elements takes "+result+" ms");
        }
        System.out.println("-------------------------------------------------------------------------------------");
        for (int tempI =100; tempI <= 3200; tempI=tempI*2) {
            Random random = new Random();
            List<Integer> reverseOList = new ArrayList<Integer>();
            for (int tempJ = 0; tempJ < tempI; tempJ++) {
                reverseOList.add(tempI - tempJ);
            }
            InsertionSort insertionSort = new InsertionSort(new Config(new Ini()));
            Benchmark<Integer> benchmark = new Benchmark_Timer<>("",
                    blank -> {
                        insertionSort.sort(reverseOList);
                    });
            result = benchmark.run(0, 10);
            System.out.println("Reverse Ordered Array: Insertion sort with "+tempI+" elements takes "+result+" ms");
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }
}