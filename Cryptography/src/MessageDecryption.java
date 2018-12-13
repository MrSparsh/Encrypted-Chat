

import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class MessageDecryption {
    private String message;
    String DecryptAES="E:/java/Cryptography/src/DecryptAES.txt";
    String DecryptDES="E:/java/Cryptography/src/DecryptDES.txt";
    public MessageDecryption(String message, Key key,String type) {
        try {
            byte[] cipherText = new byte[message.length()];
            char[] carr = message.toCharArray();
            for (int i = 0; i < message.length(); i++) {
                cipherText[i] = (byte) carr[i];
            }

            // Creates the Cipher object (specifying the algorithm, mode, and padding)
            Cipher cipher = Cipher.getInstance(type+"/ECB/PKCS5Padding");

            // decrypt the ciphertext using the same key
            System.out.println("\nStart decryption");
            final long startTime = System.nanoTime();
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] newPlainText = cipher.doFinal(cipherText);
            final long duration = System.nanoTime() - startTime;
            System.out.println("Finish decryption: ");

            this.message = new String(newPlainText, StandardCharsets.UTF_8);
            Launcher.consoleBoxTextArea.append("It took " + duration + " millisecond to Decrypt the message " + message +" using "+type+ "\n");
            Launcher.consoleBoxTextArea.append("Message length is " + message.length()+"\n");
            if("AES".equals(type))
            {try (BufferedWriter writer = new BufferedWriter(new FileWriter(DecryptAES, true))) {

                                    writer.append(this.message.length()+" "+duration+"\n");

                                }
            }
            else
            {
               try (BufferedWriter writer = new BufferedWriter(new FileWriter(DecryptDES, true))) {

                                    writer.append(this.message.length()+" "+duration+"\n");

                                } 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }
}
