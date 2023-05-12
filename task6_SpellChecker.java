import java.util.*;

public class task6_SpellChecker {
    // Define a private Set object to store the lexicon of valid words
    private Set<String> lexicon;

    // Constructor that takes a Set object of valid words as input
    public task6_SpellChecker(Set<String> lexicon) {
        this.lexicon = lexicon;  // Initialize the lexicon with the input Set object
    }

    // Method to generate a Set of words that differ from the input word by one edit operation
    private Set<String> word_variation1(String word) {
        Set<String> edits = new HashSet<>();  // Create a new Set object to store the generated words
        for (int i = 0; i < word.length(); i++) {
            // Deletion: remove the i-th character from the word
            String deleted = word.substring(0, i) + word.substring(i + 1);
            if (lexicon.contains(deleted)) {  // Add the word to the Set if it is in the lexicon
                edits.add(deleted);
            }

            for (char c = 'a'; c <= 'z'; c++) {
                // Substitution: replace the i-th character with a character from a to z
                String substituted = word.substring(0, i) + c + word.substring(i + 1);
                if (lexicon.contains(substituted)) {
                    edits.add(substituted);
                }

                // Insertion: insert a character from a to z at the i-th position
                String inserted = word.substring(0, i) + c + word.substring(i);
                if (lexicon.contains(inserted)) {
                    edits.add(inserted);
                }

                // Transposition: swap the i-th and (i+1)-th characters
                if (i < word.length() - 1) {
                    String transposed = word.substring(0, i) + word.charAt(i + 1) + word.charAt(i) + word.substring(i + 2);
                    if (lexicon.contains(transposed)) {
                        edits.add(transposed);
                    }
                }
            }
        }
        return edits;  // Return the Set of generated words
    }

    // Method to generate a Set of words that differ from the input word by two edit operations
    // This method dont work not used, so it is commented out
//    private Set<String> word_variation(String word) {
//        Set<String> edits = new HashSet<>();
//        for (String e1 : word_variation1(word)) {
//            edits.addAll(word_variation1(e1));
//        }
//        return edits;
//    }

    // Method to generate a Set of words that have the same Soundex code as the input word
    private Set<String> phoneticSubstitutions(String word) {
        Set<String> substitutions = new HashSet<>();  
        // Create a new Set object to store the generated words
        String soundex = getSoundex(word);  
        // Get the Soundex code of the input word
        for (String candidate : lexicon) {  
            // Iterate through the lexicon
            if (getSoundex(candidate).equals(soundex)) {  
                // If a word has the same Soundex code as the input word
                substitutions.add(candidate);  
                // Add the word to the Set
            }
        }
        return substitutions; 
         // Return the Set of generated words
    }

    // Method to generate the Soundex code of a word
    private String getSoundex(String word) {
        char[] letters = word.toUpperCase().toCharArray(); 
         // Convert the word to uppercase and store its characters in an array
        char firstletter = letters[0];  
        // Store the first letter of the word
        // Replace consonants with digits according to the Soundex algorithm
        for (int i = 0; i < letters.length; i++) {
            switch (letters[i]) {
                case 'B':
                case 'F':
                case 'P':
                case 'V':
                    letters[i] = '1';
                    break;
                case 'C':
                case 'G':
                case 'J':
                case 'K':
                case 'Q':
                case 'S':
                case 'X':
                case 'Z':
                    letters[i] = '2';
                    break;
                case 'D':
                case 'T':
                    letters[i] = '3';
                    break;
                case 'L':
                    letters[i] = '4';
                    break;
                case 'M':
                case 'N':
                    letters[i] = '5';
                    break;
                case 'R':
                    letters[i] = '6';
                    break;
                default:
                    letters[i] = '0';
                    break;
            }
        }
        // Remove duplicates from the digits and append the first letter to form the Soundex code
        StringBuilder sb = new StringBuilder();
        sb.append(firstletter);
        for (int i = 1; i < letters.length; i++) {
            if (letters[i] != letters[i - 1] && letters[i] != '0') {
                sb.append(letters[i]);
            }
        }
        // Pad the Soundex code with zeros and truncate it to 4 characters
        while (sb.length() < 4) {
            sb.append('0');
        }
        return sb.toString().substring(0, 4);  // Return the Soundex code
    }

    // Method to check the spelling of a word and generate suggestions for correction
    public List<String> check(String s) {
        List<String> suggestions = new ArrayList<>(); 
         // Create a new List object to store the suggestions
        if (lexicon.contains(s)) {  // If the input word is in the lexicon
            suggestions.add(s); 
             // Add the word to the List as a valid suggestion
        } else {  // If the input word is not in the lexicon
            Set<String> possiblewords = new HashSet<>();  // Create a new Set object to store the generated words
            possiblewords.addAll(word_variation1(s));  // Add the words generated by the first edit operation to the Set
//            possiblewords.addAll(word_variation2(s));  // Add the words generated by the second edit operation to the Set
            possiblewords.addAll(phoneticSubstitutions(s));  // Add the words generated by the Soundex code to the Set
            for (String candidate : possiblewords) {  // Iterate through the generated words
                if (lexicon.contains(candidate)) {  // If a word is in the lexicon
                    suggestions.add(candidate);  // Add the word to the List as a valid suggestion
                }
            }
        }
        return suggestions;  // Return the List of suggestions
    }

    // Main method to test the spell checker
    public static void main(String[] args) {
        // Initialize the lexicon with some example words
        Set<String> lexicon = new HashSet<>();
        lexicon.add("apple");
        lexicon.add("iphone");
        lexicon.add("mercedes");
        lexicon.add("orange");
        lexicon.add("steve");
        lexicon.add("messi");
        lexicon.add("spelling");
        lexicon.add("java");

        // Create a new task6_SpellChecker object with the lexicon
        task6_SpellChecker spellChecker = new task6_SpellChecker(lexicon);

        // Read input from the user and check the spelling of the input word
        Scanner scanner = new Scanner(System.in);
        while (true) {  // Loop until the user types "end"
            System.out.print("type in a word or type 'end' to exit: ");
            String word = scanner.nextLine();
            if (word.equalsIgnoreCase("end")) {  // If the user types "end"
                break;  // Exit the loop
            }

            List<String> suggestions = spellChecker.check(word);  // Check the spelling of the input word and generate suggestions
            if (suggestions.isEmpty()) {  // If no suggestions are generated
                System.out.println("No suggestions found.");
            } else {  // If suggestions are generated
//                System.out.println("Suggestions:");
                for (String suggestion : suggestions) {
                    System.out.println("- " + suggestion);  // Print each suggestion to the console
                }
            }
        }
    }

}