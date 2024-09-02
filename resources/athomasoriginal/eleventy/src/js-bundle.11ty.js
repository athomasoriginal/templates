const esbuild = require('esbuild')
 const { NODE_ENV = 'production' } = process.env

 const isProduction = NODE_ENV === 'production'

 module.exports = class {
   data() {
     return {
       // @note stop 11ty from creating a file: esbuild will do this for us below
       permalink: false,
       eleventyExcludeFromCollections: true
     }
   }

   async render() {
     await esbuild.build({
       entryPoints: ['src/js/index.js'],
       bundle: true,
       minify: isProduction,
       outdir: 'dist/js',
       sourcemap: !isProduction,
       target: isProduction ? 'es6' : 'esnext'
     })
   }
 }
