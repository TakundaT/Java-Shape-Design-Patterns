public class ClientExample {
    public static void main(String[] args) {
        //composition
        LineSeg l = new LineSeg(new Point(2,2), new Point(1,1));
        Circle c = new Circle(new Point(7.1f, 0.4f), 3.3f);
        Rectangle r = new Rectangle(1,3,3,2);
        CompElement e = new CompElement();
        e.addElement(l);
        e.addElement(c);
        e.addElement(r);
        CompElement e2 = new CompElement();
        e.removeElement(r);
        e2.addElement(r);
        e2.addElement(e);
        
        //intersect
        e2.intersect(l);
        e2.intersect(c);
        e2.intersect(r);
        e2.intersect(e);
        
        //access components
        CompElementIterator it = e2.getIterator();
        it.first();
        
        while(it.isDone()){
        AbstractElement element = it.getCurrentElement();
        it.next();
        }
        
        //singleton Drawing
        Drawing dwg = Drawing.getInstance();
        dwg.setElement(e2);
        AbstractElement de = dwg.getElement();
    }
}
