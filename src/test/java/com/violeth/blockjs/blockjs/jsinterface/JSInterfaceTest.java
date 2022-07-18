package com.violeth.blockjs.blockjs.jsinterface;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JSInterfaceTest {

    private JSExecutionInterface runner = new JSExecutionInterface(new File("src/test/resources/jsTests/basicTest.js"));

    @Test
    @DisplayName("Runs JS without error")
    void runJS() {
        assertDoesNotThrow(() -> runner.runJS());
    }

    @Test
    @DisplayName("Can register callbacks")
    void registerCallbacks() {
        runner = new JSExecutionInterface(new File("src/test/resources/jsTests/callbackTest.js"));
        assertDoesNotThrow(() -> runner.registerCallbacks());
    }

    @Test
    @DisplayName("Can run JS with callbacks")
    void runJSWithCallbacks() {
        runner = new JSExecutionInterface(new File("src/test/resources/jsTests/callbackTest.js"));
        runner.registerCallbacks();
        assertDoesNotThrow(() -> runner.runJS());
    }
}