---
sidebar_position: 2
---

# Whisper

Send a message to a specific player

## Usage

```js
const bjs = require('blockjs-mc');

const exampler = bjs.entities.players.getPlayer('exampleplayer');

bjs.chat.whisper(exampler, 'Hi there!');
```
