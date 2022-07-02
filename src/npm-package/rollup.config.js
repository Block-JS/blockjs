import dts from 'rollup-plugin-dts';

const config = [
    {
        input: 'out/index.js',
        output: {
            file: 'dist/index.js',
            format: 'cjs',
        }
    },
    {
        input: 'out/index.d.ts',
        output: {
            file: 'dist/index.d.ts',
        },
        plugins: [dts()]
    }
]

export default config;