/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2014.framework;

/**
 *
 * @author rado
 */
public class Mapping {
    String className;
    String Method;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String Method) {
        this.Method = Method;
    }

    public Mapping(String className, String Method) {
        this.setClassName(className);
        this.setMethod(Method);
    }   
}
