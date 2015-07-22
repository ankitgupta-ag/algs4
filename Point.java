/*************************************************************************
  * Name:
  * Email:
  *
  * Compilation:  javac Point.java
  * Execution:
  * Dependencies: StdDraw.java
  *
  * Description: An immutable data type for points in the plane.
  *
  *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable < Point > {
    
    // compare points by slope
    public final Comparator < Point > SLOPE_ORDER = new PointComparator();
    
    private final int x; // x coordinate
    private final int y; // y coordinate
    
    private class PointComparator implements Comparator < Point > {
        public int compare(Point v, Point w) {
            double slopeToV = Point.this.slopeTo(v);
            double slopeToW = Point.this.slopeTo(w);
            
            if (slopeToV < slopeToW) return -1;
            if (slopeToV == slopeToW) return 0;
            return 1;
        }
    }
    
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    
    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }
    
    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that.y == this.y && that.x == this.x) return Double.NEGATIVE_INFINITY;
        if (that.y == this.y) return 0.0;
        if (that.x == this.x) return Double.POSITIVE_INFINITY;
        else {
            double vertDist = (that.y - this.y);
            double horizontalDist = (that.x - this.x);
            double slope = vertDist / horizontalDist;
            return slope;
        }
    }
    
    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
        else if (this.y == that.y && this.x == that.x) return 0;
        else return 1;
    }
    
    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    // unit test
    public static void main(String[] args) {
        Point p1 = new Point(6000, 7000);
                             Point p2 = new Point(3000, 4000);
                             StdOut.print(p2.compareTo(p1));
    }
}