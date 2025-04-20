run-dist:
	./build/install/app/bin/app

run:
	make run-dist

build: test checkstyle
	./gradlew clean
	./gradlew checkstyleMain
	./gradlew checkstyleTest
	./gradlew test
	./gradlew build

test: # Run tests
	./gradlew test

checkstyle:
	./gradlew checkstyleMain checkstyleTest