public class LineSeg extends AbstractElement implements CollisionDetector {
    private static int numberOfInstances = 0;  // Track the number of line segment instances
    private Point begin;  //start point of lineseg
    private Point end;    // End point of lineseg

    // default 
    public LineSeg() {
        this(new Point(), new Point());
    }

    // constructor
    public LineSeg(Point begin, Point end) {
        super();
        this.begin = begin;
        this.end = end;
        numberOfInstances++;
    }

    // Getter
    public Point getBegin() {
        return begin;
    }

    // Getter
    public Point getEnd() {
        return end;
    }

    // no.of.instances
    public static int getNumOfInstances() {
        return numberOfInstances;
    }

    // Check if this line segment intersects with a point
    @Override
    public boolean intersect(Point p) {
        return p.intersect(this);  // Default to  Point class implementation
    }

    // check if linesegment intersects with rectangle
    @Override
    public boolean intersect(Rectangle rect) {
        return lineInside(rect) || linePartInside(rect);
    }

    // both ends of lineseg inside rect
    private boolean lineInside(Rectangle rect) {
        return pointInRectangle(rect, begin) && pointInRectangle(rect, end);
    }

    //one endpoint inside rect
    private boolean linePartInside(Rectangle rect) {
        return pointInRectangle(rect, begin) || pointInRectangle(rect, end) ||
               segmentIntersectsRectangle(rect);
    }

    // point inside rect
    private boolean pointInRectangle(Rectangle rect, Point p) {
        return p.getX() > rect.getLeft() && p.getX() < rect.getRight() &&
               p.getY() > rect.getBottom() && p.getY() < rect.getTop();
    }

    // seg intersect rect edges
    private boolean segmentIntersectsRectangle(Rectangle rect) {
        LineSeg topEdge = new LineSeg(new Point(rect.getLeft(), rect.getTop()), new Point(rect.getRight(), rect.getTop()));
        LineSeg bottomEdge = new LineSeg(new Point(rect.getLeft(), rect.getBottom()), new Point(rect.getRight(), rect.getBottom()));
        LineSeg leftEdge = new LineSeg(new Point(rect.getLeft(), rect.getTop()), new Point(rect.getLeft(), rect.getBottom()));
        LineSeg rightEdge = new LineSeg(new Point(rect.getRight(), rect.getTop()), new Point(rect.getRight(), rect.getBottom()));

        return this.intersect(topEdge) || this.intersect(bottomEdge) ||
               this.intersect(leftEdge) || this.intersect(rightEdge);
    }

    @Override
public boolean intersect(Circle circle) {
    //cals helper funct to determine if lineseg intersects circle
    return segmentIntersectsCircle(circle);
}


private boolean segmentIntersectsCircle(Circle circle) {
    // Get the center of the circle and its radius
    Point center = circle.getCenter();
    float radius = circle.getRadius();

 //turns change in x,y into vetcors
    float dx = end.getX() - begin.getX();
    float dy = end.getY() - begin.getY();

    // we also need vector from lineseg's start to the circ center
   //closest point on seg to the center of circle
    float fx = center.getX() - begin.getX();
    float fy = center.getY() - begin.getY();

    // Calculate projection of vector (AC) onto segment vector (AB)
    // closest point on infinite line relative to segment
    float frac = (fx * dx + fy * dy) / (dx * dx + dy * dy);
/*
 * frac - fractional position along the line segment where 
 *  closest point to circ's center lies.
 *
 *  If frac = 0, closest point is start of the segment (begin)
 *  If frac = 1, closest point is the end of the segment (end)
 * If 0 < frac < 1,closest point lies somewhere between the 
 *   start and end of segment.
 * If frac < 0, closest point is not on seg
 *  If frac  > 1, closest point not on seg
 */


    // restrict 'frac' range [0, 1] 
    frac = Math.max(0, Math.min(1, frac));
// frac = 0 represents starting point of seg, frac = 1 represents ending point of seg
    // Calculate  closest point on the segment to the circle's center
    float closestX = begin.getX() + frac * dx;
    float closestY = begin.getY() + frac * dy;

    // Calculate diff... between closest point and circle's center
    //we need  distance from the center to the closest point
    float distanceX = closestX - center.getX();
    float distanceY = closestY - center.getY();

    //  dist^2 from closest point to circle's center
    float distanceSquared = distanceX * distanceX + distanceY * distanceY;

    // Check dist^2 < than r^2
    // If true, the segment properly intersects the circle
    // strict inequality - dont include tangent intersections
    return distanceSquared < radius * radius;
}
       // Check if this line segment intersects with another line segment using cross product. 
       //If the cross product betwene 2 lines is 0 then they intersect
       @Override
       public boolean intersect(LineSeg segLine) {
           return segmentsIntersect(this.begin, this.end, segLine.getBegin(), segLine.getEnd());
       }
   
       // Helper method to determine if two line segments intersect
       private boolean segmentsIntersect(Point p1, Point p2, Point p3, Point p4) {
           // Calculate the orientation of points using the cross product
           float d1 = crossProduct(p1, p2, p3);
           float d2 = crossProduct(p1, p2, p4);
           float d3 = crossProduct(p3, p4, p1);
           float d4 = crossProduct(p3, p4, p2);
   
           // Check if the segments intersect
           if (d1 * d2 < 0 && d3 * d4 < 0) {
               return true;  // Proper intersection
           }
   
           // Check for collinear cases where the segments overlap
           return isOnSegment(p1, p2, p3) || isOnSegment(p1, p2, p4) ||
                  isOnSegment(p3, p4, p1) || isOnSegment(p3, p4, p2);
       }
   
       // Helper method to calculate the cross product of vectors (p1p2) x (p1p3)
       private float crossProduct(Point p1, Point p2, Point p3) {
           return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) -
                  (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
       }
   
       // Helper method to check if a point lies on a line segment
       private boolean isOnSegment(Point p1, Point p2, Point p) {
           return Math.min(p1.getX(), p2.getX()) <= p.getX() && p.getX() <= Math.max(p1.getX(), p2.getX()) &&
                  Math.min(p1.getY(), p2.getY()) <= p.getY() && p.getY() <= Math.max(p1.getY(), p2.getY());
       }
       @Override
       public boolean intersect(CompElement compElement) {
       // give intersection task to compelem class
       return compElement.intersect(this);
   }
   }