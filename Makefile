#A3 Makefile

SRCDIR=cloudscapes
JC=/usr/bin/javac
JFLAGS=  -classpath .
.SUFFIXES: .java .class
JVM=/usr/bin/java

.java.class:
	$(JC) $(JFLAGS) $*.java

SOURCES = $(SRCDIR)/simulator.java $(SRCDIR)/CloudData.java $(SRCDIR)/vector.java $(SRCDIR)/SimulatorPar.java $(SRCDIR)/CloudDataPar.java $(SRCDIR)/SumThread.java

MAIN = $(SRCDIR)/simulator
MAINPAR = $(SRCDIR)/SimulatorPar

classes: $(SOURCES:.java=.class)

default: classes

run: $(MAIN).class
	$(JVM) $(MAIN) $(arg1) $(arg2)

runpar: $(MAINPAR).class
	$(JVM) $(MAIN) $(arg1) $(arg2)

clean:
	rm *.class
	
