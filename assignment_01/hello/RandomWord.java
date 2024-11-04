/* *****************************************************************************
 *  Name:              Pedro Gabriel Amorim Soares
 *  Coursera User ID:  ?
 *  Last modified:     November 04, 2024
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = null;
        int count = 0;

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            count++;

            // Select the current word as the new champion with probability 1/count
            if (StdRandom.bernoulli(1.0 / count)) {
                champion = word;
            }
        }

        // Print the surviving champion
        if (champion != null) {
            StdOut.println(champion);
        }
    }
}
