package io.github.blockjs.plugin.jsinterface

import io.github.blockjs.plugin.js.runtime.JSMinecraftRuntimeManager
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class JSInterfaceTest {
    @Test
    @DisplayName("Runs JS without error")
    fun runJS() {
        val node = JSMinecraftRuntimeManager.createRuntime()
        Assertions.assertDoesNotThrow { node.runtime.getExecutor("console.log('Hello World!')").executeVoid() }
    } //TODO: Fix other tests
    //    @Test
    //    @DisplayName("Can register interface")
    //    void registerCallbacks() {
    //        var node = JSRuntimeManager.createNodeJS();
    //
    //        node.exec(new File("src/test/resources/jsTests/callbackTest.js"));
    //
    //        assertDoesNotThrow(() -> runner.registerCallbacks());
    //    }
    //    @Test
    //    @DisplayName("Can run JS with interface methods")
    //    void runJSWithInterfaceMethods() {
    //        var node = JSRuntimeManager.createNodeJS();
    //
    //        assertDoesNotThrow(() -> node.exec(new File("src/test/resources/jsTests/interfaceTest.js")));
    //    }
}