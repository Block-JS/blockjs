package com.violeth.blockjs.blockjs.jsinterface;

import com.violeth.blockjs.blockjs.BlockJS;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class JSExecutionInterface {
    public File file;
//    public NodeRuntime runtime;

    public JSExecutionInterface(File file) {
        this.file = file;
    }

}
