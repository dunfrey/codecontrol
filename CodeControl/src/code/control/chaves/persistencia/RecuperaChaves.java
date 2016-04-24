package code.control.chaves.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RecuperaChaves {

    private final String ARQUIVO_CHAVE_PRIVADA = "files/private.key";
    private final String ARQUIVO_CHAVE_PUBLICA = "files/public.key";

    public PublicKey RecuperaPublica() {
        if (verificaChaves()) {
            try {
                ObjectInputStream inputStream = null;

                inputStream = new ObjectInputStream(new FileInputStream(
                        ARQUIVO_CHAVE_PUBLICA));
                final PublicKey publicKey = (PublicKey) inputStream.readObject();
                return publicKey;
            } catch (IOException | ClassNotFoundException e) {
            }
        }
        return null;
    }

    public PrivateKey RecuperaPrivada() {
        if (verificaChaves()) {
            try {
                ObjectInputStream inputStream = null;

                inputStream = new ObjectInputStream(new FileInputStream(
                        ARQUIVO_CHAVE_PRIVADA));
                final PrivateKey privateKey = (PrivateKey) inputStream.readObject();              
                return privateKey;
            } catch (IOException | ClassNotFoundException e) {
            }
        }        
        return null;
    }

    public boolean verificaChaves() {
        File privateKey = new File(ARQUIVO_CHAVE_PRIVADA);
        File publicKey = new File(ARQUIVO_CHAVE_PUBLICA);

        return privateKey.exists() && publicKey.exists();
    }

}
