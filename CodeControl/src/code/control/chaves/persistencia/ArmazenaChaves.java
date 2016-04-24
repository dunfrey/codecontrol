package code.control.chaves.persistencia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class ArmazenaChaves {

    private final String algGenChave = "RSA";
     
    private final String ARQUIVO_CHAVE_PRIVADA = "files/private.key";
    private final String ARQUIVO_CHAVE_PUBLICA = "files/public.key";
    
    public ArmazenaChaves(){
        System.out.println("Armazenando chaves.");
    }

    public void geraChaves() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator
                    .getInstance(algGenChave);
            keyGen.initialize(2048);
            final KeyPair chave = keyGen.generateKeyPair();

            File privateKeyFile = new File(ARQUIVO_CHAVE_PRIVADA);
            File publicKeyFile = new File(ARQUIVO_CHAVE_PUBLICA);

            // Criando arquivos e guardando as chaves.
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
