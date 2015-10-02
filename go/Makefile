ZOG_THREADS ?= 1000
ZOG_MSGS ?= 1000

GO = $(shell which go)
ifeq ($(GO),)
	$(error "go command not found")
endif

BIN = zog

all: run

run: $(BIN)
	./zog $(ZOG_THREADS) $(ZOG_MSGS)

$(BIN): $(BIN).go
	$(GO) build $<

clean:
	-rm -f zog
	-rm *~

.PHONY: all run clean
