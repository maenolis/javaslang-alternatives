package com.github.maenolis.switchcase.util;

import java.util.function.Consumer;

public class SwitchTestUtils {

    public static final Consumer<String> msg =
            str -> System.out.println(String.format("matched with [%s]!", str));

}
