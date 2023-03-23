/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package frameworks;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 *
 * @author rado
 */
public class Frameworks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            File fichier = new File("/home/rado/NetBeansProjects/Frameworks/test.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(fichier);
            doc.getDocumentElement().normalize();
            System.out.println("Element: "+doc.getDocumentElement().getNodeName());
            NodeList node = doc.getElementsByTagName("employer");

            for (int i = 0; i < node.getLength(); i++) {
                Node nd = node.item(i);
                if(nd.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) nd;
                    int num = i+1;
                    System.out.println("\nEmployer numero "+num);
                    System.out.println("Id employer: "+element.getElementsByTagName("id").item(0).getTextContent());
                    System.out.println("Nom employer: "+element.getElementsByTagName("nom").item(0).getTextContent());
                    System.out.println("Prenom employer: "+element.getElementsByTagName("prenom").item(0).getTextContent());
                    System.out.println("Departement employer: "+element.getElementsByTagName("departement").item(0).getTextContent());
                    System.out.println("Salaire employer: "+element.getElementsByTagName("salaire").item(0).getTextContent());
                }
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
    
}
