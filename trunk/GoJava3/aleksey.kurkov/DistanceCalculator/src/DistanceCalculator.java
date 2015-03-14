import java.util.Scanner;

/**
 * Created by Aleksey Kurkov on 11.03.15.
 */

/*  Test cases
    Input: 23 45 34 12 45 4 38 56 2 49 100

    Output:
    min1[8] = 2
    min2[5] = 4
    Distance = 3
*/


public class DistanceCalculator {
    public static void main(String[] args) {

        int min1, min2, index1, index2;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input string of numbers: ");

        String string = scanner.nextLine();
        String[] stringArray = string.split(" ");

        int[] intArray = new int[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        min1 = intArray[0];
        index1 = 0;

        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] < min1) {
                min1 = intArray[i];
                index1 = i;
            }
        }

        min2 = intArray[0];
        index2 = 0;

        for (int i = 0; i < intArray.length; i++) {
            if ((intArray[i] < min2) && (intArray[i] > min1)) {
                min2 = intArray[i];
                index2 = i;
            }
        }

        System.out.println("\nmin1[" + index1 + "] = " + min1);
        System.out.println("min2[" + index2 + "] = " + min2);
        System.out.println("Distance = " + (index1 - index2));
    }
}