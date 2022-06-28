package com.violeth.blockjs.blockjs.jsinterface.runtime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JSRunnerTest {

    private JSRunner runner = new JSRunner(new File("src/test/resources/test.js"));

    @BeforeEach
    void setUp() {
        runner = new JSRunner(new File("src/test/resources/test.js"));
    }

    @Test
    @DisplayName("Runs JS without error")
    void runJS() {
        assertDoesNotThrow(() -> runner.runJS());
    }
}