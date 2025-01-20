public class Rectangle extends AbstractElement implements CollisionDetector {
    protected static int numberOfInstances = 0;  // Track the number of instances
    private float left, right, top, bottom;  // Rectangle boundaries

    // Default constructor: Initializes an empty rectangle
    public Rectangle() {
        this(0, 0, 0, 0);
    }

    // Constructor with parameters for all four boundaries
    public Rectangle(float left, float right, float top, float bottom) {
        super();
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        numberOfInstances++;
    }

    // Getter methods for the bounds
    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return bottom;
    }

    // Static method to get the total number of instances
    public static int getNumOfInstances() {
        return numberOfInstances;
    }

    // Check if this rectangle intersects with a point
    @Override
    public boolean intersect(Point p) {
    //default to og pointclass
        return p.intersect(this);
    }

    // Check if this rectangle intersects with a line segment
    @Override
    public boolean intersect(LineSeg segline) {
        // Default to og lineseg class
        return segline.intersect(this);
    }

    // Check if this rectangle partially intersects a circle
    public boolean partIntersect(Circle circle) {
        /*
     

        top or bottom inside vertical range of circle (center.y ± raidus )
        left or right inside horizontage range of circle
         */
        return ((this.getBottom() > circle.getCenter().getY() - circle.getRadius() &&
                 this.getBottom() < circle.getCenter().getY() + circle.getRadius()) ||

                (this.getTop() < circle.getCenter().getY() + circle.getRadius() &&
                 this.getTop() > circle.getCenter().getY() - circle.getRadius()) ||

                (this.getLeft() > circle.getCenter().getX() - circle.getRadius() &&
                 this.getLeft() < circle.getCenter().getX() + circle.getRadius()) ||

                (this.getRight() < circle.getCenter().getX() + circle.getRadius() &&
                 this.getRight() > circle.getCenter().getX() - circle.getRadius()));
    }

    // Check if the rectangle is fully inside circle
    private boolean fullIntersect(Circle circle) {
        /*
          rect is fully inside the circle iff
         - All four rect bounds inside the circle's horizont. and vert. range (center ± radius).
        
         */
        return this.getBottom() > circle.getCenter().getY() - circle.getRadius() &&
               this.getTop() < circle.getCenter().getY() + circle.getRadius() &&
               this.getLeft() > circle.getCenter().getX() - circle.getRadius() &&
               this.getRight() < circle.getCenter().getX() + circle.getRadius();
    }

    // Check if this rectangle intersects with a circle
    @Override
    public boolean intersect(Circle circle) {
        // The rectangle and circle intersect if they are either partially or fully intersecting
        return partIntersect(circle) || fullIntersect(circle);
    }

    // Check if this rectangle intersects with another rectangle
    @Override
    public boolean intersect(Rectangle rect) {

        //horozontal || vertical overlap somewhere
        boolean partiallyInside = (this.left < rect.getRight() && this.right > rect.getLeft() &&
                                   this.bottom < rect.getTop() && this.top > rect.getBottom());

        /*

         * - All bounds of this rect1 must lie within the other rect2 bounds. 
         * rectangles could also be equal
         */
        boolean fullyInside = (this.left >= rect.getLeft() && this.right <= rect.getRight() &&
                               this.top <= rect.getTop() && this.bottom >= rect.getBottom());

        return partiallyInside || fullyInside;
    }
    @Override
    public boolean intersect(CompElement compElement) {
    // give intersection task to compelem class
    return compElement.intersect(this);
}
}
