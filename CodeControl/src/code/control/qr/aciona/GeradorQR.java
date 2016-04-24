package code.control.qr.aciona;


import code.control.criptografia.Criptografia;
import code.control.jpa.dao.ClienteDAO;
import code.control.jpa.entidade.Cliente;
import code.control.qr.gera.QRModulo;

public class GeradorQR {

    public void GeradorQR(String texto) {

        //Persiste o nome do usuario em banco
        ClienteDAO usuarioDao = new ClienteDAO();
        Cliente usuario = new Cliente();
        usuario.setNome(texto);
        usuarioDao.persistir(usuario);
        
        //PersistirUsuario pu = new PersistirUsuario(texto);
        Criptografia c = new Criptografia();

        //Realiza a criptografia
        String codigo = c.cifra(texto);

        //Gera o QR-Code
        QRModulo qrcode = new QRModulo();

        qrcode.inicializa();
        qrcode.geraQR(codigo);
    }

}
