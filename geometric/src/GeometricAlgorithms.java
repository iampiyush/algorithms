import java.util.List;
import java.util.ListIterator;
import com.rreeves.algorithms.ConvexHull;
import com.rreeves.algorithms.Point;
import com.rreeves.algorithms.LineSegment;

/*
  Test driver..
 */
public class GeometricAlgorithms {
    public static void main(String []args) {
	Point []arr = new Point[7]; //Convex hull is {a b d f g}
	arr[5] = new Point(0, 0, "a");
	arr[6] = new Point(3, 3, "b");
	arr[3] = new Point(1, 2, "c");
	arr[2] = new Point(1, 7, "d");
	arr[4] = new Point(-2, 6, "e");
	arr[0] = new Point(-4, 8, "f");
	arr[1] = new Point(-3, 3, "g");
	
	ConvexHull hull = new ConvexHull(arr);
	
	List<Point> points = hull.grahamScan();
//	List<Point> points = hull.jarvisMarch();
	
	ListIterator<Point> itr = points.listIterator();
	while (itr.hasNext()) {
	    Point pt = itr.next();
	    System.out.println(pt.toString());
	}
       	
	//Line segments..
	Point p1 = new Point(0, 10);
	Point p2 = new Point(10, 0);
    
	Point p3 = new Point(9, 0);
	Point p4 = new Point(9, -12);

	if (LineSegment.intersect(p1, p2, p3, p4))
	    System.out.println("Lines intersect");
	else
	    System.out.println("Lines do not intersect");
    }
}