package code.control.observer.arduino;

import java.io.DataOutputStream;
import java.net.Socket;

public class UnlockDoor {

    public void executa() throws Exception {
        Socket clientSocket;
        //IP do Arduino e Porta de comunicação adotada no sistema.
        clientSocket = new Socket("192.168.0.2", 37776);
        try (DataOutputStream outToServer = 
                new DataOutputStream(clientSocket.getOutputStream())
            )   {
                    outToServer.writeBytes("P");   
                    clientSocket.close();
                }
    }
}