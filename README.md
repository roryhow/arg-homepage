# homepage

A [re-frame](https://github.com/Day8/re-frame) application generally used for small tinkering, hosted at [www.roryhow.com](http://www.roryhow.com)

There are no guarantees for this code to work; I will most likely break this repo (and the corresponding hosted site) from time to time.

## Development Mode

### Start Cider from Emacs:

Navigate to a clojurescript file and start a figwheel REPL with `cider-jack-in-clojurescript` or (`C-c M-J`)

You can also use `cider-jack-in-clj&cljs` to fire up both Clojure and Clojurescript REPLs

### Compile css:

Compile css file once.

```
lein garden once
```

Automatically recompile css file on change.

```
lein garden auto
```

CSS also gets automatically built when running `lein build` and recompiled on change with `lein dev`.

### Run application:

```
lein dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build

```
lein clean
lein with-profile prod uberjar
```

That should compile the clojurescript code first, and then create the standalone jar.

When you run the jar you can set the port the ring server will use by setting the environment variable PORT.
If it's not set, it will run on port 3000 by default.

To deploy to heroku, first create your app:

```
heroku create
```

Then deploy the application:

```
git push heroku master
```

To compile clojurescript to javascript:

```
lein build
```
