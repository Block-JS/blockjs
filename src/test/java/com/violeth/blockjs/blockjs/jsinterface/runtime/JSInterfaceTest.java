package com.violeth.blockjs.blockjs.jsinterface.runtime;

import com.violeth.blockjs.blockjs.jsinterface.JSInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JSInterfaceTest {

    private JSInterface runner = new JSInterface(new File("src/test/resources/test.js"));

    @BeforeEach
    void setUp() {
        runner = new JSInterface(new File("src/test/resources/test.js"));
    }

    @Test
    @DisplayName("Runs JS without error")
    void runJS() {
        assertDoesNotThrow(() -> runner.runJS());
    }
}