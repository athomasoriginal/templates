# Templates

A collection of common templates I use to start different kinds of projects

- [Housekeeping]
- [Getting Started]
- [Usage]
  - [Global Alias]
- [Development]
- [Custom Template Engine Clojure]
  - [CLJS & Frontmatter]
  - [CLJS & Data Cascade]
  - [EDN Data]
  - [Adding JavaScript]
- [License]

## Housekeeping

Before starting with this project, please make sure you have the following
tools installed:

- [Install Java]
- [Install Clojure CLI Tools]


## Getting Started

These templates are meant for use with [deps-new].  To create a new project:

```command
clojure -Sdeps \
  '{:deps {io.github.athomasoriginal/templates {:git/sha "2dd0ac94f77251ad9c1ba47d223c36f1d091f9f8"}}}' \
  -Tnew create \
  :template athomasoriginal/reagent \
  :name myusername/my-app-name
```

The above will get you started with a `reagent` app.

If the above seems like a lot to type out, it is.  Please see the [usage] section
below for alternative approaches to running this app.


## Usage

### Global Alias

The above is a lot to type.  It's only meant to give the reader the least amount
of things to do to get things going.  However, if you're like me and using this
tool a lot, you're going to want to setup an alias.

- Open your global `.clojure` directory
  ```bash
  vim ~/.clojure
  ```
  > Note that `vim` is _my_ editor.  If you're not using `vim`, replace `vim`
  > with the CLI command for your editor of choice.  Or just open `~/.clojure`
  > from inside of your fav' editor.
- Add the [new global alias] to the `deps.edn` file in `~/.clojure`
- Create a new template
  ```command
  clj -T:new :name nike/app-name
  ```
  > `nike` is your organization name.  `app-name` is the name of your app and
  > will be the name of your top level folder.

## Development

- Clone this repo
- Move into this repo
- Modify the contents
- Build a template from local repo
  ```command
  clojure -Sdeps '{:deps {athomasoriginal/templates {:local/root "."}}}' \
          -Tnew create \
          :template athomasoriginal/reagent \
          :name mygithubhandle/myreagent
  ```

## Custom Template Engine Clojure

This template comes with Custom Template Engine and Data support for `cljs`
and `edn`.

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

* CLJS & Frontmatter
* CLJS & Data Cascade
* CLJS & EDN Data

### CLJS & Frontmatter

To use `frontmatter` in your `.cljs` files we don't add it to the top of the
`.cljs` file because `---` isn't supported.  Furthermore, we don't advocate
for upating the `delim` because this means that all of your templates,
regardless of whether or not they are `.cljs` have to use our alternate delim
approach.  Finally, even with our own `delim` set, we would also have to add
a parser to take advantage of `.edn`.  Again, this isn't challenging, but it's
an additional edge case to work around.  Instead, what we do is build in
functionality so that you can define a a clojure function called `front-matter`
in your `.cljs` file and specify you `front-matter` in there.

Example:

```clojure
(ns src.entry)

(defn front-matter
  []
  #js{:layout    "html-base.njk"
      :permalink "entry.html"})


(defn page
  [data]
  [:h1 "Welcome!"])
```

With the above, `some-hiccup` will render your code and then `front-matter`
will be read in as front-matter and we don't need to change additional eleventy
configs.

### CLJS & Data Cascade

All `page` components will take the following shape:

```clojure
(defn page
  [{:keys [data]}]
  [:div
    [:h1 "Welcome to " (.. data -site -name)]]
```

`data` is vanilla JS so you'll access it using `cljs` interop.

### EDN Data

`edn` is a better file format compared to JSON from a developer experience
perspective.  For example, you can add comments and it's relatively terse.

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

## License

Copyright © 2023 Thomas Mattacchione

Distributed under the Eclipse Public License version 1.0.


[Housekeeping]: #housekeeping
[Getting Started]: #getting-started
[Usage]: #usage
[Global Alias]: #global-alias
[Development]: #development
[Custom Template Engine Clojure]: #custom-template-engine-clojure
[CLJS & Frontmatter]: #cljs-&-frontmatter
[CLJS & Data Cascade]: #cljs-&-data-cascade
[EDN Data]: #edn-data
[License]: #license

[deps-new]: https://github.com/seancorfield/deps-new
[Install Java]: https://www.youtube.com/watch?v=SljDPNwAFOc&t=16s
[Install Clojure CLI Tools]: https://www.youtube.com/watch?v=5_q5pLoz9b0
[new global alias]: https://github.com/athomasoriginal/dotfiles/blob/master/.clojure/deps.edn#L39
[nbb]: https://github.com/babashka/nbb
