import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server{
    private final ExecutorService threadPool;


    public Server(int poolSize){
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void handleClient(Socket clientSocket){
        try(PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream())){
            toSocket.println("hello from the server " + clientSocket.getInetAddress());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        int port = 8010;
        int poolSize = 10; // adjust Pool size accordingly 

        Server server = new Server(poolSize);

        try {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                serverSocket.setSoTimeout(10000);
                System.out.println("Server is Listening on the port: " + port);

                while (true) {
                    Socket clienSocket = serverSocket.accept();
                   
                    System.err.println("Connection accepted at Client: " + clienSocket.getRemoteSocketAddress());
                    
                    // use the thread pool to handle the client
                    server.threadPool.execute(() -> server.handleClient(clienSocket));
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally{
            server.threadPool.shutdown();
        }
    }
}