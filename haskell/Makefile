SHELL = /bin/bash

ZOG_THREADS ?= 1000
ZOG_MSGS ?= 1000

all: run

run: build
	time ./Main +RTS -N$(nproc) -RTS '$(ZOG_THREADS)' '$(ZOG_MSGS)'

build:
	ghc -O -threaded -rtsopts Main.hs

clean:
	rm Main Main.hi Main.o
