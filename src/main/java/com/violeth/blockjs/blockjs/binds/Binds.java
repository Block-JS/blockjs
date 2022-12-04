package com.violeth.blockjs.blockjs.binds;

import com.eclipsesource.v8.V8Function;

import java.util.ArrayList;
import java.util.List;

public class Binds {
    public int nextAvailableId = 0;
    /** TODO: Use one callback map for every event instead of each new map for each block?
     *      Cons: for each block event there has to be stored position instead of one BlockBinds entry(memory)
     *      Pros: you only check one map(performance), less code */
    public List<BlockBinds> onBlockDamage = new ArrayList<>();
    public List<BlockBinds> onBlockBreak = new ArrayList<>();
    public List<BlockBinds> onBlockInteract = new ArrayList<>();
    public int addBlockDamageEventListener(int x, int y, int z, V8Function callback) {
        for(var blockBinds: onBlockDamage) {
            if(blockBinds.x == x && blockBinds.y == y && blockBinds.z == z) {
                var newBlockBind = new BlockBind(nextAvailableId++, callback);

                blockBinds.map.put(newBlockBind.id, newBlockBind);

                return newBlockBind.id;
            }
        }

        var newBlockBinds = new BlockBinds(x, y, z);
        var newBlockBind = new BlockBind(nextAvailableId++, callback);

        newBlockBinds.map.put(newBlockBind.id, newBlockBind);

        onBlockDamage.add(newBlockBinds);

        return newBlockBind.id;
    }
    public void removeBlockDamageEventListener(int id) {
        for(var blockBinds: onBlockDamage) {
            var removedBind = blockBinds.map.remove(id);

            if(removedBind != null) {
                return;
            }
        }
        /** Use this instead if you're doing concurrent modifications */
//        for(var blockBindsIterator = onBlockDamage.iterator(); blockBindsIterator.hasNext();) {
//            var blockBinds = blockBindsIterator.next();
//
//            var blockBindEntrySet = blockBinds.map.entrySet();
//
//            for(var blockBindIterator = blockBindEntrySet.iterator(); blockBindIterator.hasNext();) {
//                var blockBind = blockBindIterator.next();
//
//                if(blockBind.getKey() == id) {
//                    blockBindIterator.remove();
//
//                    return;
//                }
//            }
//        }
    }
    public int addBlockBreakEventListener(int x, int y, int z, V8Function callback) {
        for(var blockBinds: onBlockBreak) {
            if(blockBinds.x == x && blockBinds.y == y && blockBinds.z == z) {
                var newBlockBind = new BlockBind(nextAvailableId++, callback);

                blockBinds.map.put(newBlockBind.id, newBlockBind);

                return newBlockBind.id;
            }
        }

        var newBlockBinds = new BlockBinds(x, y, z);
        var newBlockBind = new BlockBind(nextAvailableId++, callback);

        newBlockBinds.map.put(newBlockBind.id, newBlockBind);

        onBlockBreak.add(newBlockBinds);

        return newBlockBind.id;
    }
    public void removeBlockBreakEventListener(int id) {
        for (var blockBinds : onBlockBreak) {
            var removedBind = blockBinds.map.remove(id);

            if (removedBind != null) {
                return;
            }
        }
    }
    public int addBlockInteractEventListener(int x, int y, int z, V8Function callback) {
        for(var blockBinds: onBlockInteract) {
            if(blockBinds.x == x && blockBinds.y == y && blockBinds.z == z) {
                var newBlockBind = new BlockBind(nextAvailableId++, callback);

                blockBinds.map.put(newBlockBind.id, newBlockBind);

                return newBlockBind.id;
            }
        }

        var newBlockBinds = new BlockBinds(x, y, z);
        var newBlockBind = new BlockBind(nextAvailableId++, callback);

        newBlockBinds.map.put(newBlockBind.id, newBlockBind);

        onBlockInteract.add(newBlockBinds);

        return newBlockBind.id;
    }
    public void removeBlockInteractEventListener(int id) {
        for (var blockBinds : onBlockInteract) {
            var removedBind = blockBinds.map.remove(id);

            if (removedBind != null) {
                return;
            }
        }
    }
}
