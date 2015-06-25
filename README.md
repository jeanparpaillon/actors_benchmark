# Actors benchmark

Compare different languages/framework on actors model implementation.

The algortihtm is described here: https://www.sics.se/~joe/ericsson/du98024.html

Feel free to PR your preferred framework/language implementation.

## Usage

Each dir should contain a Makefile with default rule:
- compiling the code
- run the benchmark handling the following env var:
  * ZOG_THREADS = number of threads in the ring (default to 1000)
  * ZOG_MSGS = number of exchanged messages (default to 1000)
  