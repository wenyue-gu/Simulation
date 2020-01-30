package enumerated;

import java.util.List;


/**
 * Simple example of using enumerated types.
 *
 * @author Robert C. Duvall
 */
public class Main {
    // a simple example to show how to use enumerated types
    public static void main (String[] args) {
        double earthWeight = (args.length == 1) ? Double.parseDouble(args[0]) : 100;

        // can use enum values just like instances
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        // can print their name and value out easily
        for (Planet p : Planet.values()) {
            System.out.printf("Your weight on %s is %f%n", p, p.surfaceWeight(mass));
        }
        System.out.println();

        // can read them in by their name easily
        List<String> names = List.of("Mercury", "venus", "MARS", "EARTH", "MOON");
        for (String name : names) {
            try {
                System.out.println(name + "'s gravity : " + Planet.valueOf(name.toUpperCase()).surfaceGravity());
            }
            catch (IllegalArgumentException e) {
                // NOT an ideal solution, but okay in this simple test case
                e.printStackTrace();
            }
        }
    }
}
