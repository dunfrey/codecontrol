package code.control.qr.action;


import code.control.ciphers.CipherClass;
import code.control.jpa.dao.ClientDAO;
import code.control.jpa.entity.Client;
import code.control.qr.gen.QRModel;

public class GeneratorQR {

    public void GeradorQR(String texto) {

        //Persiste o nome do usuario em banco
        ClientDAO usuarioDao = new ClientDAO();
        Client usuario = new Client();
        usuario.setName(texto);
        usuarioDao.persist(usuario);
        
        //PersistirUsuario pu = new PersistirUsuario(texto);
        CipherClass c = new CipherClass();

        //Realiza a criptografia
        String codigo = c.cipher(texto);

        //Gera o QR-Code
        QRModel qrcode = new QRModel();

        qrcode.inicializa();
        qrcode.geraQR(codigo);
    }

}
