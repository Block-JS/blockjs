package com.violeth.blockjs.blockjs;

import com.eclipsesource.v8.*;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.Chat;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.Player;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.World;

import java.util.ArrayList;
import java.util.List;

public class JSRuntimeManager {
    static public List<NodeJS> runtimes = new ArrayList<>();
    static public NodeJS createNodeJS() {
        var node = NodeJS.createNodeJS();
        var runtime = node.getRuntime();

        {
            var player = new Player(runtime);
            var playerV8 = new V8Object(runtime);

            playerV8.registerJavaMethod(player, "setGameMode", "setGameMode", new Class[] {
                String.class,
                int.class
            });

            playerV8.registerJavaMethod(player, "setWalkSpeed", "setWalkSpeed", new Class[] {
                String.class,
                float.class
            });

            playerV8.registerJavaMethod(player, "setFlySpeed", "setFlySpeed", new Class[] {
                String.class,
                float.class
            });

            playerV8.registerJavaMethod(player, "setFlying", "setFlying", new Class[] {
                String.class,
                boolean.class
            });

            playerV8.registerJavaMethod(player, "setSneaking", "setSneaking", new Class[] {
                String.class,
                boolean.class
            });

            playerV8.registerJavaMethod(player, "setSprinting", "setSprinting", new Class[] {
                String.class,
                boolean.class
            });

            playerV8.registerJavaMethod(player, "setHealth", "setHealth", new Class[] {
                String.class,
                double.class
            });

            playerV8.registerJavaMethod(player, "setExhaustion", "setExhaustion", new Class[] {
                String.class,
                float.class
            });

            playerV8.registerJavaMethod(player, "setPosition", "setPosition", new Class[] {
                String.class,
                double.class,
                double.class,
                double.class
            });

            playerV8.registerJavaMethod(player, "setVelocity", "setVelocity", new Class[] {
                String.class,
                double.class,
                double.class,
                double.class
            });

            playerV8.registerJavaMethod(player, "getOnlinePlayerUUIDByName", "getOnlinePlayerUUIDByName", new Class[] {
                String.class
            });

            playerV8.registerJavaMethod(player, "getOfflinePlayerUUIDByName", "getOfflinePlayerUUIDByName", new Class[] {
                String.class
            });

            playerV8.registerJavaMethod(player, "getOnlinePlayersUUIDs", "getOnlinePlayersUUIDs", null);

            playerV8.registerJavaMethod(player, "getPlayerNameByUUID", "getPlayerNameByUUID", new Class[] {
                String.class
            });

            playerV8.registerJavaMethod(player, "doDamage", "doDamage", new Class[] {
                String.class,
                double.class
            });

            playerV8.registerJavaMethod(player, "heal", "heal", new Class[] {
                String.class,
                double.class
            });

            runtime.add("Player", playerV8);
            playerV8.release();
        }
        {
            var world = new World();
            var worldV8 = new V8Object(runtime);

            worldV8.registerJavaMethod(world, "addBlockDamageListener", "addBlockDamageListener", new Class[] {
                int.class,
                int.class,
                int.class,
                V8Function.class
            });

            worldV8.registerJavaMethod(world, "removeBlockDamageListener", "removeBlockDamageListener", new Class[] {
                int.class
            });

            worldV8.registerJavaMethod(world, "addBlockBreakListener", "addBlockBreakListener", new Class[] {
                int.class,
                int.class,
                int.class,
                V8Function.class
            });

            worldV8.registerJavaMethod(world, "removeBlockBreakListener", "removeBlockBreakListener", new Class[] {
                int.class
            });

            runtime.add("World", worldV8);
            worldV8.release();
        }
        {
            var chat = new Chat();
            var chatV8 = new V8Object(runtime);

            chatV8.registerJavaMethod(chat, "broadcast", "broadcast", new Class[] {
                String.class
            });

            chatV8.registerJavaMethod(chat, "whisper", "whisper", new Class[] {
                String.class,
                String.class
            });

            runtime.add("Chat", chatV8);
            chatV8.release();
        }

        runtimes.add(node);

        return node;
    }
}
