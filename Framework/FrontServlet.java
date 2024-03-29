/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu2014.framework.servlet;

import annotation.Url;
import etu2014.framework.ModelView;
import etu2014.framework.Mapping;
import exception.UrlInconue;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rado
 */
@WebServlet(name = "FrontServlet", urlPatterns = {"/"})
public class FrontServlet extends HttpServlet {

    HashMap<String, Mapping> MappingUrls;

    public HashMap<String, Mapping> getMappingUrls() {
        return MappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> MappingUrls) {
        this.MappingUrls = MappingUrls;
    }

    @Override
    public void init() throws ServletException {
        String[] classeNames = this.getClassList();
        HashMap<String, Mapping> mappingurl = new HashMap<>();
        for(String classeName : classeNames){
            Class<?> classe = null;
            try {
                classe = Class.forName("test."+classeName);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            Method[] methods = classe.getDeclaredMethods();
            for (Method method : methods){
                Url url = method.getAnnotation(Url.class);
                if (url != null){
                    Class<?> conteneurClass = method.getDeclaringClass();
                    /*this.setMethod(method.getName());
                    this.setAnnotation(url.path());
                    this.setClassObj(conteneurClass.getName());*/
                    Mapping mapping = new Mapping(conteneurClass.getName(),method.getName());
                    mappingurl.put(url.path(), mapping);
                }
            }
        }
        this.setMappingUrls(mappingurl);
    }


     public static String[] getClassList()
    {
        File file=new File("/home/rado/Bureau/Frameworktest/Temp/src/java/test");

        File[] f=file.listFiles();
        FilenameFilter textFilefilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
//        String lowercaseName = name.toLowerCase();
                if (name.endsWith(".java")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String [] listefile=file.list(textFilefilter);
        for (int i = 0; i < listefile.length; i++) {
            listefile[i]=listefile[i].split(".java")[0];
        }
        return listefile;
    }



    public ModelView check(String url) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, UrlInconue, InstantiationException{
        if(this.getMappingUrls().containsKey(url)){
            String classname = this.getMappingUrls().get(url).getClassName();
            String methode = this.getMappingUrls().get(url).getMethod();
            Class<?> classe = Class.forName(classname);
            Method method = classe.getDeclaredMethod(methode);
            Object objet = classe.newInstance();
            ModelView mv = (ModelView)method.invoke(objet);
            return mv;
        }else{
            throw new UrlInconue();
        }
    }

    public ModelView save(String url,String[] params,String[] values) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, UrlInconue, InstantiationException{
        String classname = this.getMappingUrls().get(url).getClassName();
        String methode = this.getMappingUrls().get(url).getMethod();
        Class<?> classe = Class.forName(classname);
        Object objet = classe.newInstance();
        Field[] fields = classe.getDeclaredFields();
        String[] allparm = new String[0];
        for(Field field : fields){
            for(int i = 0;i < params.length;i++){
                if(params[i].equals(field.getName())){
                    Method setobject = classe.getMethod("set"+this.upperFirst(params[i]),field.getType());
                    Object type = null;
                    if (field.getType() == String.class) {
                        type = values[i];
                    } else if (field.getType() == int.class || field.getType() == Integer.class) {
                        type = Integer.valueOf(values[i]);
                    } else if (field.getType() == double.class || field.getType() == Double.class) {
                        type = Double.valueOf(values[i]);
                    } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                        type = Boolean.valueOf(values[i]);
                    }
                    allparm = Arrays.copyOf(allparm,allparm.length + 1);
                    allparm[allparm.length - 1] = type.getClass().getName();
                    setobject.invoke(objet, type);
                }
            }
        }
        Method method = classe.getDeclaredMethod(methode);
        ModelView mv = (ModelView)method.invoke(objet);
        return mv;
    }

    public String upperFirst(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String url = request.getServletPath();
        ModelView mv;
        try (PrintWriter out = response.getWriter()) {
            try {
              if (this.getMappingUrls().containsKey(url) && url.contains("save")){
                  Enumeration<String> parametres = request.getParameterNames();
                  String[] attributs = new String[0];
                  String[] values = new String[0];
                  while(parametres.hasMoreElements()){
                      String Parametre = parametres.nextElement();
                      String value = request.getParameter(Parametre);
                      attributs = Arrays.copyOf(attributs,attributs.length + 1);
                      attributs[attributs.length - 1] = Parametre;
                      values = Arrays.copyOf(values,values.length + 1);
                      values[values.length - 1] = value;
                      out.println("<h2>" + value + "</h1>");
                  }
                  mv = this.save(url,attributs,values);
              }
              else mv = this.check(url);
              out.print("<h3>" + k + "</h3>");
              RequestDispatcher dispatcher = request.getRequestDispatcher(mv.getView());
              for (Map.Entry<String, Object> entry : mv.getData().entrySet()) {
                  Object key = entry.getKey();
                  Object val = entry.getValue();
                  request.setAttribute("cle",(String)key);
                  request.setAttribute((String)key,val);
              }
              dispatcher.forward(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UrlInconue ex) {
                out.println("<h1>" + ex.getMessage() + "</h1>");;
            } catch (InstantiationException ex) {
                Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(Exception e){

        }
    }



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
