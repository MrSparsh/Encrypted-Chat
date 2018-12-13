

import java.io.Serializable;

public class AESkeyAndSignature implements Serializable {
    private byte[] cipherKeyAES;
    private byte[] signature;

    public AESkeyAndSignature(byte[] cipherKeyAES, byte[] signature) {
        this.cipherKeyAES = cipherKeyAES;
        this.signature = signature;
    }

    public byte[] getCipherKeyAES() {
        return cipherKeyAES;
    }

    public byte[] getSignature() {
        return signature;
    }
}
