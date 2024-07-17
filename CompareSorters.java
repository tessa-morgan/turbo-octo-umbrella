package edu.iastate.cs228.hw2;

/**
 *  
 * @author Tessa Morgan
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Use them as coordinates to construct points. Scan these points with
	 * respect to their median coordinate point four times, each time using a
	 * different sorting algorithm.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Performances of Four Sorting Algorithms in Point scanning \n");
		System.out.println("keys: 1 (random integers) 2(file input) 3 (exit)");
		Scanner sc = new Scanner(System.in);
		
		int numTrials = 1;
		System.out.print("Trial " + numTrials + ": ");
		int key = sc.nextInt(); 
		Point[] pArr;
		int numPoints;
		String fileName;
		
		PointScanner[] scanners = new PointScanner[4];
		
		while (key < 3 && key > 0) {
			
			if (key == 1) {
				System.out.print("Enter number of random points: ");
				numPoints = sc.nextInt();
				pArr = generateRandomPoints(numPoints, new Random());
				scanners[0] = new PointScanner(pArr, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(pArr, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(pArr, Algorithm.MergeSort);
				scanners[3] = new PointScanner(pArr, Algorithm.QuickSort);
			}
			
			else { //Key == 2
				System.out.println("Points from a file");
				System.out.print("File name: ");
				fileName = sc.next();
				scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
			}
			
			System.out.println("\nalgorithm\t size\t time (ns)");
			System.out.println("------------------------------------");
			for (int i = 0; i < 4; i++) {
				scanners[i].scan();
				System.out.println(scanners[i].stats()); 
				scanners[i].writeMCPToFile();
			}
			System.out.println("------------------------------------");
			
			numTrials++;
			System.out.print("\nTrial " + numTrials + ": ");
			key = sc.nextInt();
		}
		
	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] � [-50,50].
	 * Please refer to Section 3 on how such points can be generated.
	 * 
	 * @param numPts number of points
	 * @param rand   Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	private static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
		
		Point[] pointList = new Point[numPts];
		
		for (int i = 0; i < numPts; i++) {
			pointList[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		
		return pointList;
	}
	

}
