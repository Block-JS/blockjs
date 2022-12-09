---
sidebar_position: 1
---

# TypeScript

BlockJS is designed with TypeScript support in mind. Adding TypeScript to BlockJS is as simple as creating a `tsconfig.json` and configuring a couple settings.

## Adding TypeScript to BlockJS

If you haven't already setup BlockJS, you can find how to do so in the [quickstart guide](/docs/quickstart/env_setup). Otherwise, in your `scripts` folder, create a `tsconfig.json` file.

### Configuring TypeScript

In your tsconfig's `compilerOptions`, there are a few items you have to set:

- `module: 'commonjs'`, this tells the TypeScript compiler to compile and ECMAScript modules we may use to CommonJS syntax at compilation.

- `target: 'ES2015'`, this tells the TypeScript compiler to target the ES2015 syntax of JavaScript.

- `esModuleInterop: true`, this option being true lets us import CommonJS modules as if they were ECMAScript modules.
