/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.control.frontend.search;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author dunfrey
 */
public class TableModel{
    
    private IntegerProperty codigo;
    private StringProperty nome;

    public TableModel() {
    }
    
    public TableModel(int codigo, String nome) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nome = new SimpleStringProperty(nome);
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public StringProperty nomeProperty() {
        return nome;
    }    
    
}
