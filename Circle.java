public class Circle extends AbstractElement implements CollisionDetector {
    private static int numberOfInstances = 0;  // maintain the no.of circle instances
    private Point center;  // center of circle
    private float radius;  // radius of circle

    // constructor uses initializition of point(0,0) and initializes radius to 0
    public Circle() {
        this(new Point(), 0);
    }

    //constructor
    public Circle(Point center, float radius) {
        super();
        this.center = center;
        this.radius = radius;
        numberOfInstances++;
    }

    // center getter
    public Point getCenter() {
        return center;
    }

    //radius getter
    public float getRadius() {
        return radius;
    }

    // total number of circle instances
    public static int getNumOfInstances() {
        return numberOfInstances;
    }

    // Check if this circle intersects with a point
    @Override
    public boolean intersect(Point p) {
        return p.intersect(this); // Defaults to implementation in point class
    }

    // Check if this circle intersects with a line segment
    @Override
    public boolean intersect(LineSeg segline) {
        return segline.intersect(this); } // Delfaults to implemntation in lineseg class

    // Check if this circle intersects with a rectangle
    @Override
    public boolean intersect(Rectangle rect) {
        // The circle intersects the rectangle if it is either partially inside
        // or iside the rectangle, we don't need to count edge cases
        return partIntersect(rect) || fullIntersect(rect);
    }

    // Helper method to check if the rectangle is partially intersecting with the circle
    private boolean partIntersect(Rectangle rect) {
        // This method defaults method to the Rectangle class implementation
        return rect.intersect(this);
    }

  //check for full intersect,
    private boolean fullIntersect(Rectangle rect) {
        /*
           checks if the entire circle fits inside the rectangle.
          
         Conditions:
         - top of rect must be above top bound of the circle.
         - bottom of the rect must be below the bottom bound of the circle.
         - similarly with left and right

         */
        return this.center.getY() + this.radius < rect.getTop() &&
               this.center.getY() - this.radius > rect.getBottom() &&
               this.center.getX() - this.radius > rect.getLeft() &&
               this.center.getX() + this.radius < rect.getRight();
    }

    // Check circle-circle ntersection
    @Override
    public boolean intersect(Circle circle) {
        /*
 This method checks if two circles intersect. 
we need to calculate the distance between the centers of the two circles so we know how far apart the two circles are.
         */
        float dx = this.center.getX() - circle.getCenter().getX();
        float dy = this.center.getY() - circle.getCenter().getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy); // Distance formula.

        /*
we need to check if circle1 is fully inside circle2
 only when: the distance between the centers plus the smaller radius  is less than or equal to the larger radius.
         */
        boolean fullyInside = (distance + Math.min(this.radius, circle.getRadius()) <= 
                               Math.max(this.radius, circle.getRadius()));

        /* 
we need to check two circles: partially intersecting:
distance between centers < sum of radii
         */
        boolean partiallyIntersecting = (distance < this.radius + circle.getRadius());
        return fullyInside || partiallyIntersecting;
    }
    @Override
    public boolean intersect(CompElement compElement) {
    // Delegate intersection task to compelem class
    return compElement.intersect(this);
}

}
