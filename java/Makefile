JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Zog.java \
        First.java

all: run

run: $(CLASSES:.java=.class)

clean:
	-rm -f *.class
	-rm *~
