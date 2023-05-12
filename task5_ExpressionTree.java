import java.util.*;

// Define a class for a node in a binary expression tree
class Node {
    String val;
    Node left, right;
    public Node(String val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class task5_ExpressionTree {
    // Define a map to store variables and their values
    static Map<String, Integer> variables = new HashMap<>();

    // A method to build a binary expression tree from a string expression
    public static Node buildExpressionTree(String expression) {
        // Initialize a stack to store nodes
        Stack<Node> stack = new Stack<>();
        // Initialize counters for opening and closing parentheses
        int numOpenParentheses = 0;
        int numCloseParentheses = 0;
        // Iterate through the characters in the expression
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            // If a character is an opening parenthesis, increment the counter and continue
            if (c == '(') {
                numOpenParentheses++;
                continue;
                // If a character is a closing parenthesis, decrement the counter, pop the last two nodes
                // and the last operator from the stack, and set them as children of the operator node.
                // Then push the operator node back onto the stack.
            } else if (c == ')') {
                numCloseParentheses++;
                if (numCloseParentheses > numOpenParentheses) {
                    throw new IllegalArgumentException("Invalid expression: too many closing parentheses");
                }
                Node right = stack.pop();
                Node operator = stack.pop();
                Node left = stack.pop();
                operator.left = left;
                operator.right = right;
                stack.push(operator);
                // If a character is a digit, create a new node with the digit as its value and push it onto the stack.
            } else if (Character.isDigit(c)) {
                StringBuilder stringbuild = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    stringbuild.append(expression.charAt(i++));
                }
                i--;
                Node node = new Node(stringbuild.toString());
                stack.push(node);
                // If a character is a letter, create a new node with the letter (or the letter followed by digits)
                // as its value and push it onto the stack.
            } else if (Character.isLetter(c)) {
                StringBuilder stringbuild = new StringBuilder();
                while (i < expression.length() && Character.isLetterOrDigit(expression.charAt(i))) {
                    stringbuild.append(expression.charAt(i++));
                }
                i--;
                Node node = new Node(stringbuild.toString());
                stack.push(node);
                // If a character is an operator, create a new node with the operator as its value and push it onto the stack.
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                Node node = new Node(Character.toString(c));
                stack.push(node);
                // If a character is none of the above, throw an exception.
            } else {
//                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }
        // If the number of opening and closing parentheses is different, throw an exception.
        if (numOpenParentheses != numCloseParentheses) {
            throw new IllegalArgumentException("Invalid expression: mismatched parentheses");
        }
        // The last node on the stack is the root of the expression tree, so pop and return it.
        return stack.pop();
    }

    // A method to print the binary expression tree in a readable format
    public static void printTree(Node root, String prefix) {
        if (root == null) {
            return;
        }
        // Print the value of the current node, and if it represents a variable, print its value too.
        System.out.print(prefix + root.val);
        if (root.val.matches("^x[0-9]+$")) {
            Integer value = variables.get(root.val);
            if (value == null) {
                value = 0;
            }
            System.out.print(" (value: " + value + ")");
        }
        System.out.println();
        // Recursively print the left and right subtrees, with a prefix indicating their level in the tree.
        printTree(root.left, prefix + "-");
        printTree(root.right, prefix + "-");
    }

    // A method to print the value of the root node
    public static void printroot(Node root, String prefix) {
        if (root == null) {
            return;
        }
        System.out.print(prefix + root.val);
        if (root.val.matches("^x[0-9]+$")) {
            Integer value = variables.get(root.val);
            if (value == null) {
                value = 0;
            }
            System.out.print(" (value: " + value + ")");
        }
        System.out.println();

    }
    //    public static int evaluate(Node root) {
//        if (root == null) {
//            return 0;
//        }
//        if (root.left == null && root.right == null) {
//            if (root.val.matches("^x[0-9]+$")) {
//                int value = variables.get(root.val);
//                return value;
//            } else {
//                return Integer.parseInt(root.val);
//            }
//        }
//        int leftVal = evaluate(root.left);
//        int rightVal = evaluate(root.right);
//        switch (root.val) {
//            case "+":
//                return leftVal + rightVal;
//            case "-":
//                return leftVal - rightVal;
//            case "*":
//                return leftVal * rightVal;
//            case "/":
//                return leftVal / rightVal;
//        }
//        return 0;
//    }

    // The main method, which builds an expression tree from a string, prints it, sets some variable values,
    // and prints the value of the root node.
    public static void main(String[] args) {
        String expression = "(((5+3)*2)/6)";
        Node root = buildExpressionTree(expression);
        System.out.println("here is the Binary Expression Tree with the first printed value being the root");

        printTree(root, "");
        variables.put("x1", 10);
        variables.put("x2", 5);
        System.out.print("Value of root: " );
        printroot(root, "");

    }
}