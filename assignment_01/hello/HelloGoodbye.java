/* *****************************************************************************
 *  Name:              Pedro Gabriel Amorim Soares
 *  Coursera User ID:  ?
 *  Last modified:     November 04, 2024
 **************************************************************************** */

public class HelloGoodbye {
    public static void main(String[] args) {
        // Check if there are exactly two command-line arguments
        if (args.length != 2) {
            System.out.println("Please provide exactly two names.");
            return;
        }

        // Store the command-line arguments in variables for readability
        String name1 = args[0];
        String name2 = args[1];

        // Print the hello message in the order of the command-line arguments
        System.out.println("Hello " + name1 + " and " + name2 + ".");

        // Print the goodbye message with the names in reverse order
        System.out.println("Goodbye " + name2 + " and " + name1 + ".");
    }
}

