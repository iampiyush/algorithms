package com.rreeves.algorithms;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

//Custom stack used by graham scan algorithm.
class PointStack {
    private LinkedList<Point> mItems = new LinkedList<Point>();
    
    public void push(Point pt) {
	mItems.add(pt);
    }
    
    public Point pop() {
	return mItems.removeLast();
    }
    
    public Point peek() {
	return mItems.getLast();
    }

    public Point peekNextToTop() {
	return mItems.get(mItems.size() - 2);
    }

    public List<Point> toList() {
	return mItems;
    }
}

public class ConvexHull {
    private int mMinPoint;
    private Point []mPoints;

    /*
      Sorting comparator to sort points from rightmost to leftmost 
      relative to mMinYPoint. 
      
      In the case of collinear points, sorting is done on x coordinate.
     */
    private Comparator<Point> pointComparator = new Comparator<Point>() {
	@Override
	public int compare(Point one, Point two) {
	    int diff = mPoints[mMinPoint].getOrientation(one, two);
	    if (diff == 0)
		diff = one.getX() - two.getX();
	    
	    return diff;
	}
    };
    
    /*
      Finds point with minimum y value.
      Ties are broken by chosing the leftmost point.
     */
    private int findMinimum() {
	int min = 0;

	for (int i = 1; i < mPoints.length; ++i) {
	    int diff = mPoints[i].getY() - mPoints[min].getY();
	    if (diff < 0 || (diff == 0 && mPoints[i].getX() < mPoints[min].getX())) {
		min = i;
	    }
	}
	return min;
    }
    
    /*
      Returns the most clockwise point relative to a.
      If vectors are collinear return the farthest point from a.
    */
    private int mostClockwise(int a, int b, int c) {
	Point origin = mPoints[a];
	int orientation = origin.getOrientation(mPoints[b], mPoints[c]);
	if (orientation > 0) 
	    return c;
	else if (orientation < 0) 
	    return b;
	
	Point ptB = mPoints[b];
	Point ptC = mPoints[c];
	double cDist = Math.sqrt(Math.pow(ptC.getX() - origin.getX(), 2.0d) + Math.pow(ptC.getY() - origin.getY(), 2.0d));
	double bDist = Math.sqrt(Math.pow(ptB.getX() - origin.getX(), 2.0d) + Math.pow(ptB.getY() - origin.getY(), 2.0d));
	return cDist > bDist ? c : b;
    }
    
    //Finds most clockwise point relative to origin in 
    //the array of points.
    private int findNextPoint(int origin) {
	int ret = mostClockwise(origin, 0, 1);
	for (int i = 2; i < mPoints.length; ++i) {
	    ret = mostClockwise(origin, ret, i);
	}
	return ret;
    }

    public ConvexHull(Point []points) {
	mPoints = points;
    }
    
    /*
      Jarvis March algorithm to find convex hull.
      
      Algorithm:
      1) Find lowest point, breaking ties by picking leftmost.
      2) Add this point to the convex hull.
      3) Find the most clockwise point from this point and put it in the hull.
      4) Repeat from the new point until the original point is reached. 
      Walks around the hull finding the rightmost point from the current point.
      5) Algorithm is O(hn). For each point in the hull (h), you search all points
      finding the most clockwise (h * n).
     */
    public List<Point> jarvisMarch() {
	List<Point> convexHull = new LinkedList<Point>();
	swap(mPoints, 0, findMinimum());
	int min = 0;

	convexHull.add(mPoints[min]);
	
	int nextPoint = findNextPoint(min);
	
	while (nextPoint != min) {
	    convexHull.add(mPoints[nextPoint]);
	    nextPoint = findNextPoint(nextPoint);
	}
	return convexHull;
    }
    
    private void swap(Point[] arr, int one, int two) {
	Point t = arr[one];
	arr[one] = arr[two];
	arr[two] = t;
    }

    /*
      Graham Scan algorithm to find convex hull.
      
      Algorithm:
      1) Find the lowest point, breaking ties by picking leftmost.
      
      2) Sort remaining points by their clockwise orientation,
      relative to the point in step 1.
      
      3) Push first three points on the stack.
      
      4) For the remaining points, find a spot in stack where the top of the stack
      to the new point is a left turn. Once a left turn is found put it on the stack
      and move to the next point in the sorted array.

      5) When all points have been processed the stack will contain all points on the hull.
      
      6) Algorithm is O(nlogn) due to the sorting step being nlogn.
    */
    public List<Point> grahamScan() {
	List<Point> hull = new LinkedList<Point>();
	mMinPoint = findMinimum();

	swap(mPoints, 0, mMinPoint);
	mMinPoint = 0;

	Arrays.sort(mPoints, 1, mPoints.length, pointComparator);
	
	PointStack stack = new PointStack();
	stack.push(mPoints[0]);
	stack.push(mPoints[1]);
	stack.push(mPoints[2]);

	for (int i = 3; i < mPoints.length; ++i) {
	    while (stack.peekNextToTop().getOrientation(stack.peek(), mPoints[i]) > 0)
		stack.pop();
	    
	    stack.push(mPoints[i]);
	}
	return stack.toList();
    }
}
