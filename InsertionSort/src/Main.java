/**
 * this class is for sorting an array and printing it from low to high
 * @version 0.1
 * @author Mohammad Javad Zandiyeh
 */
public class Main {

    public static void main(String[] args) {
        //the array we want to sort it
        int[] array = {14, 19, 8, 7, 48, 16, 63, 27};

        //the (n) is the length of this array
        int n = array.length;

        /**
         * this for loop sort this array by specifying key.this key number is begin
         * from the second element of array and by the while loop we sort the elements
         * before this key and also the key.then we put the next element the key number
         * and do this work to end of array.
         */
        for (int j = 1; j < n; j++) {

            int key = array[j];
            int i = j - 1;

            /**
             * this while loop sort the elements before the key and also the key.
             * but how? it puts the key to its place and shift the elements that are
             * bigger than the key.
             */
            while ((i >= 0) && (array[i] > key)) {
                array[i + 1] = array[i];
                i--;
            }

            //putting the key to its correct place after the shift that has been done in
            //while loop
            array[i + 1] = key;

        }

        /**
         * printing the sorted array
         */
        for (int i : array) {
            System.out.print(i + " ");
        }

    }
}
