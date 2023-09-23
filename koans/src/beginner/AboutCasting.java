package beginner;

import com.sandwich.koan.Koan;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.fail;

@SuppressWarnings("unused")
public class AboutCasting {

    @Koan
    public void longPlusInt() {
        int a = 6;
        long b = 10;
        Object c = a + b; // c has reference to an object (the ancestor of long or int)
        assertEquals(c, 16L);
        assertEquals(c instanceof Integer, false);
        assertEquals(c instanceof Long, true); // but is a long? Not int because I imagine would like to be long to keep
                                               // more memory
    }

    @Koan
    public void forceIntTypecast() {
        long a = 2147483648L;
        // What happens if we force a long value into an int?
        int b = (int) a;
        assertEquals(b, -2147483648); // it becomes negative? because of overflow -- think Gandhi in Civ
    }

    @Koan
    public void implicitTypecast() {
        int a = 1;
        int b = Integer.MAX_VALUE;
        long c = a + b; // still overflows int... which is the Integer.MIN_VALUE, the operation occurs
                        // prior to assignment to long
        assertEquals(c, -2147483648L); // gets assigned to long before overflow wow

    }

    interface Sleepable {
        String sleep();
    }

    class Grandparent implements Sleepable { // this is an interface, not inheritance
        public String sleep() {
            return "zzzz";// this is gparent's own implementation of sleep()
        }
    }

    class Parent extends Grandparent {
        public String complain() {
            return "TPS reports don't even have a cover letter!"; // parent is child of gparent
            // gparent does not have complain(), parent does. Does parent have sleep()?
        }
    }

    class Child extends Parent {
        public String complain() {
            return "Are we there yet!!"; // child is a child of parent
        }
    }

    @Koan
    public void upcastWithInheritance() {
        Child child = new Child();
        Parent parentReference = child; // Why isn't there an explicit cast? -- perhaps because we declare Parent first?
        // or perhaps because there is an implicit cast because Child is related to
        // parent
        // upcasting -- implementing the superclass
        // Parent parent2 = (Parent) child;
        // assertEquals(parent2.getClass(), Parent.class); //expected:<class
        // beginner.AboutCasting$Child> but was:<class beginner.AboutCasting$Parent>

        assertEquals(child instanceof Child, true);
        assertEquals(parentReference instanceof Child, true);
        assertEquals(parentReference instanceof Parent, true); // see below -- but this says parentRef is an instance!
        assertEquals(parentReference instanceof Grandparent, true);
    }

    @Koan
    public void upcastAndPolymorphism() {
        Child child = new Child();
        Parent parentReference = child;
        // If the result is unexpected, consider the difference between an instance and
        // its reference
        assertEquals(parentReference.complain(), "Are we there yet!!"); // so the parentRef is an instance of Parent but
                                                                        // does not implement Parent methods?
    }

    @Koan
    public void downcastWithInheritance() {
        Grandparent child = new Child(); // child has only a ref to gparent, so complains like child, but maybe sleeps
                                         // like gparent?
        // System.out.println(child.sleep()); //yes this prints zzzz
        // System.out.println(child.getClass()); //Child
        // child is an instance of Child, but contains ref to gparent (possibly making
        // the downcast work?)
        Parent parentReference = (Parent) child; // Why do we need an explicit cast here?
        // System.out.println(parentReference.getClass()); // Child again
        // System.out.println(parentReference.complain()); // this complains like child
        Child childReference = (Child) parentReference; // Or here? -- automatic conversion cannot be done?
        // System.out.println(childReference.complain()); //complains like child
        assertEquals(childReference instanceof Child, true);
        assertEquals(childReference instanceof Parent, true);
        assertEquals(childReference instanceof Grandparent, true);
    }

    @Koan
    public void downcastAndPolymorphism() {
        Grandparent child = new Child();
        Parent parent = (Child) child;
        // Think about the result. Did you expect that? Why?
        // How is that different from above?
        assertEquals(parent.complain(), "Are we there yet!!");
    }

    @Koan
    public void classCasting() {
        try {
            Object o = new Object();
            ((Grandparent) o).sleep(); // would this even compile without the cast? No, because Object doesn;t have
                                       // .sleep()
        } catch (ClassCastException x) {
            fail("Object does not implement Sleepable, maybe one of the people classes do?");
        }
    }

    @Koan
    public void complicatedCast() {
        Grandparent parent = new Parent();
        // How can we access the parent's ability to "complain" - if the reference is
        // held as a superclass?
        Parent castParent = (Parent) parent;

        assertEquals("TPS reports don't even have a cover letter!", castParent.complain());
    }

}
