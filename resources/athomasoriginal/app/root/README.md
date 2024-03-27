# {{raw-name}}

- [Quickstart]
  - [Housekeeping]
  - [Get the Code]
  - [Run the App]
- [App Commands]
- [App Features]
- [Dev Tips]
  - [Run Editor REPL Vim]


## Quickstart

> The following shows how to run the app in development

### Housekeeping

...

### Get the Code

- Clone project
  ```
  git clone https://github.com/{{scm/user}}/{{scm/repo}}
  ```

### Run the App


- Move into `{{main/ns}}` dir
  ```command
  cd path/to/{{main/ns}}
  ```
- Run the app
  ```clojure
  bb dev
  ```
  > This will load a Clojure REPL. From here, you can run arbitrary clojure
  > code or, as you will see in the next step, move into the dev namespace.
- Move into the dev namespace
  ```command
  (into-dev)
  ```
- Run the app
  ```command
  (start)
  ```

Visit localhost:8090


## App Commands

The following is a complete list of commands to run:

- Run dev with `vim-iced`
  ```command
  bb dev:vi
  ```
  - Be sure to have [vim-iced] installed.
- Find outdated packages
  ```command
  bb outdated
  ```
- Run the project directly, via `:exec-fn`:
  ```command
  clojure -X:run-x
  ```
  > Hello, Clojure!
- Run the project, overriding the name to be greeted:
  ```command
  clojure -X:run-x :name '"Someone"'
  ```
  > Hello, Someone!
- Run the project directly, via :main-opts (-m {{top/ns}}.{{main/ns}}):
  ```command
  clojure -M:run-m
  ```
  > Hello, World!
- Run the project, overriding the name to be greeted:
  ```command
  clojure -M:run-m Via-Main
  ```
  > Hello, Via-Main!
- Run the project's tests (they'll fail until you edit them):
  ```command
  clojure -T:build test
  ```
- Run the project's CI pipeline and build an uberjar (this will fail until you edit the tests to pass):
  ```command
  clojure -T:build ci
  ```

This will produce an updated `pom.xml` file with synchronized dependencies inside the `META-INF`
directory inside `target/classes` and the uberjar in `target`. You can update the version (and SCM tag)
information in generated `pom.xml` by updating `build.clj`.

If you don't want the `pom.xml` file in your project, you can remove it. The `ci` task will
still generate a minimal `pom.xml` as part of the `uber` task, unless you remove `version`
from `build.clj`.

- Run that uberjar:
  ```command
  java -jar target/{{group/id}}/{{artifact/id}}-{{version}}.jar
  ```

## Dev Tips

### Run Editor REPL Vim

> The following assumes you've setup `vim-iced` correctly.

- Move into `{{main/ns}}` dir
  ```command
  cd path/to/{{main/ns}}
  ```
- Run the app
  ```clojure
  bb dev:vi
  ```
  > You should see a line telling you the REPL port, for example:
  > `nREPL server started on port 49750 on host localhost - nrepl://localhost:49750`
- Open vim
- Navigate to `dev.clj`
  > Make sure you're in `clj` file.
- Connect to REPL
  ```bash
  :IcedConnect 49750
  ```

[Quickstart]: #quickstart
[Housekeeping]: #housekeeping
[Get the Code]: #get-the-code
[Run the App]: #run-the-app
[App Commands]: #app-commands
[Dev Tips]: #dev-tips
[Run Editor REPL Vim]: #run-editor-repl-vim
[Todo]: #todo

[vim-iced]: https://github.com/liquidz/vim-iced
