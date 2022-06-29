package com.violeth.blockjs.blockjs.jsinterface.runtime;

import com.violeth.blockjs.blockjs.jsinterface.JSInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JSInterfaceTest {

    private JSInterface runner = new JSInterface(new File("src/test/resources/jsTests/basicTest.js"));

    @Test
    @DisplayName("Runs JS without error")
    void runJS() {
        assertDoesNotThrow(() -> runner.runJS());
    }

    @Test
    @DisplayName("Can register callbacks")
    void registerCallbacks() {
        runner = new JSInterface(new File("src/test/resources/jsTests/callbackTest.js"));
        assertDoesNotThrow(() -> runner.registerCallbacks());
    }

    @Test
    @DisplayName("Can run JS with callbacks")
    void runJSWithCallbacks() {
        runner = new JSInterface(new File("src/test/resources/jsTests/callbackTest.js"));
        runner.registerCallbacks();
        assertDoesNotThrow(() -> runner.runJS());
    }
}