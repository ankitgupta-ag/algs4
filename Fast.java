import java.util.*;
public class Fast {
    public static void main(String[] args)
    {
        
        String filename = args[0];
        In in = new In(filename);
        
        int N = in .readInt();
        Point[] points = new Point[N];
        Point[] aux = new Point[N];
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(2000);
        
        for (int i = 0; i < N; i++) {
            int x = in .readInt();
            int y = in .readInt();
            
            Point p = new Point(x, y);
            p.draw();
            points[i] = p;
            aux[i] = p;
        }
        
        Arrays.sort(points);
        
        for (int i = 0; i < N; i++) {
            
            Arrays.sort(aux,points[i].SLOPE_ORDER);
            int lastJ = 1 , j;
            
            for (j = 2; j < N;j++)
            {
                if(points[i].slopeTo(aux[j]) != points[i].slopeTo(aux[j-1]))
                {
                    printCollinearPoints(points,aux,i,j,lastJ); 
                    lastJ = j;
                }
            }
            
            if(lastJ != j-1)
                printCollinearPoints(points,aux,i,j,lastJ);      
        }
        
        StdDraw.show(0);
    }
    
    
    private static void printCollinearPoints(Point[] points,Point[] aux, int i,int j, int lastJ)
    {
        Arrays.sort(aux,lastJ,j);
        if(points[i].compareTo(aux[lastJ])<0 && (j-lastJ)>=3) //smaller than the smallest one
        {
            StdOut.print(points[i] + " -> ");
            
            for (int k = lastJ; k < j-1; k++)
                StdOut.print(aux[k] + " -> ");
            StdOut.println(aux[j-1]);
            
            points[i].drawTo(aux[j-1]);
        }
        
    }
}