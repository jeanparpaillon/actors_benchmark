JFLAGS = -g
JC = javac
JAVA ?= java

ZOG_THREADS ?= 1000
ZOG_MSGS ?= 1000

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Zog.java \
        First.java

all: run

run: $(CLASSES:.java=.class)
	$(JAVA) Zog $(ZOG_THREADS) $(ZOG_MSGS)

clean:
	-rm -f *.class
	-rm *~
