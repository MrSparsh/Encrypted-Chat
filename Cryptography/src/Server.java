
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Server {
    
    private static Socket readSocket,writeSocket;
    private static PrivateKey serverPrivateKey;
    private static PublicKey serverPublicKey,clientPublicKey;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;
    private static Key AESKey,DESKey;
    
    static void generateRSAKeyPair() throws NoSuchAlgorithmException{
         System.out.println("\nStart generating RSA key");
            KeyPairGenerator keyGenRSA = KeyPairGenerator.getInstance("RSA");
            keyGenRSA.initialize(1024);
            KeyPair keyRSA = keyGenRSA.generateKeyPair();
             serverPrivateKey = keyRSA.getPrivate();
            serverPublicKey = keyRSA.getPublic();
            System.out.println("Finish generating RSA key");

    }
    
    static void generateAndShareAESKey() throws Exception{
        System.out.println("\nStart generating AES key");
            KeyGenerator keyGenAES = KeyGenerator.getInstance("AES");
            keyGenAES.init(128);    //In AES cipher block size is 128 bits
            SecretKey key = keyGenAES.generateKey();
            AESKey = key;
            System.out.println("Finish generating AES key" + key);
            
            EncryptRSAwithSignature encryptRSAwithSignature = new EncryptRSAwithSignature(key.getEncoded(), clientPublicKey, serverPrivateKey);
            AESkeyAndSignature aeSkeyAndSignature = new AESkeyAndSignature(encryptRSAwithSignature.getCipherKey(), encryptRSAwithSignature.getSignature());
            // Now send the AES common key
            oos.writeObject(aeSkeyAndSignature);
            oos.flush();
            System.out.println("Common key (AES) has been sent to client");
    }
    static void generateAndShareDESKey() throws Exception{
        System.out.println("\nStart generating DES key");
            KeyGenerator keyGenDES = KeyGenerator.getInstance("DES");
            keyGenDES.init(56);    //In DES cipher block size is 56 bits
            SecretKey key = keyGenDES.generateKey();
            DESKey = key;
            System.out.println("Finish generating DES key" + key);
            
            EncryptRSAwithSignature encryptRSAwithSignature = new EncryptRSAwithSignature(key.getEncoded(), clientPublicKey, serverPrivateKey);
            DESKeyAndSignature deSkeyAndSignature = new DESKeyAndSignature(encryptRSAwithSignature.getCipherKey(), encryptRSAwithSignature.getSignature());
            // Now send the DES common key
            oos.writeObject(deSkeyAndSignature);
            oos.flush();
            System.out.println("Common key (DES) has been sent to client");
    }
    public static void main(String[] args) throws Exception{
        ServerSocket fileSendServerSocket = new ServerSocket(5555);
        Socket fileSendSocket = fileSendServerSocket.accept();
        
        
        ServerSocket ss1= new ServerSocket(3110);
        readSocket = ss1.accept();
        
        writeSocket = new Socket("localhost",3111);
        
        ois = new ObjectInputStream(readSocket.getInputStream());
        oos = new ObjectOutputStream(writeSocket.getOutputStream());

       generateRSAKeyPair();

        try {
            System.out.println("Server running");
            clientPublicKey = (PublicKey) ois.readObject();
            System.out.println(clientPublicKey);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // First Server send the own public key to the client
        oos.writeObject(serverPublicKey);
        oos.flush();
        System.out.println("Public key (RSA) of server has been sent to client");
        generateAndShareAESKey();
        generateAndShareDESKey();
        new Launcher(readSocket,writeSocket,AESKey,DESKey,ois,oos,fileSendSocket).setVisible(true);
    }
}
