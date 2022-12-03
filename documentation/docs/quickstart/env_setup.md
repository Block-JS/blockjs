---
sidebar_position: 1
---

# 1. Setting up enviorment

To begin, make sure you have Node.js and NPM installed. You can get these both via the [Node Version Manager (nvm)](https://github.com/nvm-sh/nvm)

Make sure you also have a Minecraft server instance set up in order to test your scripts.

## Downloading BlockJS

You can grab the latest release from the [BlockJS GitHub Repo](https://github.com/Block-JS/blockjs) from the releases tab on the right. Put the JAR file you download into your Minecraft server's `plugins` folder.

## Setting up server for BlockJS Development

Create a folder in your server's root directory called `blockjs`, inside that folder create another folder titled `scripts`.

Open the scripts folder in your IDE of choice and run `npm init` to initalize the folder with NPM. Then, run `npm i blockjs-mc` to install the BlockJS node package into your project
