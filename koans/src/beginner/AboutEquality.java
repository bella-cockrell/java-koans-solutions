package beginner;

import com.sandwich.koan.Koan;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutEquality {

    @Koan
    public void doubleEqualsTestsIfTwoObjectsAreTheSame() {
        Object object = new Object();
        Object sameObject = object;
        assertEquals(object == sameObject, true); // same instantiation, and same value?
        assertEquals(object == new Object(), false); // double equals checks instantiation, assertEquals is value?
    }

    @Koan
    public void equalsMethodByDefaultTestsIfTwoObjectsAreTheSame() {
        Object object = new Object();
        assertEquals(object.equals(object), true);
        assertEquals(object.equals(new Object()), false);
    }

    @Koan
    public void equalsMethodCanBeChangedBySubclassesToTestsIfTwoObjectsAreEqual() {
        Object object = new Integer(1);
        assertEquals(object.equals(object), true);
        assertEquals(object.equals(new Integer(1)), true); // integer is an object
        // Note: This means that for the class 'Object' there is no difference between
        // 'equal' and 'same'
        // but for the class 'Integer' there is difference - see below
    }

    @Koan
    public void equalsMethodCanBeChangedBySubclassesToTestsIfTwoObjectsAreEqualExample() {
        Integer value1 = new Integer(4);
        Integer value2 = new Integer(2 + 2);
        assertEquals(value1.equals(value2), true); // same class and same value
        assertEquals(value1, value2);
    }

    @Koan
    public void objectsNeverEqualNull() {
        assertEquals(new Object().equals(null), false);
    }

    @Koan
    public void objectsEqualThemselves() {
        Object obj = new Object();
        assertEquals(obj.equals(obj), true);
    }
}
