run-dist:
	./build/install/app/bin/app

run:
	make run-dist

build: test checkstyle
	./gradlew clean
	./gradlew installDist

test: # Run tests
	./gradlew test

checkstyle:
	./gradlew checkstyleMain checkstyleTest