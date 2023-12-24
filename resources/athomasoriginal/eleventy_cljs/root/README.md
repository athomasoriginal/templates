##  {{main/ns}}


{{description}}

- [Housekeeping]
- [Quickstart]
- [Feature Choices]
- [Data Usage]
- [Before Dev]
- [Before Deploy]


## Housekeeping

Be sure to have the following tools installed

- [Node]
- [Yarn]

## Quickstart

- Move into `{{main/ns}}
- Install JS dependencies
  ```command
  yarn install
  ```
  > Wait for everything to install before running the next step
- Start the project
  ```command
  yarn run dev
  ```

## Feature Choices

This section includes tooling and pattern choices made for this template.

- [HTML5 Boilerplate]
- [Modern CSS Reset]
- A few useful filters:
  - `dateFilter` & `w3DateFilter`
- Example Blog Post
- RSS feed
- Trailing slashes
- Social sharing previews
  - Open Graph
  - Twitter

## Project Commands

- Run development environment
  ```command
  yarn run dev
  ```
- Run development environment in debug mode
  ```command
  yarn run dev:debug
  ```
- Build for production
  ```command
  yarn run build
  ```

## Data Usage

There is a priority order for how Data is consumed by Eleventy.  Familarize yourself with that.  The `_data/site.json` is meant to contain reusable, site related global variables.  Example of what this is not for:  post front matter data.

## Before Dev

- [ ] Update `_data/site.json`
  - [ ] Update the URL links + Ensure robots.txt is setup correctly

## Before Deploy

- [ ] Add favicon
- [ ] Update sitemap
  - [ ] Add `favicon.ico` to `src`
  - [ ] Update `eleventy.js` w/ `eleventyConfig.addPassthroughCopy("./src/favicon.ico");`
- [ ] Add Apple Touch Icon
  - [ ] Add `apple-icon.png` to `src`
  - [ ] Update `eleventy.js` w/ `eleventyConfig.addPassthroughCopy("./src/apple-icon.png");`
- [ ] Verify sitemap
  - [ ] Consider whether or not to include landing pages
- [ ] Add analytics tracking
- [ ] Update social media cards - OG, Twitter etc
- [ ] Add analytics tracking
- [ ] Add Apple Touch Icon
- [ ] Populate web.manifest
- [ ] Update site.webmanifest
- [ ] List copy for stakeholders to verify
  - [ ] Site Title
  - [ ] Site Description
  - [ ] Site Social Media Links
  - [ ] Site Favicon
  - [ ] Facebook Card
  - [ ] Twitter Card



[Housekeeping]: #housekeeping
[Quickstart]: #quickstart
[Feature Choices]: #feature-choices
[Data Usage]: #data-usage
[Before Dev]: #before-dev
[Before Deploy]: #before-deploy



[Eleventy]: https://www.11ty.dev/
[Node]: https://nodejs.org/en/
[Yarn]: https://classic.yarnpkg.com/en/docs/install/
[HTML5 Boilerplate]: https://html5boilerplate.com/
[Modern CSS Reset]: https://github.com/andy-piccalilli/modern-css-reset
