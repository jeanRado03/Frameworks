/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import annotation.Url;
import etu2014.framework.ModelView;
/**
 *
 * @author rado
 */
public class Dept {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dept(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dept() {
    }
    
    @Url(path="/dept-all")
    public ModelView getDept(){
        String view = "dept-all.jsp";
        ModelView mv = new ModelView(view);
        return mv;
    }
}
