package code.control.observer;

public interface Observable {
    
    public void incluirObservador(Observer obs);   
    public void removerObservador(Observer obs);   
    public void notificarObservadores();
    
}
