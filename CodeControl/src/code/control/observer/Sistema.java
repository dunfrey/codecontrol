package code.control.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import code.control.criptografia.Criptografia;

public class Sistema implements Observado {

    private List<Observador> observadores;
    private String textoCriptografado;
    private String tmp = "";

    public Sistema() {
        this.observadores = new ArrayList<>();
    }

    public void setTextoCriptografado(String leituraDoQrcode) {
        this.textoCriptografado = leituraDoQrcode;
    }

    @Override
    public void incluirObservador(Observador obs) {
        this.observadores.add(obs);
    }

    @Override
    public void removerObservador(Observador obs) {
        int ind = this.observadores.indexOf(obs);
        if (ind >= 0) {
            this.observadores.remove(obs);
        }
    }

    @Override
    public void notificarObservadores() {
        for (Observador observador : observadores) {

            Criptografia c = new Criptografia();
            String retorno = c.decifra(textoCriptografado);

            if (retorno.equals(tmp)) {
                try {
                    System.out.print("Retire o QR-Code e aguarde");
                    TimeUnit.SECONDS.sleep(2); System.out.print(".");
                    TimeUnit.SECONDS.sleep(2); System.out.print(".");
                    TimeUnit.SECONDS.sleep(2); System.out.println(".");
                    TimeUnit.SECONDS.sleep(2); System.out.println("Pronto.");
                    this.tmp = "";
                } catch (InterruptedException e) { }
            } else {
                this.tmp = retorno;
                observador.atualizar(new String(retorno));
            }
        }
    }

}
