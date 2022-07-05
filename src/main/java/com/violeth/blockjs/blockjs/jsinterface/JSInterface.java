package com.violeth.blockjs.blockjs.jsinterface;

import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.NodeJS;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.Chat;
import com.violeth.blockjs.blockjs.jsinterface.mcinterface.entitys.Players;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;

public final class JSInterface {
    private final NodeJS runner = NodeJS.createNodeJS();

    public File file;

    public JSInterface(File file) {
        this.file = file;
    }

    public void runJS() {
        runner.exec(file);

        while (runner.isRunning()) {
            runner.handleMessage();
        }

        runner.release();
    }

    public void registerCallbacks() {
        // Chat
        Chat chat = new Chat();

        runner.getRuntime().registerJavaMethod(chat, "broadcast", "javaBroadcast",
                new Class[] { String.class });

        // Players
        Players players = new Players();

        JavaCallback getPlayerCallback = (v8Object, v8Array) -> {
            if (v8Array.length() == 1) {
                String name = v8Array.getString(0);
                Player player = players.getPlayer(name);

                if (player == null) {
                    return null;
                }

                V8Object playerObject = new V8Object(runner.getRuntime());

                playerObject.add("name", player.getName());
                playerObject.add("uuid", player.getUniqueId().toString());

                return playerObject;
            } else {
                return null;
            }
        };

        JavaCallback getOnlinePlayersCallback = (v8Object, v8Array) -> {
            V8Array playersArray = new V8Array(runner.getRuntime());

            for (Player player : players.getOnlinePlayers()) {
                V8Object playerObject = new V8Object(runner.getRuntime());

                playerObject.add("name", player.getName());
                playerObject.add("uuid", player.getUniqueId().toString());

                playersArray.push(playerObject);
            }

            return playersArray;
        };

        runner.getRuntime().registerJavaMethod(getPlayerCallback, "javaGetPlayer");
        runner.getRuntime().registerJavaMethod(getOnlinePlayersCallback, "javaGetOnlinePlayers");
        runner.getRuntime().registerJavaMethod(players, "damage", "javaDamagePlayer",
                new Class[] { String.class, double.class });
        runner.getRuntime().registerJavaMethod(players, "heal", "javaHealPlayer",
                new Class[] { String.class, double.class });
    }

    public void registerAndRun() {
        registerCallbacks();
        runJS();
    }
}
