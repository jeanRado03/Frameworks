/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2014.framework;

import java.util.HashMap;

/**
 *
 * @author rado
 */
public class ModelView {
    String view;
    HashMap<String,Object> data;
    boolean isJson;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> dictionnaries) {
        this.data = dictionnaries;
    }

    public boolean isIsJson() {
        return isJson;
    }

    public void setIsJson(boolean isJson) {
        this.isJson = isJson;
    }
    
    public ModelView(String view,boolean json) {
        this.setView(view);
        this.setIsJson(json);
        HashMap<String,Object> data = new HashMap<>();
        this.setData(data);
    }
    
    public void addItem(String cle, Object objet){
        this.getData().put(cle, objet);
    }
}
