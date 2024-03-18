# Templates

A collection of common templates I use to start different kinds of projects

- [Housekeeping]
- [Getting Started]
- [Usage]
  - [Global Alias]
- [Development]
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
  '{:deps {io.github.athomasoriginal/templates {:git/sha "cae9412cfd6bc556ce694ad01725992fce0ff47c"}}}' \
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

- Clone repo
- Modify the contents
- Move into your `home` directory
- Build a template from local repo
  ```command
  clojure -Sdeps '{:deps {athomasoriginal/templates {:local/root "/code/projects/templates"}}}' \
          -Tnew create \
          :template athomasoriginal/reagent \
          :name mygithubhandle/myreagent
  ```
  > `/code/projects/templates` is specific to my environment.  Update the path
  > to reflect your environment path.

## License

Copyright © 2023 Thomas Mattacchione

Distributed under the Eclipse Public License version 1.0.


[Housekeeping]: #housekeeping
[Getting Started]: #getting-started
[Usage]: #usage
[Global Alias]: #global-alias
[Development]: #development
[License]: #license

[deps-new]: https://github.com/seancorfield/deps-new
[Install Java]: https://www.youtube.com/watch?v=SljDPNwAFOc&t=16s
[Install Clojure CLI Tools]: https://www.youtube.com/watch?v=5_q5pLoz9b0
[new global alias]: https://github.com/athomasoriginal/dotfiles/blob/master/.clojure/deps.edn#L39
