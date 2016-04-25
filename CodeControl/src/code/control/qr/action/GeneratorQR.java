package code.control.qr.action;


import code.control.ciphers.CipherClass;
import code.control.jpa.dao.ClienteDAO;
import code.control.jpa.entidade.Cliente;
import code.control.qr.gera.QRModulo;

public class GeneratorQR {

    public void GeradorQR(String texto) {

        //Persiste o nome do usuario em banco
        ClienteDAO usuarioDao = new ClienteDAO();
        Cliente usuario = new Cliente();
        usuario.setNome(texto);
        usuarioDao.persistir(usuario);
        
        //PersistirUsuario pu = new PersistirUsuario(texto);
        CipherClass c = new CipherClass();

        //Realiza a criptografia
        String codigo = c.cipher(texto);

        //Gera o QR-Code
        QRModulo qrcode = new QRModulo();

        qrcode.inicializa();
        qrcode.geraQR(codigo);
    }

}
