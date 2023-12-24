const fs = require("fs");

const filter = require("./src/filter");

// @note import plugins
const pluginRss = require("@11ty/eleventy-plugin-rss");


async function parse_edn_frontmatter(frontmatter) {
  const { loadFile } = await import('nbb');
  const { compileFrontmatter } = await loadFile("run_clj.cljs");
  const data = await compileFrontmatter(frontmatter);
  return data
}


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
    const { compileFile } = await loadFile("template_engine_clj.cljs");
    const { htmlString, deps } = await compileFile(inputPath, {data: data});
    this.addDependencies(inputPath, deps);

    return htmlString
  };

}

module.exports = function (eleventyConfig) {
  // @configuration avoid using .gitignore to tell eleventy what should/should
  // not be watched
  eleventyConfig.setUseGitIgnore(false);

  eleventyConfig.addPassthroughCopy("./src/css");

  eleventyConfig.addPassthroughCopy("./src/site.webmanifest");

  eleventyConfig.addFilter("dateFilter", filter.dateFilter);

  eleventyConfig.addFilter("w3cDate", filter.w3cDate);

  // @note add custom delims for frontmatter - required to avoid throwing
  // errors in CLJS files
  eleventyConfig.setFrontMatterParsingOptions({
    delims: [";;;", ";;;"],
    engines: {
      edn: {
        parse: parse_edn_frontmatter,
      }
    }
  })


  // @configuration rock an RSS feed
  eleventyConfig.addPlugin(pluginRss);

  return {
    dir: {
      input: "src",
      output: "dist",
    },
    passthroughFileCopy: true,
  };
};
