function getPlayer(name: string): Player {
    // @ts-ignore
    const obj: object = javaGetPlayer(name);

    return obj as Player;
}

export type Player = {
    id: string;
    name: string;
}

export default {
    getPlayer,
}