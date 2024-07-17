package edu.iastate.cs228.hw2;

import java.io.File;

/**
 * 
 * @author Tessa Morgan
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a
 * reference point whose x and y coordinates are respectively the medians of the
 * x and y coordinates of the original points.
 * 
 * It records the employed sorting algorithm as well as the sorting time for
 * comparison.
 *
 */
public class PointScanner {
	private Point[] points;

	/**
	 * point whose x and y coordinates are respectively the medians of the
	 * x coordinates and y coordinates of those points in the array points[]
	 */
	private Point medianCoordinatePoint;
	
	/**
	 * The algorithm that this PointScanner will use
	 */
	private Algorithm sortingAlgorithm;

	/**
	 * execution time in nanoseconds
	 */
	protected long scanTime; 

	/**
	 * This constructor accepts an array of points and one of the four sorting
	 * algorithms as input. Copy the points into the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}
		points = new Point[pts.length];
		
		for (int i = 0; i < pts.length; i++) {
			points[i] = new Point(pts[i]);
		}
		
		sortingAlgorithm = algo;
	}

	/**
	 * This constructor reads points from a file.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException if the input file contains an odd number of
	 *                                integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
		File f = new File(inputFileName);
		Scanner sc = new Scanner(f);
		
		int length = 0;
		
		while(sc.hasNextInt()) {
			sc.nextInt();
			length++;
		}
		sc.close();
		
		if (length % 2 != 0) {
			throw new InputMismatchException();
		}
		
		sc = new Scanner(f);
		points = new Point[length / 2];
		for (int i = 0; i < points.length; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			points[i] = new Point(x, y);
		}
		sc.close();
		
		sortingAlgorithm = algo;
		
	}

	/**
	 * Carry out two rounds of sorting using the algorithm designated by
	 * sortingAlgorithm as follows:
	 * 
	 * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort
	 * points[] again by the y-coordinate to get the median y-coordinate. c)
	 * Construct medianCoordinatePoint using the obtained median x- and
	 * y-coordinates.
	 * 
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter,
	 * InsertionSorter, MergeSorter, or QuickSorter to carry out sorting.
	 * 
	 * @param algo
	 * @return
	 */
	public void scan() {
		AbstractSorter aSorter; 
		long initialTime;
		long finalTime;
		
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		}
		else if (sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		}
		else if (sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		}
		else { //sortingAlgorithm == Algorithm.QuickSort
			aSorter = new QuickSorter(points);
		}
		
		
		aSorter.setComparator(0);
		
		initialTime = System.nanoTime();
		aSorter.sort();
		finalTime = System.nanoTime();
		scanTime = finalTime - initialTime;
		
		int x = aSorter.getMedian().getX();
		
		aSorter.setComparator(1);
		
		initialTime = System.nanoTime();
		aSorter.sort();
		finalTime = System.nanoTime();
		scanTime += finalTime - initialTime;
		
		int y = aSorter.getMedian().getY();
		
		medianCoordinatePoint = new Point(x, y);

	}

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description.
	 */
	public String stats() {
		return sortingAlgorithm + "\t  " + points.length + "\t " + scanTime;
	}

	/**
	 * Write MCP after a call to scan(), in the format "MCP: (x, y)" The x and y
	 * coordinates of the point are displayed on the same line with exactly one
	 * blank space in between.
	 */
	@Override
	public String toString() {
		return "MCP: " + medianCoordinatePoint;
	}

	/**
	 * 
	 * This method, called after scanning, writes point data into a file by
	 * outputFileName. The format of data in the file is the same as printed out
	 * from toString(). The file can help you verify the full correctness of a
	 * sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException {
		File outputFile = new File("./src/edu/iastate/cs228/hw2/MCPToFile.txt");
		PrintWriter writer = new PrintWriter(outputFile);
		
		writer.write(toString());
		
		writer.close();
	}

}
