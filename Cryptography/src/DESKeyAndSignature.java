

import java.io.Serializable;

public class DESKeyAndSignature implements Serializable {
    private byte[] cipherKeyDES;
    private byte[] signature;

    public DESKeyAndSignature(byte[] cipherKeyDES, byte[] signature) {
        this.cipherKeyDES = cipherKeyDES;
        this.signature = signature;
    }

    public byte[] getCipherKeyDES() {
        return cipherKeyDES;
    }

    public byte[] getSignature() {
        return signature;
    }
}