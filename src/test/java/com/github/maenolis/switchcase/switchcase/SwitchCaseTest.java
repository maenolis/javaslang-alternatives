package com.github.maenolis.switchcase.switchcase;

import java.util.function.Consumer;
import java.util.function.Function;

import com.github.maenolis.switchcase.Cases;
import com.github.maenolis.switchcase.Switch;
import com.github.maenolis.switchcase.exception.AlternativeSwitchCaseException;
import org.junit.Assert;
import org.junit.Test;

public class SwitchCaseTest {

	private static final Consumer<String> msg = str -> System.out.println(String.format("matched with [%s]!", str));

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
				Cases.ofFunctional(str -> str.endsWith("lang"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang2"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang3"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang4"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang5"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang6"), Function.identity())
		);

		Assert.assertEquals(result, "javaslang");
	}

	@Test(expected = AlternativeSwitchCaseException.class)
	public void functionalError() {
		Switch.caseOfErroneous("javaslang").test(
				Cases.ofFunctional(str -> str.endsWith("lang1"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang2"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang3"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang4"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang5"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang6"), Function.identity())
		);
	}

	@Test(expected = AssertionError.class)
	public void functionalMissMatchError() {
		final String result = Switch.caseOfErroneous("javaslang").test(
				Cases.ofFunctional(str -> str.endsWith("lang"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang2"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang3"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang4"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang5"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang6"), Function.identity())
		);

		Assert.assertEquals(result, "javaslang4");
	}

	@Test(expected = AssertionError.class)
	public void functionalMissMatchSuccess() {
		final String result = Switch.caseOf("javaslang").test(
				Cases.ofFunctional(str -> str.endsWith("lang"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang2"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang3"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang4"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang5"), Function.identity()),
				Cases.ofFunctional(str -> str.endsWith("lang6"), Function.identity())
		);

		Assert.assertNull(result);
	}
}
