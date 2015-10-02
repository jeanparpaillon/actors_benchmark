ZOG_THREADS ?= 1000
ZOG_MSGS ?= 1000

SBT = $(shell which sbt)
ifeq ($(SBT),)
  $(error "Scala / SBT not found: you can get it from http://www.scala-lang.org/download/")
endif

all: run

run: 
	$(SBT) "run $(ZOG_THREADS) $(ZOG_MSGS)"

clean:
	-rm -rf target/

