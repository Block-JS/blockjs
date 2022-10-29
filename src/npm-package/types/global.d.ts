export {}

declare global {
    type UUID = string
    
    class Chat {
        static broadcast(message: string)
        static whisper(playerUUID: UUID, message: string)
    }

    class Player {
        static setGameMode(playerUUID: UUID, gameMode: number)
        static setWalkingSpeed(playerUUID: UUID, speed: number)
        static setFlySpeed(playerUUID: UUID, speed: number)
        static setFlying(playerUUID: UUID, state: boolean)
        static setSneaking(playerUUID: UUID, state: boolean)
        static setSprinting(playerUUID: UUID, state: boolean)
        static setHealth(playerUUID: UUID, health: number)
        static setExhaustion(playerUUID: UUID, exhaustion: number)
        static setPosition(playerUUID: UUID, x: number, y: number, z: number)
        static setVelocity(playerUUID: UUID, x: number, y: number, z: number)

        static getOnlinePlayerUUIDByName(name: string): string
        static getOfflinePlayerUUIDByName(name: string): string
        static getOnlinePlayersUUIDs(): string[]
        static getPlayerNameByUUID(playerUUID: UUID): string

        static doDamage(playerUUID: UUID, damage: number)
        static heal(playerUUID: UUID, heal: number)
    }

    type ListenerId = number

    class World {
        static addBlockDamageListener(x: number, y: number, z: number, callback: () => void): ListenerId
        static removeBlockDamageListener(id: ListenerId)

        static addBlockBreakListener(x: number, y: number, z: number, callback: () => void): ListenerId
        static removeBlockBreakListener(id: ListenerId)
    }
}