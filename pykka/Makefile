PYTHON ?= python

ZOG_THREADS ?= 1000
ZOG_MSGS ?= 1000

ifeq "$(shell python -c 'import pykka' 2> /dev/null && echo 0 || echo 1)" "1"
$(error "Missing python module: pykka")
endif

all: run

run:
	$(PYTHON) zog.py $(ZOG_THREADS) $(ZOG_MSGS)

clean:
	-rm *.pyc
	-rm *~
