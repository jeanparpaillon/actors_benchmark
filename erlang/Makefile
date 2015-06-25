ERL ?= erl
ERLC ?= erlc
ZOG_THREADS ?= 1000
ZOG_MSGS ?= 1000

all: run

run: zog.beam
	$(ERL) -noshell -eval 'zog:start('$(ZOG_THREADS)','$(ZOG_MSGS)').'

clean:
	-rm -f *.beam *~

%.beam: %.erl
	$(ERLC) $<
