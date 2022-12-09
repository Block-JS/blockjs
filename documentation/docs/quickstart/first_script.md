---
sidebar_position: 2
---

# 2. Creating your first script

Create a file in your `scripts` directory called `test.js`.

## Importing BlockJS

In your `test.js` file, add this line of code:

```js title="test.js"
const blockjs = require('blockjs-mc');
```

Note the use of the old CommonJS syntax of using `require()` instead of the ECMAScript modules `import/export`. Due to a strange quirk of the tool BlockJS uses to run JavaScript code, ECMAScript modules are not supported unless you use TypeScript (more on that later) or another tool like Babel.

## Broadcasting to chat

Back in the `test.js` file, you can use the function `blockjs.chat.broadcast()` to broadcast messages to chat. Here's an example:

```js title="test.js"
blockjs.chat.broadcast("Hello world~");
```

After these two lines of code, your `test.js` file should look something like this:

```js title="test.js"
const blockjs = require('blockjs-mc');

blockjs.chat.broadcast("Hello world~");
```

## Running your scripts

On your Minecraft server itself, run `/blockjs test`. This command runs all availible scripts in the `scripts` directory. After running this command, you should see the message you had put in your test file broadcasted into chat. It's that easy!
