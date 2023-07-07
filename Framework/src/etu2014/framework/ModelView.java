/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2014.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rado
 */
public class ModelView {
    String view;
    HashMap<String,Object> data;
    HashMap<String,Object> sessions;
    boolean isJson;
    boolean invalidateSession = false;
    List<String> removeSession;

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

    public HashMap<String, Object> getSessions() {
        return sessions;
    }

    public void setSessions(HashMap<String, Object> sessions) {
        this.sessions = sessions;
    }

    public boolean isInvalidateSession() {
        return invalidateSession;
    }

    public void setInvalidateSession(boolean invalidateSession) {
        this.invalidateSession = invalidateSession;
    }

    public List<String> getRemoveSession() {
        return removeSession;
    }

    public void setRemoveSession(List<String> removeSession) {
        this.removeSession = removeSession;
    }
    
    public ModelView(String view,boolean json) {
        this.setView(view);
        this.setIsJson(json);
        HashMap<String,Object> data = new HashMap<>();
        HashMap<String,Object> session = new HashMap<>();
        ArrayList<String> rmsession = new ArrayList<>();
        this.setData(data);
        this.setSessions(session);
        this.setRemoveSession(rmsession);
    }
    
    public void addItem(String cle, Object objet){
        this.getData().put(cle, objet);
    }
    
    public void addSession(String cle, Object objet){
        this.getSessions().put(cle, objet);
    }
    
    public void addrmSession(String sessionName){
        this.getRemoveSession().add(sessionName);
    }
}
