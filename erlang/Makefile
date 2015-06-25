ERLC = erlc

all: run

run: zog.beam

clean:
	-rm *.beam *~

%.beam: %.erl
	$(ERLC) $<
