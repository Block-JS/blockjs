package com.violeth.blockjs.blockjs;

import com.caoccao.javet.enums.JSRuntimeType;
import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interop.NodeRuntime;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.interop.converters.JavetProxyConverter;
import com.caoccao.javet.interop.engine.JavetEngineConfig;
import com.caoccao.javet.interop.engine.JavetEnginePool;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.Chat;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.Player;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.World;

import java.util.ArrayList;
import java.util.List;

public class JSRuntimeManager {
    static public List<NodeRuntime> runtimes = new ArrayList<>();

    static public NodeRuntime createNodeJS() {
        JavetEnginePool<NodeRuntime> pool = new JavetEnginePool<NodeRuntime>(new JavetEngineConfig().setJSRuntimeType(JSRuntimeType.Node));

        try (var runtime = pool.getEngine().getV8Runtime()) {
            JavetProxyConverter proxyConverter = new JavetProxyConverter();

            runtime.setConverter(proxyConverter);

            runtime.getGlobalObject().set("Player", Player.class);
            runtime.getGlobalObject().set("World", World.class);
            runtime.getGlobalObject().set("Chat", Chat.class);

            runtimes.add(runtime);

            return runtime;
        } catch (JavetException e) {
            throw new RuntimeException(e);
        }
    }
}
