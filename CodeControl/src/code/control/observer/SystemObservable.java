package code.control.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import code.control.ciphers.CipherClass;

public class SystemObservable implements Observable {

    private List<Observer> observadores;
    private String textoCriptografado;
    private String tmp = "";

    public SystemObservable() {
        this.observadores = new ArrayList<>();
    }

    public void setTextoCriptografado(String leituraDoQrcode) {
        this.textoCriptografado = leituraDoQrcode;
    }

    @Override
    public void incluirObservador(Observer obs) {
        this.observadores.add(obs);
    }

    @Override
    public void removerObservador(Observer obs) {
        int ind = this.observadores.indexOf(obs);
        if (ind >= 0) {
            this.observadores.remove(obs);
        }
    }

    @Override
    public void notificarObservadores() {
        for (Observer observador : observadores) {

            CipherClass c = new CipherClass();
            String returnDecypher = c.decipher(textoCriptografado);

            if (returnDecypher.equals(tmp)) {
                try {
                    System.out.print("Retire o QR-Code e aguarde");
                    TimeUnit.SECONDS.sleep(2); System.out.print(".");
                    TimeUnit.SECONDS.sleep(2); System.out.print(".");
                    TimeUnit.SECONDS.sleep(2); System.out.println(".");
                    TimeUnit.SECONDS.sleep(2); System.out.println("Pronto.");
                    this.tmp = "";
                } catch (InterruptedException e) { }
            } else {
                this.tmp = returnDecypher;
                observador.atualizar(new String(returnDecypher));
            }
        }
    }

}
