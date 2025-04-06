import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class Server{


    // main server that handles all the server operations
    public void run() throws IOException{ 
        int port = 8010;
        try (ServerSocket socket = new ServerSocket(port)) {
            socket.setSoTimeout(10000);

            while (true) {
                try {
                    System.out.println("Server is listening on port: " + port);

                    Socket acceptedConnection = socket.accept();

                    System.err.println("Connection accpeted from the Client: " + acceptedConnection.getRemoteSocketAddress());


                    PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                    BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream())); 

                    toClient.println("hello form the server!!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void main(String[] args) {
        Server server = new Server();

        try{
            server.run();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}