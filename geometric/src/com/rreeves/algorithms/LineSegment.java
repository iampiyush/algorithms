package com.rreeves.algorithms;

public class LineSegment {
    private static int min(int one, int two) {
	if (one < two)
	    return one;
	
	return two;
    }

    private static int max(int one, int two) {
	if (one < two)
	    return two;
	
	return one;
    }

    //Assumes vector a->c is collinear with a->b. 
    //Returns true of point c is on the vector a->b.
    private static boolean pointOnLine(Point a, Point b, Point c) {
	int minX = min(a.getX(), b.getX());
	int maxX = max(a.getX(), b.getX());
	int minY = min(a.getY(), b.getY());
	int maxY = max(a.getY(), b.getY());
	
	int cX = c.getX();
	int cY = c.getY();

	return ((minX <= cX && maxX >= cX) && (minY <= cY && maxY >= cY));
    }

    //Returns true if vector (a1,a2) intersects vector (b1, b2)
    public static boolean intersect(Point a1, Point a2, Point b1, Point b2) {
	int direction1 = a1.getOrientation(a2, b1);
	int direction2 = a1.getOrientation(a2, b2);
	int direction3 = b1.getOrientation(b2, a1);
	int direction4 = b1.getOrientation(b2, a2);
	
	boolean straddleOne = (direction1 > 0 && direction2 < 0) || (direction1 < 0 && direction2 > 0);
	boolean straddleTwo = (direction3 > 0 && direction4 < 0) || (direction3 < 0 && direction4 > 0);
	if (straddleOne && straddleTwo)//If both lines straddle each other, they intersect.
	    return true;
	
	//If any vectors are collinear, see if they are on top of each other.
	if (direction1 == 0 && pointOnLine(a1, a2, b1))
	    return true;
	if (direction2 == 0 && pointOnLine(a1, a2, b2))
	    return true;
	if (direction3 == 0 && pointOnLine(b1, b2, a1))
	    return true;
	if (direction4 == 0 && pointOnLine(b1, b2, a2))
	    return true;
	    	
	return false;
    }
}
