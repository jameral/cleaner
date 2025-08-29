# Cleaner

Author: Javier Mercé Altaber

### How to run it

Since it wasn't stated how the end user would interact with the system, the main way of testing the
application is with unit tests. There's also the possibility of starting the application with the 
following command:

```bash
./mvnw clean spring-boot:start`
```

or
```bash
./mvnw clean package
java -jar target/cleaner-0.0.1.jar
```

Once the application is running you can enter the names of an input file containing the settings
of the problem, and an output file for the program to write the results in.

### Domain

This is an application to operate a tiny robot in charge of cleaning empty rectangular rooms.

I've created my domain around the concept of floor. In this program, floor is the rectangular
surface
that the robots (cleaners) have to keep clean. Those cleaners are an attribute of the floor, and
they have a position and a direction.

Almost all the business logic revolves around these concepts.

### Architecture

I've decided to use hexagonal architecture mainly because it was suggested in the instructions of
the technical challenge.
Because of this, I've added a couple of adapters (one to read from the console and the other to
manage file i/o). I'm quite used to this kind of architecture, since I've been using it for almost 3
years in my current position.

### Framework

I've used Spring framework in spite of the apparent simplicity of the task
because of the use of hexagonal architecture in the project: I simply needed a way to do dependency
injection because of the dependency inversion needed in any kind of clean architecture, to ensure
the right
"direction" of every dependency. Since I'm used to Spring it seemed like the natural choice.

### Language

Finally, I've chosen Kotlin as the programming language for the application, in spite of being a language I've never used before. 
I thought it was a good chance to try it, and to check if it's that easy to learn it coming from Java 