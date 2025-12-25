#  {{main/ns}}

{{description}}

- [Housekeeping]
- [Quickstart]
- [Feature Choices]
- [Custom Template Engine Clojure]
  - [CLJS & Frontmatter]
  - [CLJS & Data Cascade]
  - [EDN Data]
  - [Adding JavaScript]
- [Project Commands]
- [Data Usage]
- [Before Dev]
- [Before Deploy]


## Housekeeping

Be sure to have the following tools installed

- [Node]
- [Yarn]

## Quickstart

- Move into `{{main/ns}}`
- Install JS dependencies
  ```command
  yarn install
  ```
  > Wait for everything to install before running the next step
- Start the project
  ```command
  yarn dev
  ```

Visit http://localhost:8080/


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

## Custom Template Engine Clojure

We add Custom Template Engine and Data support for `cljs` and `edn`.

I enjoy `eleventy`, but I don't like the templating solutions provided and I
already have a large body of code which I would like to reuse for these
projects because my daily language is `clj/s`. My original goal was to support
what would get me `hiccup`, but then I saw it would be more straightforward to
just handle `cljs` files as a whole.

The choice of [nbb] is because it can be install via node which means we don't
have to add any deps to our server and this template will continue to work
and live well with all the existing `eleventy` literature.

The `hiccup` itself is parsed via `reagent`.

With this in mind, this section will outline how to use `cljs` as a templating
language:

* CLJS & Data Cascade
* CLJS & Frontmatter
* CLJS & EDN Data
* CLJS & Extra


### CLJS & Data Cascade

All `page` components will take the following shape:

```clojure
(defn page
  [{:keys [data]}]
  [:div
    [:h1 "Welcome to " (.. data -site -name)]]
```

`data` is vanilla JS so you'll access it using `cljs` interop.

### CLJS & Frontmatter

To use `frontmatter` in your `.cljs` files we don't add it to the top of the
`.cljs` file because `---` isn't supported.  Furthermore, we don't
upate the `delim` because this means that all of your templates,
regardless of whether or not they are `.cljs`, have to use our new `delim`
approach.  Finally, even with our own `delim` set, we would also have to add
a parser to take advantage of `.edn`.  Again, this isn't challenging, but it's
an additional edge case to work around.  Instead, what we do is build in
functionality so that you can define a a clojure function called `front-matter`
in your `.cljs` file and specify your `front-matter` in there.

Example from `index.cljs`

```clojure
(ns src.index)

(defn front-matter
  []
  #js{:layout    "html-base"
      :title     "home"
      :permalink "index.html"})


(defn page
  [data]
  [:h1 "Welcome!"])
```

With the above, `page` will render your code and then `front-matter`
will be read in as front-matter and we don't need to change additional eleventy
configs.

### EDN Data

`edn` can be used as an alternative to JSON for your eleventy `data` files

- Add an edn file to the data dir
  ```command
  touch  _data/transactions.edn
  ```
- Add data to `transactions.edn`
  ```command
  {:items [{:name "A Name"}]}
  ```

The above will be automatically handled because of the additional support
provided to our JS.

### CLJS & Extra

`extras` is a dir you can put your supplementary support code.  The reason we
specify is because we tell Eleventy to ignore builds on these files for
performance.

## Project Commands

- Run development environment
  ```command
  yarn dev
  ```
- Run development environment in debug mode
  ```command
  yarn dev:debug
  ```
- Build for production
  ```command
  yarn build
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
[Custom Template Engine Clojure]: #custom-template-engine-clojure
[CLJS & Frontmatter]: #cljs-&-frontmatter
[CLJS & Data Cascade]: #cljs-&-data-cascade
[CLJS & Extra]: #cljs-&-extra
[EDN Data]: #edn-data
[Project Commands]: #project-commands
[Data Usage]: #data-usage
[Before Dev]: #before-dev
[Before Deploy]: #before-deploy



[Eleventy]: https://www.11ty.dev/
[Node]: https://nodejs.org/en/
[Yarn]: https://yarnpkg.com/getting-started/install
[HTML5 Boilerplate]: https://html5boilerplate.com/
[Modern CSS Reset]: https://github.com/andy-piccalilli/modern-css-reset
