package code.control.criptografia;

import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import code.control.chaves.persistencia.RecuperaChaves;

public class Criptografia {

    private RecuperaChaves rc = new RecuperaChaves();
    private PublicKey PU;
    private PrivateKey PK;
    
    private final String RSA = "RSA";
    
    public Criptografia(){
        PU = rc.RecuperaPublica();
        PK = rc.RecuperaPrivada();
    }

    private byte[] cifra(String text, PublicKey chavePublica) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, chavePublica);
        return cipher.doFinal(text.getBytes());
    }    

    public final String cifra(String text) {
        try {
            return byte2hex(Criptografia.this.cifra(text, PU));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final String decifra (String data) {
        try {
            return new String(decifra(hex2byte(data.getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] decifra (byte[] src) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, PK);
        return cipher.doFinal(src);
    }

    public String byte2hex(byte[] dado_byte) {
        String dado_hexa = "";
        String stmp = "";
        for (int n = 0; n < dado_byte.length; n++) {
            stmp = Integer.toHexString(dado_byte[n] & 0xFF);
            if (stmp.length() == 1) {
                dado_hexa += ("0" + stmp);
            } else {
                dado_hexa += stmp;
            }
        }
        return dado_hexa.toUpperCase();
    }

    public byte[] hex2byte(byte[] dado_hexa) {
        if ((dado_hexa.length % 2) != 0) {
            throw new IllegalArgumentException("Olá.");
        }

        byte[] dado_byte = new byte[dado_hexa.length / 2];

        for (int n = 0; n < dado_hexa.length; n += 2) {
            String item = new String(dado_hexa, n, 2);
            dado_byte[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return dado_byte;
    }
}
