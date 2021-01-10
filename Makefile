SOURCES = $(shell find src -type f -name "*.java")
CLASSES = $(patsubst src/%.java,out/%.class,$(SOURCES))
MAINCLASS = Main

all: $(CLASSES)

run:
	java -cp    ${MAINCLASS}

pack:
	zip taskA.zip -r Makefile src

clean:
	rm -rf out

out:
	mkdir -p out

