package com.github.maenolis.switchcase;

import java.util.function.Function;

import static com.github.maenolis.switchcase.util.SwitchTestUtils.msg;
import org.junit.Assert;
import org.junit.Test;

import static com.github.maenolis.switchcase.Cases.of;

public class SwitchTest {

	@Test
	public void consumableSuccess() {
		final Switch<String, Void> aSwitch = Switch
				.addCase(of(str -> str.endsWith("lang"), msg))
				.addCase(of(str -> str.endsWith("lang3"), msg))
				.addCase(of(str -> str.endsWith("lang4"), msg))
				.addCase(of(str -> str.endsWith("lang5"), msg))
				.addCase(of(str -> str.endsWith("lang6"), msg))
				.addCase(of(str -> str.endsWith("lang7"), msg))
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
