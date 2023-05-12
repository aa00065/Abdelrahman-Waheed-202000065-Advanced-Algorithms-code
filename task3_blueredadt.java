public class task3_blueredadt<T> {
    // Instance variables
    private T[] stackArray; // The array used to implement the stack
    private int redTop; // Index of the top item of the red stack
    private int blueTop; // Index of the top item of the blue stack
    
    // Constructor
    public task3_blueredadt(int size) {
        stackArray = (T[]) new Object[size]; // Create a new array of the specified size
        redTop = -1; // Initialize redTop to -1, indicating an empty stack
        blueTop = size; // Initialize blueTop to size, indicating an empty stack
    }

    // Push an item onto the red stack
    public void redPush(T item) {
        if (redTop + 1 == blueTop) { // If the red stack is full
            throw new RuntimeException("Stack overflow"); // Throw an exception
        }
        stackArray[++redTop] = item; // Add the item to the red stack and increment redTop
    }

    // Push an item onto the blue stack
    public void bluePush(T item) {
        if (blueTop - 1 == redTop) { // If the blue stack is full
            throw new RuntimeException("Stack overflow"); // Throw an exception
        }
        stackArray[--blueTop] = item; // Add the item to the blue stack and decrement blueTop
    }

    // Pop an item from the red stack
    public T redPop() {
        if (redTop == -1) { // If the red stack is empty
            throw new RuntimeException("Red stack underflow"); // Throw an exception
        }
        return stackArray[redTop--]; // Return the top item of the red stack and decrement redTop
    }

    // Pop an item from the blue stack
    public T bluePop() {
        if (blueTop == stackArray.length) { // If the blue stack is empty
            throw new RuntimeException("Blue stack underflow"); // Throw an exception
        }
        return stackArray[blueTop++]; // Return the top item of the blue stack and increment blueTop
    }

    // Check if the red stack is empty
    public boolean isRedEmpty() {
        return redTop == -1; // Return true if redTop is -1, indicating an empty stack
    }

    // Check if the blue stack is empty
    public boolean isBlueEmpty() {
        return blueTop == stackArray.length; // Return true if blueTop is equal to the length of the array, indicating an empty stack
    }

    // Get the size of the red stack
    public int redSize() {
        return redTop + 1; // Return the number of items in the red stack
    }

    // Get the size of the blue stack
    public int blueSize() {
        return stackArray.length - blueTop; // Return the number of items in the blue stack
    }

    // Check if the stack is full
    public boolean full() {
        return redTop + 1 == blueTop; // Return true if redTop + 1 is equal to blueTop, indicating a full stack
    }

      // Print the contents of both stacks
    public void printStack() {
        System.out.print("Red stack: ");
        for (int i = redTop; i >= 0; i--) {
            System.out.print(stackArray[i] + " ");
        }
        
        System.out.print("\nBlue stack: ");
        for (int i = blueTop; i < stackArray.length; i++) {
            System.out.print(stackArray[i] + " ");
        }
        
        System.out.println();
    }

   public static void main(String[] args) {
    	  
    	  task3_blueredadt stack = new task3_blueredadt(10);

        //push red and blue items 
    	  stack.redPush("A");
    	  stack.redPush("B");
    	  stack.redPush("C"); 
    	  stack.redPush(1);
	  stack.redPush(2);


    	  stack.bluePush("X");
    	  stack.bluePush("Y");
    	  stack.bluePush("Z");
    	  stack.bluePush(3);
    	  stack.bluePush(4);
    	  
        //print the stacks and the result of the methods
    	  System.out.println(stack.redPop()); 
    	  System.out.println(stack.bluePop()); 
    	  System.out.println(stack.redPop()); 
    	  System.out.println(stack.bluePop());

  
    	  System.out.println(stack.isRedEmpty());
    	  System.out.println(stack.isBlueEmpty()); 
    	  System.out.println(stack.full()); 
    	  
    	  stack.printStack();
    }
}
        
       
