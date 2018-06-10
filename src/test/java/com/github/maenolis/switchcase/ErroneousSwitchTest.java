package com.github.maenolis.switchcase;

import com.github.maenolis.switchcase.exception.AlternativeSwitchCaseException;
import org.junit.Test;

import static com.github.maenolis.switchcase.util.SwitchTestUtils.msg;
import java.util.function.Function;

import static com.github.maenolis.switchcase.Cases.of;

public class ErroneousSwitchTest {

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
}
