import {Player} from "../entities/player";

/**
 * Broadcasts a message to the chat.
 * @param {string} message - Message to broadcast.
 */
function broadcast(message: string) {
  // @ts-ignore
    javaChat.broadcast(message);
}

/**
 * Sends a message to a specific player.
 * @param {Player} player - Player to send message to.
 * @param {string} message - Message to send.
 */
function whisper(player: Player, message: string) {
  // @ts-ignore
    javaChat.whisper(player.name, message);
}

export {
    broadcast,
    whisper,
}