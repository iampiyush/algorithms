package com.rreeves.algorithms;

public class Point {
    private String mName;
    private int mX;
    private int mY;
    
    public Point(int x, int y) {
	this(x, y, "");
    }

    public Point(int x, int y, String name) {
	mX = x;
	mY = y;
	mName = name;
    }
    
    public int getX() {
	return mX;
    }
    
    public int getY() {
	return mY;
    }
    
    @Override
    public String toString() {
	return mName;
    }
    
    /* 
       getOrientation() - returns angle orientation of 
       sweeping motion from point a to b.
       
       Returns positive if point a is counter clockwise from b.
       Returns negative is point a is clockwise from b.
       Returns 0 if points a and b are collinear.
    */
    public int getOrientation(Point a, Point b) {
	int ax = a.getX() - mX;
	int ay = a.getY() - mY;
	int bx = b.getX() - mX;
	int by = b.getY() - mY;
	return (ay*bx) - (by*ax);
    }
}
