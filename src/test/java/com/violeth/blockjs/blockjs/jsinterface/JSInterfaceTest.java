package com.violeth.blockjs.blockjs.jsinterface;

import com.violeth.blockjs.blockjs.JSRuntimeManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

//FIXME: Change test to use Javet methods
class JSInterfaceTest {
    @Test
    @DisplayName("Runs JS without error")
    void runJS() {
        var node = JSRuntimeManager.createNodeJS();

        assertDoesNotThrow(() -> node.exec(new File("src/test/resources/jsTests/basicTest.js")));
    }

//    @Test
//    @DisplayName("Can register interface")
//    void registerCallbacks() {
//        var node = JSRuntimeManager.createNodeJS();
//
//        node.exec(new File("src/test/resources/jsTests/callbackTest.js"));
//
//        assertDoesNotThrow(() -> runner.registerCallbacks());
//    }

    @Test
    @DisplayName("Can run JS with interface methods")
    void runJSWithInterfaceMethods() {
        var node = JSRuntimeManager.createNodeJS();

        assertDoesNotThrow(() -> node.exec(new File("src/test/resources/jsTests/interfaceTest.js")));
    }
}