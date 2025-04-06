import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    // This method returns a Consumer that handles a connected client (Socket)
    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {

            try {
                // Create a writer to send a message to the client
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());

                // Send a greeting message to the client
                toClient.println("Hello from the Server!");

                // Close the writer and client socket
                toClient.close();
                clientSocket.close();

            } catch (IOException ex) {
                // Handle any IO exceptions
                ex.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {

        int port = 8010;

        // Create an instance of the Server class
        Server server = new Server();

        try {
            try (// Create a ServerSocket listening on the given port
            ServerSocket serverSocket = new ServerSocket(port)) {
                // Set a timeout (10 seconds) for waiting on client connections
                serverSocket.setSoTimeout(10000);

                System.out.println("Server is listening at port: " + port);

                // Continuously accept incoming client connections
                while (true) {
                    // Accept a new client connection
                    Socket accpetedSocket = serverSocket.accept();

                    // Create a new thread to handle this client using the Consumer
                    Thread thread = new Thread(() -> server.getConsumer().accept(accpetedSocket));


                    System.err.println("Connection Accepted From the Client: " + accpetedSocket.getRemoteSocketAddress());

                    // Start the thread to handle client independently
                    thread.start();
                }
            }

        } catch (IOException ex) {
            // Handle any IO exceptions (e.g., timeout, connection issues)
            ex.printStackTrace();
        }

    }

}
