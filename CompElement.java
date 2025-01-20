import java.util.ArrayList;

/**
 * CompElement class - composite shape
 * Contains a set of shapes and uses composite pattern
 */
public class CompElement extends AbstractElement implements CollisionDetector {
    // Private ArrayList to hold child shapes
    private ArrayList<AbstractElement> elements;

    /**
     * Initializes empty CompElement.
     */
    public CompElement() {
        super();
        this.elements = new ArrayList<>();
    }

    /**
     Adds shape CompElement
     */
    public void addElement(AbstractElement element) {
        this.elements.add(element);
    }

    /**
     * Removes a shape from the CompElement.
     */
    public void removeElement(AbstractElement element) {
        this.elements.remove(element);
    }

    /*
     need to get list of shapes
     */
    public ArrayList<AbstractElement> getElements() {
        return elements;
    }

    @Override
    public boolean intersect(Point p) {
        for (AbstractElement element : elements) {
            if (element instanceof CollisionDetector && ((CollisionDetector) element).intersect(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * compelem intersect lineseg
     * true if shape intersct lineseg else false
     */
    @Override
    public boolean intersect(LineSeg lineSeg) {
        for (AbstractElement element : elements) {
            if (element instanceof CollisionDetector && ((CollisionDetector) element).intersect(lineSeg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * compelement intersect circle?
 true if shape has intersection with circl else false
     */
    @Override
    public boolean intersect(Circle circle) {
        for (AbstractElement element : elements) {
            if (element instanceof CollisionDetector && ((CollisionDetector) element).intersect(circle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * compelement intersect rect?
  true if shape intersects rect else false
     */
    @Override
    public boolean intersect(Rectangle rect) {
        for (AbstractElement element : elements) {
            if (element instanceof CollisionDetector && ((CollisionDetector) element).intersect(rect)) {
                return true;
            }
        }
        return false;
    }

    /**
     * comp elem intersect compelem?
 true if any shape in compelem1 intersect any shape in compelem2 else false
     *         
     */
    @Override
    public boolean intersect(CompElement compElement) {
        for (AbstractElement element1 : elements) {
            if (element1 instanceof CollisionDetector) {
                for (AbstractElement element2 : compElement.getElements()) {
                    if (element2 instanceof CollisionDetector) {
                        // Determine type of element2 and cast
                        if (element2 instanceof Point && ((CollisionDetector) element1).intersect((Point) element2)) {
                            return true;
                        } else if (element2 instanceof LineSeg && ((CollisionDetector) element1).intersect((LineSeg) element2)) {
                            return true;
                        } else if (element2 instanceof Circle && ((CollisionDetector) element1).intersect((Circle) element2)) {
                            return true;
                        } else if (element2 instanceof Rectangle && ((CollisionDetector) element1).intersect((Rectangle) element2)) {
                            return true;
                        } else if (element2 instanceof CompElement && ((CollisionDetector) element1).intersect((CompElement) element2)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    /**
 * Returns an iterator for this CompElement.
 */
public CompElementIterator getIterator() {
    return new CompElementIterator(this);
}

}
