# bcs

The part of breakpoint that compiles code.

This is very new and completely unfinished. It doesn't even remotely do what it is
meant to do at this point, but is a proof of concept of our design.

## Usage

Well, `lein run <lang> <code>` lets you run the application and execute a particular language.
This is not how it is meant to be used, of course, but is easy enough to test things. 

Note that bcs requires the `unbuffer` program that comes with `expect`. If you don't have it,
check your distro for `expect-dev` or whatever has `unbuffer` and install it. We require it so
we can fake being a tty so that we can get output unbuffered.

## License

Copyright © 2012 Anthony Grimes

Distributed under the Eclipse Public License, the same as Clojure.
