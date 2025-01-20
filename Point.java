public class Point extends AbstractElement implements CollisionDetector {
    private float x;
    private float y;

    protected static int numberOfInstances = 0;

    public Point() {
        this(0,0);
    } //inittialize pt to 0
    
    public Point (float x, float y){
        super();
        this.x = x;
        this.y = y;
        numberOfInstances++;
    }
    // no.of.instances
    public static int getNumOfInstances() {
        return numberOfInstances;
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    @Override
    public boolean intersect(Point p){
        return this.x == p.getX() && this.y ==p.getY();
    }
    @Override public boolean intersect(Rectangle rect){
        //in order for a point to intersect a rectangle, the point needs to
        //be within the vertical and horizontal bounds of the rectangle object

        return x >  rect.getLeft() && x < rect.getRight() 
            && y > rect.getBottom() && y < rect.getTop();
    }
    @Override 
    public boolean intersect(Circle circle){
//to check if a point lies within a circle, we use the euclidian distance that states if 
//the eucledian distance is less than the radius, the point is wihthin the circle
//we don't count edge cases so we use stricly </
//equation of circle is (x-a)^2  + (y - b)^2 = r^2
       float dx = x - circle.getCenter().getX(); //change in x from ceter
       float dy = y - circle.getCenter().getY(); //change in y from center
       return (dx * dx + dy * dy) < (circle.getRadius() * circle.getRadius());
       
    }
    @Override 
    public boolean intersect(LineSeg segline){
        //to check if a point is on a line, the cross product must be zero
        float x1 = segline.getBegin().getX();
        float y1 = segline.getBegin().getY();
        float x2 = segline.getEnd().getX();
        float y2 = segline.getEnd().getY();

        double cProd = (x-x1)*(y2-y1)-(y-y1)*(x2-x1);
        if (Math.abs(cProd) != 0){ //floating point may cause percision errors, conside "> small epsilon error bound"
            return false;
        }
        boolean horizontalBound = (Math.min(x1,x2) <= x && x <= Math.max(x1,x2)); //a line stretches infinitely so the cross product isnt enough
        boolean verticalBound = (Math.min(y1,y2) <= y && y <= Math.max(y1,y2)); //we need to make ure the point intersecrs
        //the line within the bounds of begin & end.

        return horizontalBound && verticalBound;     
    }
    @Override
    public boolean intersect(CompElement compElement) {
    // give intersection task to compelem class
    return compElement.intersect(this);
}
}

