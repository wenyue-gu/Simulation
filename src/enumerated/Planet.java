package enumerated;


/**
 * A simple example of an enumerated type taken from:
 *   http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 * 
 * It shows how to use an enumerated type, but also that it is just
 * a specialized version of a class that declares all possible values
 * publicly in its definition.
 */
public enum Planet {
    // these are the ONLY instances that can ever exist
    MERCURY (3.303e+23, 2.4397e6),
    VENUS   (4.869e+24, 6.0518e6),
    EARTH   (5.976e+24, 6.37814e6),
    MARS    (6.421e+23, 3.3972e6),
    JUPITER (1.9e+27,   7.1492e7),
    SATURN  (5.688e+26, 6.0268e7),
    URANUS  (8.686e+25, 2.5559e7),
    NEPTUNE (1.024e+26, 2.4746e7);

    // universal gravitational constant  (m3 kg-1 s-2)
    public static final double G = 6.67300E-11;

    // immutable instance variables
    private final double mass;   // in kilograms
    private final double radius; // in meters


    // constructor cannot be called, but private just to emphasize that
    private Planet (double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    /**
     * Returns gravity at the surface of the planet
     */
    public double surfaceGravity () {
        return G * mass / (radius * radius);
    }

    /**
     * Returns weight of the given mass at the surface of the planet
     */
    public double surfaceWeight (double otherMass) {
        return otherMass * surfaceGravity();
    }

    // NOTE: provides getters, but not setters
    /**
     * Returns mass of this planet.
     */
    public double getMass () {
        return mass;
    }

    /**
     * Returns radius of this planet.
     */
    public double getRadius () {
        return radius;
    }
}
