

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class  DecryptRSAwithSignature {
    private SecretKey key;

    public DecryptRSAwithSignature(byte[] cipherText, PrivateKey privateKey, PublicKey publicKey, byte[] signature,String type) {
        // verify the signature with the public key
        System.out.println("\nStart signature verification");
        Signature sig2 = null;
        try {
            sig2 = Signature.getInstance("MD5WithRSA");
            sig2.initVerify(publicKey);                        // Verifies the signature.
            sig2.update(cipherText);
            if (sig2.verify(signature)) {
                System.out.println("Signature verified");

                // Now decrypt the message
                try {
                    // get an RSA cipher object
                    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

                    // decrypt the ciphertext using the private key
                    System.out.println("\nStart decryption");
                    cipher.init(Cipher.DECRYPT_MODE, privateKey);
                    byte[] newPlainText = cipher.doFinal(cipherText);
                    System.out.println("Finish decryption: ");

                    key = new SecretKeySpec(newPlainText, type);
                } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            } else System.out.println("Signature failed");
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
    }

    public SecretKey getKey() {
        return key;
    }
}
