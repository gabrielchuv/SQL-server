package DBServer;

import DBExceptions.InvalidActionException;
import DBExceptions.InvalidTokenException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DBServer {

    DBCmd cmd;
        DBDatabase db;
    String currentDatabase;
    ArrayList<ArrayList<String>> queryResult;

    public DBServer(int portNumber) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server Listening");
            while(true) processNextConnection(serverSocket);
        } catch(IOException ioe) {
            System.err.println(ioe);
        }
    }

    private void processNextConnection(ServerSocket serverSocket)
    {
        try {
            Socket socket = serverSocket.accept();
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Connection Established");
            while(true) processNextCommand(socketReader, socketWriter);
        } catch(IOException ioe) {
            System.err.println(ioe);
        } catch(NullPointerException npe) {
            System.out.println("Connection Lost");
        }
    }

    private void processNextCommand(BufferedReader socketReader, BufferedWriter socketWriter) throws IOException, NullPointerException
    {
        String incomingCommand = socketReader.readLine();
        System.out.println("Received message: " + incomingCommand);

        try {
            Parser parser = new Parser(incomingCommand);
            cmd = parser.command();
            this.queryResult = cmd.query(this);
            socketWriter.write("[OK]");
            if(this.queryResult != null) {
                printQueryResult(socketWriter);
            }
        } catch(InvalidTokenException|InvalidActionException|RuntimeException e) {
            socketWriter.write("[ERROR]: " + e.toString());
        }
        socketWriter.write("\n" + ((char)4) + "\n");
        socketWriter.flush();
    }

    public void setDatabaseName(String DBName) {
        this.currentDatabase = DBName;
    }

    public String getDatabaseName() {
        return this.currentDatabase;
    }

    private void printQueryResult(BufferedWriter socketWriter) throws IOException {
        socketWriter.write("\n-------------------------------------------------------------\n");
        for(int i = 0; i < this.queryResult.size(); i++) {
            for(int j = 0; j < this.queryResult.get(i).size(); j++) {
                socketWriter.write(String.format("%15s|", queryResult.get(i).get(j)));
            }
            socketWriter.write("\n");
        }
        socketWriter.write("------------------------------------------------------------");
    }


    public static void main(String args[]) {
        DBServer server = new DBServer(8888);
    }

}