# Java Web Server (SingleThreaded | MultiThreaded | ThreadPool)

This project is a lightweight custom HTTP Web Server built using Java. It demonstrates three different threading models to handle multiple client requests:

-  **Single Threaded Server**
-  **Multi Threaded Server**
-  **Thread Pool Based Server**

A perfect project to understand socket programming and concurrency in Java!

---

##  Threading Models Explained


###  SingleThread
- The server handles one client at a time.
- Simple to understand and good for learning basic server-client interaction.

###  MultiThread
- Each client connection is handled in a separate thread.
- Allows multiple clients to be served simultaneously.

###  ThreadPool
- Uses a fixed-size pool of threads.
- More efficient resource usage and scalable under high load.

## How to Run

> Make sure Java is installed and added to your PATH.

### Run SingleThread or MultiThread:

```bash
cd SingleThread   # or MultiThread
javac Server.java
javac Client.java
java Server       # Run in one terminal
java Client       # Run in another terminal
```
### Run ThreadPool:

```bash
cd ThreadPool
javac Server.java
java Server
```
---
## What I Learned 
- Java Socket Programming (ServerSocket, Socket)
- Handling concurrency using Threads and Thread Pools
- Building a basic yet functional HTTP server



