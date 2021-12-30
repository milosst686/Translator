
package translator;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Translator {
    static String rec;
    String izvorniJezik;
    String ciljaniJezik;
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        
        Translator t = new Translator();
        
        t.translator("Win", "eng", "srb");       
    }

    public Translator() {
    }
    
    
    public void translator(String rec, String iJezik, String cJezik)throws ParserConfigurationException, SAXException, IOException 
    {
    this.rec = rec;
    this.izvorniJezik = iJezik;
    this.ciljaniJezik = cJezik;
    
    
        File inpuFile = new File("spisakReci.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder dB = dbf.newDocumentBuilder();
        Document doc = dB.parse(inpuFile);
        doc.getDocumentElement().normalize();
        Element koren = doc.getDocumentElement();
 
        NodeList reci = koren.getElementsByTagName("rec");
        NodeList eng = koren.getElementsByTagName("eng");
        NodeList srb = koren.getElementsByTagName("srb");
        
        for (int i=0; i<reci.getLength();i++)
        {
           if(  izvorniJezik.equals("eng") )
           {
               if(rec.equals(eng.item(i).getFirstChild().getTextContent())){
            System.out.println("English word: "+rec+", on Serbian is word: "+srb.item(i).getFirstChild().getTextContent());
            break;
               }
               else 
               {
                   System.out.println("Check if you write word right, or maybe it doesnt exist in our database");
                   break;
               }
           }
           else if ( izvorniJezik.equals("srb") )
           {
               if (rec.equals(srb.item(i).getFirstChild().getTextContent()))
               {
                System.out.println("Srpska rec: "+rec+", na engleskom znaci: "+eng.item(i).getFirstChild().getTextContent());
                break;
               }
               else 
               {
                   System.out.println("Proverite da li ste uneli ispravnu rec, ukoliko jeste onda rec ne postoji u nasem recniku");
                   break;
               }
           }
               
           }
        }
        
    
    }


