package beginner;

import com.sandwich.koan.Koan;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutArithmeticOperators {

    @Koan
    public void simpleOperations() {
        assertEquals(1, 1);
        assertEquals(1 + 1, 2);
        assertEquals(2 + 3 * 4, 14);
        assertEquals((2 + 3) * 4, 20);
        assertEquals(2 * 3 + 4, 10);
        assertEquals(2 - 3 + 4, 3);
        assertEquals(2 + 4 / 2, 4);
        assertEquals((2 + 4) / 2, 3);
    }

    @Koan
    public void notSoSimpleOperations() {
        assertEquals(1 / 2, 0); //int not float
        assertEquals(3 / 2, 1); //'how many times does 2 go into 1'
        assertEquals(1 % 2, 1);
        assertEquals(3 % 2, 1); //the leftover
    }

    @Koan
    public void minusMinusVariableMinusMinus() {
        int i = 1;
        assertEquals(--i, 0); //mutates
        assertEquals(i, 0); 
        assertEquals(i--, 0); //updates when it is called again
        assertEquals(i, -1);
    }

    @Koan
    public void plusPlusVariablePlusPlus() {
        int i = 1;
        assertEquals(++i, 2); //adds and reassigns
        assertEquals(i, 2);
        assertEquals(i++, 2); //updates when it is called again
        assertEquals(i, 3);
    }

    @Koan
    public void timesAndDivInPlace() {
        int i = 1;
        i *= 2;
        assertEquals(i, 2);
        i /= 2;
        assertEquals(i, 1);
    }

}
