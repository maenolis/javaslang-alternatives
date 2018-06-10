package com.github.maenolis.switchcase.switchcase;

import java.util.function.Consumer;
import java.util.function.Function;

import com.github.maenolis.switchcase.Switch;
import com.github.maenolis.switchcase.exception.AlternativeSwitchCaseException;
import org.junit.Assert;
import org.junit.Test;

import static com.github.maenolis.switchcase.Cases.of;

public class SwitchCaseTest {

	private static final Consumer<String> msg = str -> System.out.println(String.format("matched with [%s]!", str));

	@Test
	public void consumableSuccess() {
		final Switch<String, Void> aSwitch = Switch
				.addCase(of(str -> str.endsWith("lang"), msg))
				.addCase(of(str -> str.endsWith("lang3"), msg))
				.addCase(of(str -> str.endsWith("lang4"), msg))
				.addCase(of(str -> str.endsWith("lang5"), msg))
				.addCase(of(str -> str.endsWith("lang6"), msg))
				.addCase(of(str -> str.endsWith("lang7"), msg))
				.erroneous()
				.build();

		aSwitch.apply("javaslang");
	}

	@Test(expected = AlternativeSwitchCaseException.class)
	public void consumableError() {
		final Switch<String, Void> aSwitch = Switch
				.addCase(of(str -> str.endsWith("lang2"), msg))
				.addCase(of(str -> str.endsWith("lang3"), msg))
				.addCase(of(str -> str.endsWith("lang4"), msg))
				.addCase(of(str -> str.endsWith("lang5"), msg))
				.addCase(of(str -> str.endsWith("lang6"), msg))
				.addCase(of(str -> str.endsWith("lang7"), msg))
				.erroneous()
				.build();

		aSwitch.apply("javaslang");
	}

	@Test
	public void functionalSuccess() {
		final Switch<String, String> aSwitch = Switch
				.addCase(of(str -> str.endsWith("lang"), Function.<String>identity()))
				.addCase(of(str -> str.endsWith("lang2"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang3"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang4"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang5"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang6"), Function.identity()))
				.build();

		Assert.assertEquals(aSwitch.apply("javaslang"), "javaslang");
	}

	@Test(expected = AlternativeSwitchCaseException.class)
	public void functionalError() {
		final Switch<String, String> aSwitch = Switch
				.addCase(of(str -> str.endsWith("lang1"), Function.<String>identity()))
				.addCase(of(str -> str.endsWith("lang2"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang3"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang4"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang5"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang6"), Function.identity()))
				.erroneous()
				.build();

		aSwitch.apply("javaslang");
	}

	@Test(expected = AssertionError.class)
	public void functionalMissMatchError() {
		final Switch<String, String> aSwitch = Switch
				.addCase(of(str -> str.endsWith("lang"), Function.<String>identity()))
				.addCase(of(str -> str.endsWith("lang2"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang3"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang4"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang5"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang6"), Function.identity()))
				.build();

		Assert.assertEquals(aSwitch.apply("javaslang"), "javaslang4");
	}

	@Test(expected = AssertionError.class)
	public void functionalMissMatchSuccess() {
		final Switch<String, String> aSwitch = Switch
				.addCase(of(str -> str.endsWith("lang"), Function.<String>identity()))
				.addCase(of(str -> str.endsWith("lang2"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang3"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang4"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang5"), Function.identity()))
				.addCase(of(str -> str.endsWith("lang6"), Function.identity()))
				.build();

		Assert.assertNull(aSwitch.apply("javaslang"));
	}
}
