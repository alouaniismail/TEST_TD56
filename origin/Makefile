DIRSRC?=src
DIRTST=tst

DIRBUILD=build

TU= tec.TestJauge tec.TestPosition
#-d build/ -cp build/ avec package tec; => le TestJauge
#sans execution pour l'executer ca devient build/paquetage tec . TestJauge
#soit tec.TestJauge si on précise à l'execution -cp build/.
TR= tec.TestAutobus tec.TestPassagerStandard tec.TestPassagerIndecis tec.TestPassagerStresse

COMPILER= javac
EXECUTE= java -ea
EXECUTE2=java

SRCCLASS=$(DIRSRC)/Autobus.java $(DIRSRC)/Passager.java\
$(DIRSRC)/Jauge.java $(DIRSRC)/PassagerStandard.java\
$(DIRSRC)/Position.java $(DIRSRC)/Vehicule.java

SRCTEST=$(DIRTST)/FauxPassager.java $(DIRTST)/FauxVehicule.java\
$(DIRTST)/TestAutobus.java $(DIRTST)/TestJauge.java\
$(DIRTST)/TestPassagerStandard.java $(DIRTST)/TestPosition.java\
$(DIRTST)/TestPassagerIndecis.java $(DIRTST)/TestPassagerStresse.java

#toujours compilation+execution a part ou integre (2 manieres differentes).

source: $(DIRSRC) 
	${COMPILER} -d build -cp build/ $</*.java

test: $(DIRTST)
	${COMPILER} -d build -cp build/ $</*.java

source2: $(SRCCLASS)
	javac -d $(DIRBUILD) -cp $(DIRBUILD) $^

test2: source $(SRCTEST)
	javac -d build/ -cp build/ $(SRCTEST)

clean: $(DIRBUILD)
	rm -rf $</*
	rm -rf src/*.java~
	rm -rf tst/*.java~
	rm -f Makefile~ 

utest: source test $(DIRBUILD)
	for tstu in ${TU}; do ${EXECUTE} -cp build/ $$tstu; done
recette: source test $(DIRBUILD)
	for tstr in ${TR}; do ${EXECUTE} -cp build/ $$tstr; done

Simple: source Simple.java
#tec.Simple non car pas de package tec au debut.
	${COMPILER} -d build/ -cp build/ Simple.java


Simple2: source SimpleIndecis.java
#tec.Simple non car pas de package tec au debut.
	${COMPILER} -d build/ -cp build/ SimpleIndecis.java

Simple3: source SimpleStresse.java
#tec.Simple non car pas de package tec au debut.
	${COMPILER} -d build/ -cp build/ SimpleStresse.java

executer: $(DIRBUILD)
#-cp pour class path ou trouver $\@.build.
	${EXECUTE2} -cp $< Simple

executer2: $(DIRBUILD)
#-cp pour class path ou trouver $\@.build.
	${EXECUTE2} -cp $< SimpleIndecis 

executer3: $(DIRBUILD)
#-cp pour class path ou trouver $\@.build.
	${EXECUTE2} -cp $< SimpleStresse

test_all: source 
	javac -d build/ -cp build/ src/SimpleAll.java
test_all_exec: test_all
	java -cp build/ SimpleAll

cleanAll: clean
	rm -f build/*.class && rm -f *.java~
#les targets a retenir:
#utest(jauge position)
#recette: les 3 passagers faussaires + l'autobus avec le passager faussaire.
#Simple et Simple2 et Simple3
#en coordination avec executer executer2 et executer3.
#puis test_all et test_all_exec (3 dans un seul autobus pas de separation)
#comme avant..///
