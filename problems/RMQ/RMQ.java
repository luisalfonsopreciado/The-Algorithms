package problems.RMQ;

/**
 * RMQ interface defines the behaviour of a class that solves the Range Minimum
 * Query Problem.
 */
public interface RMQ {
    /**
     * Query any possible range for the RMQ implementation
     * 
     * @param lo
     * @param hi
     * @return int - range minimum
     */
    public int query(int lo, int hi);
}
