import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class MessageEncryption {
    private String messageString;
    String EncryptAES="E:/java/Cryptography/src/EncryptAES.txt";
    String EncryptDES="E:/java/Cryptography/src/EncryptDES.txt";
    public MessageEncryption(String message, Key key,String type) throws Exception{
        byte[] plainText = message.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance(type+"/ECB/PKCS5Padding");
        // encrypt using the key and the plaintext
        

        final long startTime = System.nanoTime();
        //  Initializes the Cipher object
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Calculates the ciphertext with a plaintext string.
        byte[] cipherText = cipher.doFinal(plainText);
        final long duration = System.nanoTime() - startTime;
        String str2="";

        for (byte b:cipherText) {
            str2 +=(char)b;
        }
        this.messageString = str2;
        
        Launcher.consoleBoxTextArea.append("It took " + duration + " milli to Encrypt the message \"" + message +"\" using "+type+ "\n");
        Launcher.consoleBoxTextArea.append("Encrypted message is "+ messageString+"\n");
        Launcher.consoleBoxTextArea.append("Message length is " + message.length()+"\n");
        if("AES".equals(type))
            {try (BufferedWriter writer = new BufferedWriter(new FileWriter(EncryptAES, true))) {

                                    writer.append(message.length()+" "+duration+"\n");
                                }
            }
            else
            {
               try (BufferedWriter writer = new BufferedWriter(new FileWriter(EncryptDES, true))) {

                                    writer.append(message.length()+" "+duration+"\n");
                                } 
            }
    }

    public String getMessageString() {
        return messageString;
    }
}