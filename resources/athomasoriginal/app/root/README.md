# {{raw-name}}

* [Quickstart]
  * [Housekeeping]
  * [Get the App]
  * [Run the App]
  * [Learn the App]
* [Commands]
  * [test]
  * [outdated]
  * [build]
  * [toolkit]
  * [docker]
* [Guides]
  * [Conventions]
  * [Style]
  * [Dev Lifecycle]
  * [REPL Vim]


## Quickstart

> The following shows how to run the app in development

### Housekeeping

...

### Get the App

* Clone project
  ```
  git clone https://github.com/{{scm/user}}/{{scm/repo}}
  ```

### Run the App


* Move into `{{main/ns}}` dir
  ```command
  cd path/to/{{main/ns}}
  ```
* Run the app
  ```clojure
  bb dev
  ```
  > This will load a Clojure REPL. From here, you can run arbitrary clojure
  > code or, as you will see in the next step, move into the dev namespace.
* Move into the dev namespace
  ```command
  (into-dev)
  ```
* Run the app
  ```command
  (start)
  ```

Visit localhost:8090


### Learn the App

...

## Commands

The following is a complete list of commands to run:


### test

* Run all tests
  ```command
  bb test
  ```
  > They'll fail until you edit them


### outdated

* Find outdated deps
  ```command
  bb outdated
  ```

### build

* Build uberjar
  ```command
  clojure -T:build uberjar :version '"2024.12.22.8"'
  ```
* Sanity check uberjar
  ```command
  java -jar target/{{group/id}}/{{artifact/id}}-{{version}}.jar
  ```
  > If you need to pass args see the [clojure guide on quoting]
* Visit site
  ```text
  localhost:3000
  ```

## Guides

### Conventions

...

### Style

...

### Dev Lifecycle

...

### REPL Vim

> The following assumes you've setup `vim-iced` correctly.

* Move into `{{main/ns}}` dir
  ```command
  cd path/to/{{main/ns}}
  ```
* Run the app
  ```clojure
  bb dev:vi
  ```
  > You should see a line telling you the REPL port, for example:
  > `nREPL server started on port 49750 on host localhost - nrepl://localhost:49750`
* Open vim
* Navigate to `dev.clj`
  > Make sure you're in `clj` file.
* Connect to REPL
  ```bash
  :IcedConnect 49750
  ```

[Quickstart]: #quickstart
[Housekeeping]: #housekeeping
[Get the App]: #get-the-app
[Run the App]: #run-the-app
[Learn the App]: #learn-the-app
[Commands]: #commands
[test]: #test
[outdated]: #outdated
[build]: #build
[Guides]: #guides
[Conventions]: #conventions
[Style]: #style
[Dev Lifecycle]: #dev-lifecycle
[REPL Vim]: #repl-vim

[vim-iced]: https://github.com/liquidz/vim-iced
