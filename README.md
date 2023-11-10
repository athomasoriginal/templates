# Templates

A collection of common templates I use to start different kinds of projects

- [Housekeeping]
- [Getting Started]
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
clojure -Sdeps '{:deps {net.clojars.athomasoriginal/templates {:local/root "."}}}' -Tnew create :template athomasoriginal/templates :name myusername/mycoollib
```

Assuming you have installed `deps-new` as your `new` "tool" via:

```command
clojure -Ttools install-latest :lib io.github.seancorfield/deps-new :as new
```

> Note: once the template has been published (to a public git repo), the
> invocation will be the same, except the `:local/root` dependency will be
> replaced by a git or Maven-like coordinate.

Run this template project's tests (by default, this just validates your
template's `template.edn` file -- that it is valid EDN and it satisfies the
`deps-new` Spec for template files):

```command
clojure -T:build test
```

## Development

- Clone repo
- Modify the contents
- Move into your `home` directory
- Build a template from local repo
  ```command
  clojure -Sdeps '{:deps {athomasoriginal/templates {:local/root "/code/projects/templates"}}}' -Tnew create :template athomasoriginal/reagent :name mygithubhandle/myreagent
  ```
  > `/code/projects/templates` is specific to my environment.  Update the path
  > to reflect your environment path.

## License

Copyright © 2023 Thomas Mattacchione

Distributed under the Eclipse Public License version 1.0.


[Housekeeping]: #housekeeping
[Getting Started] #getting-started
[Development]: #development
[License] #license

[deps-new]: https://github.com/seancorfield/deps-new
[Install Java]: https://www.youtube.com/watch?v=SljDPNwAFOc&t=16s
[Install Clojure CLI Tools]: https://www.youtube.com/watch?v=5_q5pLoz9b0
