package com.violeth.blockjs.blockjs.jsinterface;

import be.seeseemelk.mockbukkit.MockBukkit;
import com.violeth.blockjs.blockjs.BlockJS;
import org.bukkit.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JSExecutionInterfaceTest {
    private JSExecutionInterface runner;

    private static Server server;
    private static BlockJS blockJS;

    @BeforeAll
    static void setup() {
        server = MockBukkit.mock();
        blockJS = MockBukkit.load(BlockJS.class);
    }

    @AfterAll
    static void teardown() {
        MockBukkit.unmock();
    }

    File getFile(String filePath) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
        return new File(Objects.requireNonNull(url).getFile());
    }

    @Test
    @DisplayName("Can run JS")
    void runJS() throws URISyntaxException {
        runner = new JSExecutionInterface(getFile("jsTests/basicTest.js"));
        assertDoesNotThrow(() -> runner.registerAndRun());
    }

    @Test
    @DisplayName("Can run JS with callbacks")
    void runJSWithCallbacks() {
        runner = new JSExecutionInterface(getFile("jsTests/callbackTest.js"));
        assertDoesNotThrow(() -> runner.registerAndRun());
    }

    @Test
    @DisplayName("Can run JS with import statements")
    void runJSWithImports() {
        runner = new JSExecutionInterface(getFile("jsTests/importTest.js"));
        assertDoesNotThrow(() -> runner.registerAndRun());
    }
}