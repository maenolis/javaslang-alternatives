package gr.maenolis.alternative.switchcase;

import gr.maenolis.alternative.exception.AlternativeSwitchCaseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;

public class SwitchCaseTest {

    private static final Consumer<String> msg = str -> System.out.println(String.format("matched with [%s]!", str));
    private static final Function<String, String> passthrough = str -> str;


    @Test
    public void consumableSuccess() {
        Switch.caseOfErroneous("javaslang").test(
                Cases.ofConsumable(str -> str.endsWith("lang"), msg),
                Cases.ofConsumable(str -> str.endsWith("lang3"), msg),
                Cases.ofConsumable(str -> str.endsWith("lang4"), msg),
                Cases.ofConsumable(str -> str.endsWith("lang5"), msg),
                Cases.ofConsumable(str -> str.endsWith("lang6"), msg),
                Cases.ofConsumable(str -> str.endsWith("lang7"), msg)
        );
    }

    @Test(expected = AlternativeSwitchCaseException.class)
    public void consumableError() {
        Switch.caseOfErroneous("javaslang").test(
                Cases.ofConsumable(str -> str.endsWith("lang2"), msg),
                Cases.ofConsumable(str -> str.endsWith("lang3"), msg),
                Cases.ofConsumable(str -> str.endsWith("lang4"), msg),
                Cases.ofConsumable(str -> str.endsWith("lang5"), msg),
                Cases.ofConsumable(str -> str.endsWith("lang6"), msg),
                Cases.ofConsumable(str -> str.endsWith("lang7"), msg)
        );
    }

    @Test
    public void functionalSuccess() {
        final String result = Switch.caseOfErroneous("javaslang").test(
                Cases.ofFunctional(str -> str.endsWith("lang"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang2"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang3"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang4"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang5"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang6"), passthrough)
        );

        Assert.assertEquals(result, "javaslang");
    }

    @Test(expected = AlternativeSwitchCaseException.class)
    public void functionalError() {
        Switch.caseOfErroneous("javaslang").test(
                Cases.ofFunctional(str -> str.endsWith("lang1"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang2"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang3"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang4"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang5"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang6"), passthrough)
        );
    }

    @Test(expected = AssertionError.class)
    public void functionalMissMatchError() {
        final String result = Switch.caseOfErroneous("javaslang").test(
                Cases.ofFunctional(str -> str.endsWith("lang"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang2"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang3"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang4"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang5"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang6"), passthrough)
        );

        Assert.assertEquals(result, "javaslang4");
    }

    @Test(expected = AssertionError.class)
    public void functionalMissMatchSuccess() {
        final String result = Switch.caseOf("javaslang").test(
                Cases.ofFunctional(str -> str.endsWith("lang"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang2"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang3"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang4"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang5"), passthrough),
                Cases.ofFunctional(str -> str.endsWith("lang6"), passthrough)
        );

        Assert.assertNull(result);
    }
}
