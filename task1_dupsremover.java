import java.util.Arrays;

public class task1_dupsremover {
    // method to remove duplicates from char array
	public static int removedups(char arr[], int c){  
	    // check if the len of array is 0 or 1

	    if (c==0 || c==1){  
	         return c;  
	    }  
	    // create a variable to keep track of non-duplicate elements in the array
	    int j = 0;  
	    // loop through the array starting from the second element
	    
        for (int i=1; i<c; i++){  
	        // if the current element is not equal to the previous element, move it to the next position in the array
	        if (arr[i] != arr[j]){  
	            arr[++j] = arr[i];  
	        }  
	     }  
	    // return the new len of the  array
	     return j+1;  
	}  
	       
    public static void main (String[] args) {  
        // create an array of characters
        char alpha[] = {'a','b','c','e','a','e'};
        // sort  array in ascending order
        Arrays.sort(alpha);
        // get the  len of the array
        int len = alpha.len;  
        // call the remove duplicates method to modify the array and get the new len
        len = removedups(alpha, len);  
        // loop through the modified array and print its elements
       
        for (int i=0; i<len; i++)  
            System.out.print(alpha[i]+" ");  
    }  
} 
