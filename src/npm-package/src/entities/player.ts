/**
 * Gets a player and returns it as a Player object.
 * @param {string} name - Name of the player.
 * @returns {Player} - Player object.
 */
function getPlayer(name: string): Player | null {
    // TODO: Make get player capable of getting offline players in addition to online players.

    // @ts-ignore
    const id: string = JavaPlayer.getOnlinePlayerUUIDByName(name);

    if (id === null) {
        return null;
    }

    return {
        name: name,
        id: id
    } as Player;
}

/**
 * Gets a list of all online players.
 * @returns {Player[]} - Array of Player objects.
 */
function getOnlinePlayers(): Player[] {
    // @ts-ignore
    const uuidArray: string[] | null = JavaPlayer.getOnlinePlayers();
    // @ts-ignore
    const nameArray: string[] | null = JavaPlayer.getOnlinePlayerNames();

    if (uuidArray === null || nameArray === null) {
        return [];
    } else if (uuidArray.length !== nameArray.length) {
        throw new Error("Unexpected result: UUID and name arrays are not the same length.");
    }

    const players: Player[] = [];

    uuidArray.forEach((uuid: string, index: number) => {
        players.push({
            name: nameArray[index],
            id: uuid
        });
    });

    return players;
}

/**
 * Damages a player.
 * @param {Player} player - Player to damage.
 * @param {number} damage - Amount of damage to deal.
 */
function damagePlayer(player: Player, damage: number): void {
    // @ts-ignore
    JavaPlayer.doDamage(player.id, damage);
}

/**
 * Heals a player.
 * @param {Player} player - Player to heal.
 * @param {number} health - Amount of health to restore.
 */
function healPlayer(player: Player, health: number): void {
    // @ts-ignore
    JavaPlayer.heal(player.id, health);
}

export type Player = {
    id: string;
    name: string;
}

export {
    getPlayer,
    getOnlinePlayers,
    damagePlayer,
    healPlayer,
}