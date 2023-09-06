---
title: The state of BlockJS
description: Important info on the current state of BlockJS and future plans
slug: state-of-bjs
authors: thegreatviolet
date: 2023-09-05T01:00
---

Hello everyone. As I'm sure all 3 or so of you watching this project have noticed, this project has remained quite dormant for a while now. This is due to a couple factors, namely of which me being one of the only developers, and trying to get a tool which has very little documentation in a language which I am neither a big fan of nor well versed in has been... frustrating.

So today, I would like to outline some of the next steps I'm planning on taking with the BlockJS project.

## Return to development

Firstly, I'm intending to soon return to actually developing BlockJS. I do of course have school starting soon; however, I'm hoping I'll be able to make time to actually work on the project when I'm not busy with school. Speaking of development, **I am really really desperate for some help on this project!!** This has by far been one of the biggest undertakings I've taken on for a hobby project, and the workload has been quite overwhelming with the main development coming from just me. So, if you have some changes that you think would be valuable to the development of BlockJS, **PLEASE** do not hesitate to open a pull request, I'm always happy to provide feedback if nothing else.

## Code rewrite

I am also planning to, in the very near future, do a complete rewrite of the BlockJS plugin codebase. I am planning for the rewrite to hopefully be in Kotlin instead of Java, as I vastly prefer it over the latter. The main issue will most likely be finding a JavaScript runtime that works well with Kotlin. Speaking of which...

## Moving away from J2V8

BlockJS has been widely built on the J2V8 bindings for the V8 runtime. However, the project has been vastly unmaintained for the past 2 years, in addition to lacking a lot of features I'm desiring (namely ES6 syntax). As such I, [and other users](https://github.com/Block-JS/blockjs/issues/2), have made the recommendation to switch away from J2V8.

However this is unfortunately not as simple as it may initially seem. I have been trying for a while to use the tool [Javet](https://github.com/caoccao/Javet). Unfortunately, this tool like J2V8 is not without its caveats. It also happens to be missing many features that I want to implement into BlockJS, namely being able to import NPM modules seamlessly. While I'm sure that this is possible to do with Javet, I found the processes recommended to me to be very unwieldy, so I am currently in the process of looking for a replacement that also works well with Kotlin.

## New features!

Finally, once these two (admittedly quite large) tasks are completed I intend to start implementation of new features into BlockJS. Some of the plans I have include:

- Many new methods

- Custom commands

- Event triggers

- An eventual version 1.0 release!

This is only a small taste of what I have planned for BlockJS, and I hope that you all will stick with me through this admittedly rough transition period.

\- Violet
