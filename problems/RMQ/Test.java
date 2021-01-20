package problems.RMQ;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Testing class for RMQ solution
 */
public class Test {

    public static void test(int[] arr, RMQ rmq) {
        RMQ base = new Precompute(arr);
        System.out.println("=========================");
        System.out.println("Initializing Testing...");
        System.out.println("Input Array: ");
        System.out.print("[ ");
        for (int n : arr) {
            System.out.print(n + ", ");
        }
        System.out.println("]");

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                System.out.print("Test - Low : " + i + " High : " + j);
                System.out.println(" Result : " + rmq.query(i, j) + " Actual : " + base.query(i, j));
                if (rmq.query(i, j) != base.query(i, j)) {
                    throw new Error("Assertion Failed" + i + " " + j);
                }
            }
        }

        System.out.println("Testing for RMQ class Finished");
        System.out.println("=========================");
    }

    public static void testWithRandomArrays() {
        for (int i = 0; i <= 100; i++) {
            int[] arr = new int[i];
            fillWithRandomValues(arr);
            RMQ rmq = new HybridRMQ(arr);
            test(arr, rmq);
        }
    }

    public static void fillWithRandomValues(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateRandNumInRange(-100, 100);
        }
    }

    public static int generateRandNumInRange(int lo, int hi) {
        return ThreadLocalRandom.current().nextInt(lo, hi + 1);
    }

    public static void main(String[] args) {
        testWithRandomArrays();
        System.out.println("All Test Cases Passed");
    }
}
