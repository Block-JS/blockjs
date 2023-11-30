package io.github.blockjs.plugin.binds

import com.caoccao.javet.values.reference.V8ValueFunction

class Binds {
    private var nextAvailableId = 0

    /** TODO: Use one callback map for every event instead of each new map for each block?
     * Cons: for each block event there has to be stored position instead of one BlockBinds entry(memory)
     * Pros: you only check one map(performance), less code  */
    var onBlockDamage = mutableListOf<BlockBinds>()
    var onBlockBreak = mutableListOf<BlockBinds>()
    fun addBlockDamageEventListener(x: Int, y: Int, z: Int, callback: V8ValueFunction): Int {
        for (blockBinds in onBlockDamage) {
            if (blockBinds.x == x && blockBinds.y == y && blockBinds.z == z) {
                val newBlockBind = BlockBind(nextAvailableId++, callback)

                blockBinds.map.put(newBlockBind.id, newBlockBind)

                return newBlockBind.id
            }
        }

        val newBlockBinds = BlockBinds(x, y, z)
        val newBlockBind = BlockBind(nextAvailableId++, callback)

        newBlockBinds.map[newBlockBind.id] = newBlockBind
        onBlockDamage.add(newBlockBinds)

        return newBlockBind.id
    }

    fun removeBlockDamageEventListener(id: Int) {
        for (blockBinds in onBlockDamage) {
            val removedBind = blockBinds.map.remove(id)

            if (removedBind != null) {
                removedBind.callback.clearWeak()

                return
            }
        }
        /** Use this instead if you're doing concurrent modifications  */
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

    fun addBlockBreakEventListener(x: Int, y: Int, z: Int, callback: V8ValueFunction): Int {
        for (blockBinds in onBlockBreak) {
            if (blockBinds.x == x && blockBinds.y == y && blockBinds.z == z) {
                val newBlockBind = BlockBind(nextAvailableId++, callback)

                blockBinds.map[newBlockBind.id] = newBlockBind

                return newBlockBind.id
            }
        }
        val newBlockBinds = BlockBinds(x, y, z)
        val newBlockBind = BlockBind(nextAvailableId++, callback)

        newBlockBinds.map[newBlockBind.id] = newBlockBind
        onBlockBreak.add(newBlockBinds)

        return newBlockBind.id
    }

    fun removeBlockBreakEventListener(id: Int) {
        for (blockBinds in onBlockBreak) {
            val removedBind = blockBinds.map.remove(id)

            if (removedBind != null) {
                removedBind.callback.clearWeak()

                return
            }
        }
    }
}
