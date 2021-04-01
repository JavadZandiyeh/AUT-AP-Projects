/**
 * This is a class that sorts arrays by recursion method
 * How does it works? this class put the the elements of array
 * into 2 parts.one of them contains the integers that are bigger
 * than our last element and other part contains the integers that
 * are lower than our last element.this proses is done till the last element
 * and first element be same. means each integer become a part.
 * @version 0.0
 * @author Mohammad Javad Zandiyeh
 */
public class SortArray{

    /**
     *
     * @param arr is the array we want to sort it
     * @param low is the first parameter of the array we want to sort it
     * @param high is the last parameter we want to sort it from array
     * @return
     */
    static int part(int arr[], int low, int high) {

        //pivot store the last element of array
        int pivot = arr[high];
        //j store the first element of array
        int j = low;
        //making an integer
        int i;

        //this for loop is for putting the smaller elements
        // against last element at first
        for (i = low ; i < high; i++) {

            if (arr[i] < pivot) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }

        }


        //replacing the last integer to its real place
        //that the smaller integer from it become before it
        //and the bigger integers from it become after it
            int temp = arr[j];
            arr[j] = arr[high];
            arr[high] = temp;

            //number of smaller integers from the pivot
        return j;
    }

    /**
     * this class break the array to 2 parts that are
     * and do it till all elements go to its place
     * @param arr array we want to sort it
     * @param low the low element of array
     * @param high the high element of array
     */
    static void sort(int arr[], int low, int high) {
        //this method is done till the low element is
        //smaller than the high element
        if (low < high) {
            int p = part(arr, low, high);
            //the sort work is done again and again
            //and break the to lots of parts
            sort(arr, low, p-1);
            sort(arr, p+1, high);
        }

    }

    /**
     * this method is for printing the params that
     * are passed to this method
     * @param arr is the array that is going to pass to this method
     */
    static void print(int... arr) {
        for (int i: arr)
            System.out.print(i + " ");
        System.out.println();
    }

    //this method is not used and is garbage
    /*double power(double x, double y) {
        if (y == 0)
            return 1;

        double result = x;

        for (int i = 1; i < y; i++) {
            result = result * x;
        }

        return result;
    }*/

    // Driver program
    public static void main(String[] args) {

        //I made an example to be sure about work of ArraySort
        int[] b = {10,32,5,6,23,8,134,8,6};
        int k = b.length;
        sort(b ,0 , k-1);

        int a[] = {10, 7, 8, 9, 1, 5};
        int n = a.length;
        sort(a, 0, n-1);


        print(a[0], a[1], a[2], a[3], a[4], a[5]);
        print(b[0],b[1],b[2],b[3],b[4],b[5],b[6],b[7],b[8]);
    }
}

