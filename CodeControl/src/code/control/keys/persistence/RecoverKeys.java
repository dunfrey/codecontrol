package code.control.keys.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RecoverKeys {

    private final String Private_Key = "files/private.key";
    private final String Public_Key = "files/public.key";

    public PublicKey RecoverPublicKey() {
        if (VerifyKey()) {
            try {
                ObjectInputStream inputStream = null;

                inputStream = new ObjectInputStream(new FileInputStream(
                        Public_Key));
                final PublicKey publicKey = (PublicKey) inputStream.readObject();
                return publicKey;
            } catch (IOException | ClassNotFoundException e) {
            }
        }
        return null;
    }

    public PrivateKey RecoverPrivateKey() {
        if (VerifyKey()) {
            try {
                ObjectInputStream inputStream = null;

                inputStream = new ObjectInputStream(new FileInputStream(
                        Private_Key));
                final PrivateKey privateKey = (PrivateKey) inputStream.readObject();              
                return privateKey;
            } catch (IOException | ClassNotFoundException e) {
            }
        }        
        return null;
    }

    public boolean VerifyKey() {
        File privateKey = new File(Private_Key);
        File publicKey = new File(Public_Key);

        return privateKey.exists() && publicKey.exists();
    }

}
