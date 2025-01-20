/**
CompElementIterator gives iterator to iterate through
 components of CompElement in order of inserted
 */
public class CompElementIterator {
    //compelem to address
    private CompElement compElement;
    //current index in traversal
    private int loop;
    /**
     * initialize iterator with compelement 
     */
    public CompElementIterator(CompElement compElement) {
        this.compElement = compElement;
        this.loop = 0;
    }

    /**
     reset iterator to first elem
     */
    public void first() {
        loop = 0;
    }

    /**
     //go to next elem
     */
    public void next() {
        loop++;
    }

    /**
    have all elems been seen?
 //True if all elements traversed, otherwise false.
     */
    public boolean isDone() {
        // Access the elems list using the getter method from CompElement
        return loop >= compElement.getElements().size();
    }

    /**
     * Return current elem in the iteration.
     */
    public AbstractElement getCurrentElement() {
        if (isDone()) {
            return null;
        }
        return compElement.getElements().get(loop);
    }
}
