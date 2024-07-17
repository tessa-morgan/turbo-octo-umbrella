package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 *  
 * @author Tessa Morgan
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter {

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public MergeSorter(Point[] pts) {
		super(pts);
		algorithm = "mergesort";
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {
		mergeSortRec(points);
		
	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. One way is to make copies of the two halves of pts[], recursively
	 * call mergeSort on them, and merge the two sorted subarrays into pts[].
	 * 
	 * @param pts point array
	 */
	private void mergeSortRec(Point[] pts) {
		int length = pts.length;
		if (length <= 1) {
			return;
		}
		int m = length / 2;
		Point[] A = new Point[m];
		Point[] B = new Point[length - m];
		for (int i = 0; i < m; i++) {
			A[i] = pts[i];
		}
		for (int i = m; i < length; i++) {
			B[i - m] = pts[i]; 
		}
		mergeSortRec(A);
		mergeSortRec(B);
		
		Point[] temp = merge(A, B);
		for (int i  = 0; i < pts.length; i++) {
			pts[i] = temp[i];
		}
	}
	

	/**
	 *  Merges arrays A and B together onto a new array D in sorted order.
	 *  Iterates through A and B adding the smaller point (based on the set comparator)
	 *  Once it iterates to the end of either A or B, it adds the remaining points to D
	 * @param point array A
	 * @param point array B
	 * @return
	 * 		Returns the new merged point array D
	 */
	private Point[] merge(Point[] A, Point[] B) {
		int lenA = A.length;
		int lenB = B.length;
		Point[] D = new Point[lenA + lenB];
		
		int a = 0;
		int b = 0;
		while (a < lenA && b < lenB) {
			if (pointComparator.compare(A[a], B[b]) <= 0) {
				D[a + b] = A[a];
				a++;
			}
			else {
				D[a + b] = B[b];
				b++;
			}
		}
		//p and i go with A
		//q and j go with B
		if (a >= lenA) {
			for (; b < lenB; b++) {
				D[a + b] = B[b];
			}
		}
		else {
			for (; a < lenA; a++) {
				D[a + b] = A[a];
			}
		}
		return D;
	}


}
