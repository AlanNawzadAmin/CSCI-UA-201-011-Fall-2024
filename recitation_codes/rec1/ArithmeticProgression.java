
/**
 * Represents an arithmetic progression.
 * Extends the basic progression.
 */
public class ArithmeticProgression extends Progression {

    // Instance variable for the increment step
    protected long increment;

    /**
     * Constructs progression 0, 1, 2, ...
     * Starts at 0 with an increment of 1.
     */
    public ArithmeticProgression() {
        this(1, 0);  // Default step size is 1, start at 0
    }

    /**
     * Constructs progression 0, step size, 2*step size, ...
     * Starts at 0 with a specified step size.
     *
     * @param stepSize the increment step size
     */
    public ArithmeticProgression(long stepSize) {
        this(stepSize, 0);  // Start at 0 with specified step size
    }

    /**
     * Constructs arithmetic progression with arbitrary start and increment.
     *
     * @param stepSize the increment step size
     * @param start the starting value of the progression
     */
    public ArithmeticProgression(long stepSize, long start) {
        super(start);  // Call the superclass constructor
        increment = stepSize;
    }

    /**
     * Advances the current value by adding the arithmetic increment.
     */
    @Override
    protected void advance() {
        current += increment;
    }
    
}
