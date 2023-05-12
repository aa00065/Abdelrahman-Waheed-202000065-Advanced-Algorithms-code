import java.util.Arrays;

public class task2_arraychecker {
    
    // Method to check if two integer arrays are equal
    public static boolean chkarray(int[] array1, int[] array2) {
        
        // Initialize a boolean variable to keep track of array equality
        boolean isequal = true;
        
        // creat a for loop to loop over the arrays and compare values at each index
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                // If values at any index are not equal, set isequal to false and break loop
                isequal = false;
                break;
            }
        }
        
        // Print a message  if arrays are equal or not
        if (isequal) {
            System.out.println("The arrays are the same");
        } else {
            System.out.println("The arrays are not the same");
        }
        
        // Return the boolean value indicating array equality
        return isequal;
    }
    
    public static void main(String[] args) {
        
        // cretate two arrays with the same  values but different order
        int[] a = {2, 4, 6, 8, 10};
        int[] b = {10, 8, 6, 4, 2};
        
        // Sort arrays 
        Arrays.sort(a);
        Arrays.sort(b);
        
        // Call the chkarray method and print the boolean result to the console
        System.out.println(chkarray(a, b));
    }
}
