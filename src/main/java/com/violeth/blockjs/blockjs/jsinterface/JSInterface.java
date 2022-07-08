package com.violeth.blockjs.blockjs.jsinterface;

import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interop.NodeRuntime;
import com.caoccao.javet.interop.V8Host;

import java.io.File;

public final class JSInterface {
    public File file;

    public JSInterface(File file) {
        this.file = file;
    }

    public void registerAndRun() throws JavetException {
        try (NodeRuntime runtime = V8Host.getNodeInstance().createV8Runtime()) {
            // TODO: register callbacks
            runtime.getExecutor(file).execute();
        }
    }
}
