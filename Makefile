#A3 Makefile

SRCDIR=cloudscapes
JC=/usr/bin/javac
JFLAGS=  -classpath .
.SUFFIXES: .java .class
JVM=/usr/bin/java

.java.class:
	$(JC) $(JFLAGS) $*.java

SOURCES = $(SRCDIR)/simulator.java $(SRCDIR)/CloudData.java $(SRCDIR)/vector.java

MAIN = $(SRCDIR)/simulator

classes: $(SOURCES:.java=.class)

default: classes

run: $(MAIN).class
	$(JVM) $(MAIN) $(arg1) $(arg2)

clean:
	rm *.class
	
