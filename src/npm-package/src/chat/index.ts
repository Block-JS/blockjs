import {Player} from "../entities/player";

/**
 * Broadcasts a message to the chat.
 * @param {string} message - Message to broadcast.
 */
function broadcast(message: string) {
  // @ts-ignore
    javaBroadcast(message);
}

/**
 * Sends a message to a specific player.
 * @param {Player} player - Player to send message to.
 * @param {string} message - Message to send.
 */
function whisper(player: Player, message: string) {
  // @ts-ignore
    javaWhisper(player.name, message);
}

export default {
    broadcast,
    whisper,
}