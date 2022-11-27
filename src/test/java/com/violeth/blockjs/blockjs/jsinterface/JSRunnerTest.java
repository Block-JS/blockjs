package com.violeth.blockjs.blockjs.jsinterface;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class JSRunnerTest {
    private JSRunner runner = new JSRunner(new File("src/test/resources/jsTests"));

    @Test
    @DisplayName("Runs JS without error")
    void runJS() {
        runner = new JSRunner(new File("src/test/resources/jsTests"));
        assertDoesNotThrow(() -> runner.getAndRunJS());
    }
}