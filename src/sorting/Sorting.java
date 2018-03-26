package sorting;
import java.util.Arrays;

/**
 * Created by Golota on 06.02.2018.
 */
public class Sorting {

    static void print(int[] arr) {
        for (int i = 0; i < arr.length - 1; i ++)
            System.out.print(arr[i] + ", ");
        System.out.print(arr[arr.length-1] + "\n");

    }
    public static void main(String... args) {
        int[] arr = {12, 6, 5, 1, 3, 8, 4, 9, 11, 7, 10, 2};
        Arrays.sort(arr);
        System.out.println("Arrays: " + Arrays.toString(arr));

        for (int i = 0; i < arr.length; i ++) {
            for (int j = 0; j < arr.length - i - 1; j ++)
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            System.out.print("Iteration #" + (i + 1) + ": ");
            print(arr);
        }

    }
}
