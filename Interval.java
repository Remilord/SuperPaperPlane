@SuppressWarnings("overrides")

public class Interval {
    private  int min;
    private  int max;


    public Interval(int min, int max) {
        if (Double.isInfinite(min) || Double.isInfinite(max))
            throw new IllegalArgumentException("Endpoints must be finite");
        if (Double.isNaN(min) || Double.isNaN(max))
            throw new IllegalArgumentException("Endpoints cannot be NaN");



        if (min <= max) {
            this.min = min;
            this.max = max;
        }
        else throw new IllegalArgumentException("Illegal interval");
    }

    /**
     * Returns the min endpoint of this interval.
     *
     * @return the min endpoint of this interval
     */
    public int min() {
        return min;
    }

    /**
     * Returns the max endpoint of this interval.
     *
     * @return the max endpoint of this interval
     */
    public int max() {
        return max;
    }

    /**
     * Returns true if this interval intersects the specified interval.
     *
     * @param  that the other interval
     * @return {@code true} if this interval intersects the argument interval;
     *         {@code false} otherwise
     */
    public boolean intersects(Interval that) {
        if (this.max < that.min) return false;
        if (that.max < this.min) return false;
        return true;
    }

    /**
     * Returns true if this interval contains the specified value.
     *
     * @param x the value
     * @return {@code true} if this interval contains the value {@code x};
     *         {@code false} otherwise
     */
    public boolean contains(int x) {
        return (min <= x) && (x <= max);
    }

    /**
     * Returns the length of this interval.
     *
     * @return the length of this interval (max - min)
     */
    public int length() {
        return max - min;
    }
    public void setValues(int a , int b) {
    	this.min=a;
    	this.max=b;
    }
    /**
     * Returns a string representation of this interval.
     *
     * @return a string representation of this interval in the form [min, max]
     */
    public String toString() {
        return "[" + min + ", " + max + "]";
    }

    /**
     * Compares this transaction to the specified object.
     *
     * @param  other the other interval
     * @return {@code true} if this interval equals the other interval;
     *         {@code false} otherwise
     */
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Interval that = (Interval) other;
        return this.min == that.min && this.max == that.max;
    }

}
