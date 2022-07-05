import {Player} from "../entities/player";

function broadcast(message: string) {
  // @ts-ignore
    javaBroadcast(message);
}

function whisper(player: Player, message: string) {
  // @ts-ignore
    javaWhisper(player.name, message);
}

export default {
    broadcast,
    whisper,
}