/**
   Drawing class- singleton class- one  drawing board
  only one instance. references abstractelem
 */
public class Drawing {
    private static Drawing art; // one instance
    private AbstractElement element; // element in drawing

    // constructor has to be private (no new instances from outside class)
    private Drawing() {}

    /**
     *returns only one instance of drawong class
     */
    public static Drawing getInstance() {
        if (art == null) {
            art = new Drawing();
        }
        return art;
    }

    /**
     * Gets the elem currently in the drawing.
     returbn The current AbstractElem
     */
    public AbstractElement getElement() {
        return element;
    }

    /**
     * we want to set an element for drawing
   
     */
    public void setElement(AbstractElement element) {
        this.element = element;
    }
}
