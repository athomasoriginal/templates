const fs = require("fs");

const filter = require("./src/filter");

// @note import plugins
const pluginRss = require("@11ty/eleventy-plugin-rss");

module.exports = function (eleventyConfig) {
  // @configuration avoid using .gitignore to tell eleventy what should/should
  // not be watched
  eleventyConfig.setUseGitIgnore(false);

  eleventyConfig.addPassthroughCopy("./src/css");

  eleventyConfig.addPassthroughCopy("./src/images");

  eleventyConfig.addPassthroughCopy("./src/site.webmanifest");

  eleventyConfig.addFilter("dateFilter", filter.dateFilter);

  eleventyConfig.addFilter("w3cDate", filter.w3cDate);

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
