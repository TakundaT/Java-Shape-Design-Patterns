public interface CollisionDetector {

     boolean intersect(Point p);
     boolean intersect(LineSeg line);
     boolean intersect(Rectangle rect);
     boolean intersect(Circle circle);
     boolean intersect(CompElement compElement);
} //check git push to see if assignment submitted