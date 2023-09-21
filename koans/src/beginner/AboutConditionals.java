package beginner;

import com.sandwich.koan.Koan;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutConditionals {

    @Koan
    public void basicIf() {
        int x = 1;
        if (true) {
            x++;
        }
        assertEquals(x, 2);
    }

    @Koan
    public void basicIfElse() {
        int x = 1;
        boolean secretBoolean = false;
        if (secretBoolean) {
            x++;
        } else {
            x--;
        }
        assertEquals(x, 0);
    }

    @Koan
    public void basicIfElseIfElse() {
        int x = 1;
        boolean secretBoolean = false;
        boolean otherBooleanCondition = true;
        if (secretBoolean) {
            x++;
        } else if (otherBooleanCondition) {
            x = 10;
        } else {
            x--;
        }
        assertEquals(x, 10);
    }

    @Koan
    public void nestedIfsWithoutCurlysAreReallyMisleading() {
        int x = 1;
        boolean secretBoolean = false;
        boolean otherBooleanCondition = true;
        // Curly braces after an "if" or "else" are not required...
        if (secretBoolean)
            x++;
        if (otherBooleanCondition)
            x = 10;
        else
            x--;
        // ...but they are recommended.
        // oh thats terribly unclear without braces
        assertEquals(x, 10);
    }

    @Koan
    public void ifAsIntended() {
        int x = 1;
        boolean secretBoolean = false;
        boolean otherBooleanCondition = true;
        // Adding curly braces avoids the "dangling else" problem seen
        // above.
        if (secretBoolean) {
            x++;
            if (otherBooleanCondition) {
                x = 10;
            }
        } else {
            x--;
        }
        assertEquals(x, 0);
    }

    @Koan
    public void basicSwitchStatement() {
        int i = 1;
        String result = "Basic ";
        switch (i) {
            case 1:
                result += "One";
                break;
            case 2:
                result += "Two";
                break;
            default:
                result += "Nothing";
        }
        assertEquals(result, "Basic One");
    }

    @Koan
    public void switchStatementFallThrough() {
        int i = 1;
        String result = "Basic ";
        switch (i) {
            case 1:
                result += "One";
            case 2:
                result += "Two";
            default:
                result += "Nothing";
        }
        assertEquals(result, "Basic OneTwoNothing"); // there is nothing to break
    }

    @Koan
    public void switchStatementCrazyFallThrough() {
        int i = 5;
        String result = "Basic ";
        switch (i) {
            case 1:
                result += "One";
            default:
                result += "Nothing";
            case 2:
                result += "Two";
        }
        assertEquals(result, "Basic NothingTwo"); // doesn't hit case one -- order is important if you want fallthrough
    }

    @Koan
    public void switchStatementConstants() {
        int i = 5;
        // What happens if you remove the 'final' modifier?
        // What does this mean for case values?
        final int caseOne = 1; // final makes constants (vars), prevents overriding (methods) or inheritance
                               // (classes)
        String result = "Basic ";
        switch (i) {
            case caseOne: // expression required if no final keyword?
                // caseOne is 1, so i doesn't hit it
                result += "One";
                break;
            default:
                result += "Nothing";
        }
        assertEquals(result, "Basic Nothing");
    }

    @Koan
    public void switchStatementSwitchValues() {
        // Try different (primitive) types for 'c'
        // Which types do compile?
        // Does boxing work?
        char c = 'a'; // primitive type = char, object type is new Character() -- boxing and unboxing
                        // is going from primitive to user-defined object
        String result = "Basic ";
        switch (c) {
            case 'a':
                result += "One";
                break;
            default:
                result += "Nothing";
        }
        assertEquals(result, "Basic One");
    }

    @Koan
    public void shortCircuit() {
        Counter trueCount = new Counter(true);
        Counter falseCount = new Counter(false);
        String x = "Hai";
        if (trueCount.count() || falseCount.count()) { //OR statements find the first truthy, so falseCount would not be run
            x = "kthxbai";
        }
        assertEquals(x, "kthxbai");
        assertEquals(trueCount.count, 1);
        assertEquals(falseCount.count, 0);
    }

    @Koan
    public void bitwise() {
        Counter trueCount = new Counter(true);
        Counter falseCount = new Counter(false);
        String x = "Hai";
        if (trueCount.count() | falseCount.count()) { //interesting! bitwise OR runs both, I think because it needs to evaluate the bits to compare
            x = "kthxbai";
        }
        assertEquals(x, "kthxbai");
        assertEquals(trueCount.count, 1);
        assertEquals(falseCount.count, 1);
    }

    class Counter {
        boolean returnValue;
        int count = 0;

        Counter(boolean returnValue) {
            this.returnValue = returnValue;
        }

        boolean count() {
            count++;
            return returnValue;
        }
    }
}
