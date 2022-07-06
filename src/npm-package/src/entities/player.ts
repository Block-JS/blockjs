/**
 * Gets a player and returns it as a Player object.
 * @param {string} name - Name of the player.
 * @returns {Player} - Player object.
 */
function getPlayer(name: string): Player {
    // @ts-ignore
    const obj: object = javaGetPlayer(name);

    return obj as Player;
}

/**
 * Gets a list of all online players.
 * @returns {Player[]} - Array of Player objects.
 */
function getOnlinePlayers(): Player[] {
    // @ts-ignore
    const obj: object[] = javaGetOnlinePlayers();

    return obj as Player[];
}

/**
 * Damages a player.
 * @param {Player} player - Player to damage.
 * @param {number} damage - Amount of damage to deal.
 */
function damagePlayer(player: Player, damage: number): void {
    // @ts-ignore
    javaDamagePlayer(player.name, damage);
}

/**
 * Heals a player.
 * @param {Player} player - Player to heal.
 * @param {number} health - Amount of health to restore.
 */
function healPlayer(player: Player, health: number): void {
    // @ts-ignore
    javaHealPlayer(player.name, health);
}

export type Player = {
    id: string;
    name: string;
}

export default {
    getPlayer,
    getOnlinePlayers,
    damagePlayer,
    healPlayer,
}