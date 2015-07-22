public class Brute {
    public static void main(String[] args) {
        
        String filename = args[0];
        In in = new In(filename);
        
        int N = in .readInt();
        Point[] points = new Point[N];
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        for (int i = 0; i < N; i++) {
            int x = in .readInt();
            int y = in .readInt();
            
            Point p = new Point(x, y);
            p.draw();
            points[i] = p;
            
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double slopeToQ = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < N; k++) {
                    double slopeToR = points[i].slopeTo(points[k]);
                    for (int l = k + 1; l < N; l++) {
                        double slopeToS = points[i].slopeTo(points[l]);
                        
                        if (slopeToQ == slopeToR && slopeToQ == slopeToS) drawCollinearPoint(points[i], points[j], points[k], points[l]);
                        
                    }
                }
                
            }
        }
    }
    
    private static void drawCollinearPoint(Point p, Point q, Point r, Point s) {
        Point[] collinearPoints = new Point[4];
        collinearPoints[0] = p;
        collinearPoints[1] = q;
        collinearPoints[2] = r;
        collinearPoints[3] = s;
        
        Insertion.sort(collinearPoints);
        
        collinearPoints[0].drawTo(collinearPoints[3]);
        
        for (int i = 0; i < 3; i++)
            StdOut.print(collinearPoints[i] + " -> ");
        
        StdOut.println(collinearPoints[3]);
    }
}