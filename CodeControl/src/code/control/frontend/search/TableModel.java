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
    
    private IntegerProperty code;
    private StringProperty name;

    public TableModel() {
    }
    
    public TableModel(int code, String name) {
        this.code = new SimpleIntegerProperty(code);
        this.name = new SimpleStringProperty(name);
    }

    public IntegerProperty codeProperty() {
        return code;
    }

    public StringProperty nameProperty() {
        return name;
    }    
    
}
