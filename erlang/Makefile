ERL ?= erl
ERLC ?= erlc
ZOG_THREADS ?= 1000
ZOG_MSGS ?= 1000

ERL_OPTS = $(shell echo +P $$(( $(ZOG_THREADS) + 200 )))

all: run

run: zog.beam
	$(ERL) $(ERL_OPTS) -noshell -eval 'zog:start('$(ZOG_THREADS)','$(ZOG_MSGS)').'

clean:
	-rm -f *.beam *~

%.beam: %.erl
	$(ERLC) $<
