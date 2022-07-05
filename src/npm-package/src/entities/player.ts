function getPlayer(name: string): Player {
    // @ts-ignore
    const obj: object = javaGetPlayer(name);

    return obj as Player;
}

function getOnlinePlayers(): Player[] {
    // @ts-ignore
    const obj: object[] = javaGetOnlinePlayers();

    return obj as Player[];
}

function damagePlayer(player: Player, damage: number): void {
    // @ts-ignore
    javaDamagePlayer(player.name, damage);
}

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