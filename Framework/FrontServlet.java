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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
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
        String[] classeNames = this.getClassList("/home/rado/Bureau/Frameworktest/Temp/src/java");
        HashMap<String, Mapping> mappingurl = new HashMap<>();
        for(String classeName : classeNames){
            Class<?> classe = null;
            try {
                classe = Class.forName(classeName);
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


     public static String[] getClassList(String directoryPath)
    {
        File file=new File(directoryPath);

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
        try {
            mv = this.check(url);
            RequestDispatcher dispatcher = request.getRequestDispatcher(mv.getView());
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
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code.
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>URL: etoo" + url + "</h1>");
            try{
                out.println("<h1>Method: " + this.check(url).getView() + "</h1>");
            }
            catch (ClassNotFoundException ex) {
                out.print("ClassNotFoundException:"+ex.getMessage());
            } catch (NoSuchMethodException ex) {
                out.print("NoSuchMethodException:"+ex.getMessage());
            } catch (InstantiationException ex) {
                out.print("InstantiationException:"+ex.getMessage());
            } catch (IllegalAccessException ex) {
                out.print("IllegalAccessException:"+ex.getMessage());
            } catch (IllegalArgumentException ex) {
                out.print("IllegalArgumentException:"+ex.getMessage());
            } catch (InvocationTargetException ex) {
                out.print("InvocationTargetException:"+ex.getMessage());
            } catch (UrlInconue ex) {
                out.print("UrlInconue:"+ex.getMessage());
            }
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){

        }*/
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
