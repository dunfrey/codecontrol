package code.control.keys.persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class Keys {

    private final String algoritmKey = "RSA";
     
    private final String Private_key = "files/private.key";
    private final String Public_Key = "files/public.key";

    public void GenerateKeys() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator
                    .getInstance(algoritmKey);
            keyGen.initialize(2048);
            final KeyPair chave = keyGen.generateKeyPair();

            File privateKeyFile = new File(Private_key);
            File publicKeyFile = new File(Public_Key);

            if (privateKeyFile.getParentFile() != null) {
                privateKeyFile.getParentFile().mkdirs();
            }
            privateKeyFile.createNewFile();

            if (publicKeyFile.getParentFile() != null) {
                publicKeyFile.getParentFile().mkdirs();
            }
            publicKeyFile.createNewFile();

            try ( // Salvando Publickey em arquivo
                    ObjectOutputStream publicKey_oos = new ObjectOutputStream(
                            new FileOutputStream(publicKeyFile))) {
                publicKey_oos.writeObject(chave.getPublic());
            }

            try ( // Salvando Privatekey em arquivo
                    ObjectOutputStream privateKey_oos = new ObjectOutputStream(
                            new FileOutputStream(privateKeyFile))) {
                privateKey_oos.writeObject(chave.getPrivate());
            }
        } catch (NoSuchAlgorithmException | IOException e) {
        }
    }

}
