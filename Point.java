package edu.iastate.cs228.hw2;

/**
 * 
 * @author Tessa Morgan
 *
 */

public class Point implements Comparable<Point> {
	private int x;
	private int y;

	public static boolean xORy; // compare x coordinates if xORy == true and y coordinates otherwise
								// To set its value, use Point.xORy = true or false.

	/**
	 * Default constructor
	 */
	public Point() { 
		// x and y get default value 0
	}

	/**
	 * Constructs a new Point given an x and y value
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Copy constructor, makes a deep copy of the given point
	 * @param p
	 */
	public Point(Point p) { 
		x = p.getX();
		y = p.getY();
	}

	/**
	 * 
	 * @return
	 * 		Returns this point's x-value
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return
	 * 		Returns this point's y-value
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the value of the static instance variable xORy.
	 * 
	 * @param xORy
	 */
	public static void setXorY(boolean xORy) {
		Point.xORy = xORy;
	}

	/**
	 * Checks if this point and Object obj are equal.
	 * @return true if obj is a point and has the same x- and y- values as this point
	 * 			false otherwise or if point is null;
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}

	/**
	 * Compare this point with a second point q depending on the value of the static
	 * variable xORy
	 * If xORy is true, compares the x-values of the points
	 * If xORy is false, compares the y-values
	 * 
	 * @param q
	 * @return -1 if (xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y))) 
	 * 			|| (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x))) 
	 *          0 if this.x == q.x && this.y == q.y) 
	 *          1 otherwise
	 */
	public int compareTo(Point q) {
		
		int compare;
		
		if (xORy) {
			compare = compareX(q);
			if (compare == 0) {
				compare = compareY(q);
			}
		}
		else {
			compare = compareY(q);
			if (compare == 0) {
				compare = compareX(q);
			}
		}
		
		return compare;
	}
	
	/**
	 * Compares this point to point q based on the x-values
	 * @param q
	 * @return
	 * 		returns -1 if this x value is less than q's
	 * 		1 if this x value is greater than q's y-value
	 * 		0 otherwise
	 */
	private int compareX(Point q) {
		int result = 0;
		if (this.x < q.x) {
			result = -1;
		}
		else if (this.x > q.x) {
			result = 1;
		}
		return result;
	}
	
	/**
	 * Compares this point to point q based on the y-values
	 * @param q
	 * @return
	 * 		returns -1 if this y value is less than q's
	 * 		1 if this y value is greater than q's y-value
	 * 		0 otherwise
	 */
	private int compareY(Point q) {
		int result = 0;
		if (this.y < q.y) {
			result = -1;
		}
		else if (this.y > q.y) {
			result = 1;
		}
		return result;
	}

	/**
	 * Output a point in the standard form (x, y).
	 */
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
