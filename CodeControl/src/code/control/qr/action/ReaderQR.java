package code.control.qr.action;

import code.control.observer.SystemObservable;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JFrame;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.FlowLayout;

public class ReaderQR extends JFrame implements Runnable, ThreadFactory {

    private Executor executor = Executors.newSingleThreadExecutor(this);

    private Webcam webcam = null;
    private WebcamPanel panel = null;
    
    private SystemObservable notificator;

    public ReaderQR(SystemObservable notificator) {
        super();
        
        this.notificator = notificator;
        
        setLayout(new FlowLayout());
        setTitle("Code Control - QR-Code");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension size = WebcamResolution.QVGA.getSize();

        //seleciona qual webcam utilizar
        webcam = Webcam.getWebcams().get(0);
        
        if(webcam != null){
        webcam.setViewSize(size);
        }
        
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);

        add(panel);

        pack();
        setVisible(true);

        executor.execute(this);
    }

    @Override
    public void run() {
        webcam.open();

        do {
            try {
                Thread.sleep(100);
                /* Will limit performance to maximum 10 FPS. See the following.
                 Same image, same light, but FPS is three times smaller.
                
                 Origem: https://github.com/sarxos/webcam-capture/pull/257
                 Sem o sleep fica em torno de 30 FPS, com sleep fica em torno de 9. */
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            /* Captura de imagens */
            if (webcam.isOpen()) {

                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    /* Quando verdadeiro, isso significa que 
                     não há nenhum QR Code na imagem */
                }
            }
            /* Área destinada ao tratamento do que foi lido pelo QR Code */
            /*-----------------------------------------------------------*/
            if (result != null) {
                //Inicia Padrão Observer
                notificator.setTextoCriptografado(result.getText());
                notificator.notificarObservadores();
            /*-----------------------------------------------------------*/
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "Sistema Code Control");
        t.setDaemon(true);
        return t;
    }

}
