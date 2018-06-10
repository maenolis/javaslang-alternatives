package com.github.maenolis.switchcase;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

import static com.github.maenolis.switchcase.Cases.of;

public class SwitchWithDefaultTest {

    @Test
    public void defaultableMatch() {
        final Switch<String, String> aSwitch = Switch
                .addCase(of(str -> str.endsWith("lang"), Function.<String>identity()))
                .addCase(of(str -> str.endsWith("lang3"), Function.identity()))
                .addCase(of(str -> str.endsWith("lang4"), Function.identity()))
                .addCase(of(str -> str.endsWith("lang5"), Function.identity()))
                .addCase(of(str -> str.endsWith("lang6"), Function.identity()))
                .addCase(of(str -> str.endsWith("lang7"), Function.identity()))
                .withDefault(String::toUpperCase)
                .build();

        Assert.assertEquals(aSwitch.apply("javaslang"), "javaslang");
    }

    @Test
    public void defaultableNoMatch() {
        final Switch<String, String> aSwitch = Switch
                .addCase(of(str -> str.endsWith("lang2"), Function.<String>identity()))
                .addCase(of(str -> str.endsWith("lang3"), Function.identity()))
                .addCase(of(str -> str.endsWith("lang4"), Function.identity()))
                .addCase(of(str -> str.endsWith("lang5"), Function.identity()))
                .addCase(of(str -> str.endsWith("lang6"), Function.identity()))
                .addCase(of(str -> str.endsWith("lang7"), Function.identity()))
                .withDefault(String::toUpperCase)
                .build();

        Assert.assertEquals(aSwitch.apply("javaslang"), "JAVASLANG");
    }

}
