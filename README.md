# Templates

A collection of code project templates

- [Housekeeping]
- [Getting Started]
- [Usage]
  - [Global Alias]
  - [Templates]
    - [app]
    - [bb]
    - [c_app]
    - [spa]
    - [static site]
- [Development]
- [License]

## Housekeeping

Before starting with this project, please make sure you have the following
tools installed:

- [Install Java]
- [Install Clojure CLI Tools]


## Getting Started

To create a new project based on a template in this project:

```command
clojure -Sdeps \
  '{:deps {io.github.athomasoriginal/templates {:git/sha "d939c679db7ebc1baeecbcde44ed2478b1dbe56b"}}}' \
  -Tnew create \
  :template athomasoriginal/spa \
  :name myusername/my-app-name
```

The above will get you started with a `clojuresript` SPA app.

The above is a lot to type.  See the [usage] section below for how I run it.

## Usage

### Global Alias

To avoid typing all the above out, I create a `Clojure CLI Tools` alias. Here's
how you do that.

> The following assumes you've gone through the [Housekeeping] steps

- Open your global `.clojure` directory
  ```bash
  vim ~/.clojure/deps.edn
  ```
  > Note that `vim` is _my_ editor.  If you're not using `vim`, replace `vim`
  > with the CLI command for your editor of choice.  Or just open `~/.clojure`
  > from inside of your fav' editor.
- Open my [clojure global alias file]
  > The above link should auto-highlight the `:new` alias
- Copy the `:new` alias
  > copy all the highlighted lines
- Add the `:new` alias to your `deps.edn` file in `~/.clojure`
  > Reference my `~/.clojure/deps.edn` if you're not sure what it should look
  > like.
- Create a new template
  ```command
  clj -T:new :name nike/app-name
  ```
  > `nike` is your organization name.  `app-name` is the name of your app and
  > will be the name of your top level folder.

### Templates

The following will provide examples of the available templates, what they are
for and examples commands to create them.

This encapsulates the kinds of apps I would build and how I would start them.
A quick run through:

* Am I building a `quick demo` code project? see [bb]
* Am I building a `multi-page`/`backend` web app? see [app]
* Am I building a `SPA` frontend? see [spa]
* Am I building a `Static Site` web app? see [static site]
* Am I building a `Native App` ? see [c_app]

#### app

Create `app`

```command
clj -T:new :template athomasoriginal/app :name app-name
```

This is a `clojure` web app backend.  I use this as a starting point for
creating fullstack clojure web apps.

#### bb

Create `bb`

```command
clj -T:new :template athomasoriginal/bb :name app-name
```

This is a `bb` app.  I use this as a starting point for creating a bb app.
`bb` is often what I will use when I'm experimenting with an idea and
don't require a full clojure setup. It's helpful because the startup is fast
and it contains a lot of built-in tools.

#### c_app

Create `c_app`

```command
clj -T:new :template athomasoriginal/c_app :name app-name
```

This is a `c` app.  I use this as a starting point for creating `c` apps. It's
currently built to support macos/linux.

#### static site

Create `static site`

```command
clj -T:new :template athomasoriginal/static-site :name app-name
```

This is a static site generator app using `static site`.  I use this as a
starting point for any project which should be a static app (e.g. a marketing
site).  What makes this special is that it comes with a custom templating
engine for `clojurescript`.

#### spa

Create `spa`

```command
clj -T:new :template athomasoriginal/spa :name app-name
```

This is a SPA app using `spa` (clojurescript) app.  If I start a SPA, this
is how I do that.

## Development

- Clone this repo
- Move into this repo
- Modify the contents
- Build a template from local repo
  ```command
  clojure -Sdeps '{:deps {athomasoriginal/templates {:local/root "."}}}' \
          -Tnew create \
          :template athomasoriginal/spa \
          :name mygithubhandle/myspa
  ```

## License

Copyright © 2023 Thomas Mattacchione

Distributed under the Eclipse Public License version 1.0.


[Housekeeping]: #housekeeping
[Getting Started]: #getting-started
[Usage]: #usage
[Global Alias]: #global-alias
[Templates]: #templates
[app]: #app
[bb]: #bb
[c_app]: #c_app
[static site]: #static-site
[spa]: #spa
[Development]: #development
[License]: #license

[deps-new]: https://github.com/seancorfield/deps-new
[Install Java]: https://www.youtube.com/watch?v=SljDPNwAFOc&t=16s
[Install Clojure CLI Tools]: https://www.youtube.com/watch?v=5_q5pLoz9b0
[clojure global alias file]: https://github.com/athomasoriginal/dotfiles/blob/master/.clojure/deps.edn#L40-L44
[nbb]: https://github.com/babashka/nbb
