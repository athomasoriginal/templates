const fs = require("fs");

const filter = require("./src/filter");

// @note import plugins
const pluginRss = require("@11ty/eleventy-plugin-rss");


// @note handle `clj` extension
function compile_clj(inputContent, inputPath) {

  // @todo make this dynamic or follow some kind of convention
  if(inputPath.startsWith("\.\/src\/extra")) {
    return;
  }

  return async (data) => {
    // @note enable to test against a dev version of nbb
    //
    // const { loadFile } = await import('./lib/nbb_api.js');
    const { loadFile } = await import('nbb');
    const { compileFile } = await loadFile("run_clj.cljs");
    const { htmlString } = await compileFile(inputPath, {data: data});

    return htmlString
  };

};

module.exports = function (eleventyConfig) {
  // @configuration avoid using .gitignore to tell eleventy what should/should
  // not be watched
  eleventyConfig.setUseGitIgnore(false);

  eleventyConfig.addPassthroughCopy("./src/css");

  eleventyConfig.addPassthroughCopy("./src/images");

  eleventyConfig.addPassthroughCopy("./src/site.webmanifest");

  eleventyConfig.addPassthroughCopy("./src/js");

  eleventyConfig.addFilter("dateFilter", filter.dateFilter);

  eleventyConfig.addFilter("w3cDate", filter.w3cDate);

  // @configuration rock an RSS feed
  eleventyConfig.addPlugin(pluginRss);


  // @note add support for hiccup
  eleventyConfig.addTemplateFormats("cljs");

  // @note add support for hiccup extension
  eleventyConfig.addExtension("cljs", {
    outputFileExtension: "html",
    compile: compile_clj,
    // @note we set this because when it's not set `permalink` doesn't seem
    // to work as expected.  Rather then following the instruction from
    // `permalink`, eleventy outputs to `dist` the string as the directory
    // name.  It seems the error happens in when making the permalink in
    // `TemplateContent.js` because we're custom, we automatically default to
    // permalinkNeedsCompilation to true. This feels like it shouldn't try
    // this unless `compileOptions` is specified.  Having said this, we need
    // to change the following if we want to improve this setup.  What this
    // means is that compilation aspect won't work out of the box.
    //
    // @todo verify if we actually need this and why
    compileOptions: {
      permalink: "raw"
    },
    getData: async function(inputPath) {
      // @todo make this dynamic or follow some kind of convention
      if(inputPath.startsWith("\.\/src\/extra")) {
        return;
      }

      const { loadFile } = await import('nbb');
      const { readFrontmatter } = await loadFile("run_clj.cljs");
      const data = await readFrontmatter(inputPath);
      return data
    }
  });

  eleventyConfig.addDataExtension("edn", {
    parser: async (filePath) => {
      const { loadFile } = await import('nbb');
      const { readEdn } = await loadFile("run_clj.cljs");
      const data = await readEdn(filePath);
      return data
    },
    read: false,
  });


  return {
    dir: {
      input: "src",
      // @note if we change this, it also needs to be updated in js-bundle.11ty.js
      output: "dist",
    },
    passthroughFileCopy: true,
  };
};
