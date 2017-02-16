package gr.maenolis.alternative.switchcase;

import gr.maenolis.alternative.exception.AlternativeSwitchCaseException;
import org.junit.Assert;
import org.junit.Test;

public class SwitchCaseTest {

    @Test
    public void consumableSuccess() {
        final Switch<String> myCase = Switch.caseOfErroneous("javaslang");
        myCase.test(
                Case.ofConsumable(str -> str.endsWith("lang"), str -> System.out.println(String.format("matched with [%s]!", str))),
                Case.ofConsumable(str -> str.endsWith("lang3"), str -> System.out.println(String.format("matched with [%s]!", str))),
                Case.ofConsumable(str -> str.endsWith("lang4"), str -> System.out.println(String.format("matched with [%s]!", str))),
                Case.ofConsumable(str -> str.endsWith("lang5"), str -> System.out.println(String.format("matched with [%s]!", str))),
                Case.ofConsumable(str -> str.endsWith("lang6"), str -> System.out.println(String.format("matched with [%s]!", str))),
                Case.ofConsumable(str -> str.endsWith("lang7"), str -> System.out.println(String.format("matched with [%s]!", str)))
        );
    }

    @Test(expected = AlternativeSwitchCaseException.class)
    public void consumableError() {
        final Switch<String> myCase = Switch.caseOfErroneous("javaslang");
        myCase.test(
                Case.ofConsumable(str -> str.endsWith("lang2"), str -> System.out.println(String.format("matched with [%s]!", str))),
                Case.ofConsumable(str -> str.endsWith("lang3"), str -> System.out.println(String.format("matched with [%s]!", str))),
                Case.ofConsumable(str -> str.endsWith("lang4"), str -> System.out.println(String.format("matched with [%s]!", str))),
                Case.ofConsumable(str -> str.endsWith("lang5"), str -> System.out.println(String.format("matched with [%s]!", str))),
                Case.ofConsumable(str -> str.endsWith("lang6"), str -> System.out.println(String.format("matched with [%s]!", str))),
                Case.ofConsumable(str -> str.endsWith("lang7"), str -> System.out.println(String.format("matched with [%s]!", str)))
        );
    }

    @Test
    public void funcationalSuccess() {
        final Switch<String> myCase = Switch.caseOfErroneous("javaslang");
        final String result = myCase.get(
                Case.ofFunctional(str -> str.endsWith("lang"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang2"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang3"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang4"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang5"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang6"), str -> {return str;})
        );

        Assert.assertEquals(result, "javaslang");
    }

    @Test(expected = AlternativeSwitchCaseException.class)
    public void funcationalError() {
        final Switch<String> myCase = Switch.caseOfErroneous("javaslang");
        final String result = myCase.get(
                Case.ofFunctional(str -> str.endsWith("lang1"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang2"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang3"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang4"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang5"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang6"), str -> {return str;})
        );
    }

    @Test(expected = AssertionError.class)
    public void funcationalMissMatchError() {
        final Switch<String> myCase = Switch.caseOfErroneous("javaslang");
        final String result = myCase.get(
                Case.ofFunctional(str -> str.endsWith("lang"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang2"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang3"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang4"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang5"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang6"), str -> {return str;})
        );

        Assert.assertEquals(result, "javaslang4");
    }

    @Test(expected = AssertionError.class)
    public void funcationalMissMatchSuccess() {
        final Switch<String> myCase = Switch.caseOf("javaslang");
        final String result = myCase.get(
                Case.ofFunctional(str -> str.endsWith("lang"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang2"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang3"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang4"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang5"), str -> {return str;}),
                Case.ofFunctional(str -> str.endsWith("lang6"), str -> {return str;})
        );

        Assert.assertNull(result);
    }
}
