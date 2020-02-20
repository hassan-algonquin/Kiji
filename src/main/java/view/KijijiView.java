/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.FileUtility;
import entity.Category;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import scraper.kijiji.Kijiji;
import entity.Image;
import entity.Item;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logic.CategoryLogic;
import logic.ImageLogic;
import logic.ItemLogic;


/**
 *
 * @author Hassan
 */
@WebServlet(name = "KijijiView", urlPatterns = {"/Kijiji"})
public class KijijiView extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet KijijiView</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet KijijiView at " + request.getContextPath() + "</h1>");
            out.println("<table style=\"margin-left: auto; margin-right: auto;\" border=\"1\">");
            out.println("<caption>Category</caption>");
            //this is an example, for your other tables use getColumnNames from
            //logic to create headers in a loop.
            out.println("<tr>");
            out.println("<th>Title</th>");
            out.println("<th>Url</th>");
            out.println("<th>Id</th>");
            out.println("</tr>");

            ItemLogic logic = new ItemLogic();
            List<Item> entities = logic.getAll();
            for (Item e : entities) {
                //for other tables replace the code bellow with
                //extractDataAsList in a loop to fill the data.
                out.printf("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                        logic.extractDataAsList(e).toArray());
            }

            out.println("<tr>");
            //this is an example, for your other tables use getColumnNames from
            //logic to create headers in a loop.
            out.println("<th>Title</th>");
            out.println("<th>Url</th>");
            out.println("<th>Id</th>");
            out.println("</tr>");
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
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
       String mydir = System.getProperty("user.home") + "/KijijImages/";
       File myfiles = new File(mydir);
       if (!myfiles.exists())
               myfiles.mkdir();
       Category cat = new CategoryLogic().getWithId(1);
       Kijiji myKijiji = new Kijiji();
       myKijiji.downloadPage(mydir);
       myKijiji.findAllItems();
       myKijiji.proccessItems(e ->{
       Image image = new ImageLogic().getWithPath(mydir+ e.getId()+".jpg");
       Map<String,String[]>imageMap = new HashMap<>();
       if(image ==null){
           FileUtility.downloadAndSaveFile(e.getImageUrl(),mydir,e.getId()+".jpg");
           String imagePath = mydir +e.getId() +".jpg";
           String imageUrl =e.getImageUrl();
           String imageName=e.getImageName();
           
           imageMap.put(ImageLogic.PATH, new String[]{imagePath});
           imageMap.put(ImageLogic.NAME, new String[]{imageName});
           imageMap.put(ImageLogic.URL, new String[]{imageUrl});
           
           image= new ImageLogic().createEntity(imageMap);
           new ImageLogic().add(image);
           
           
           ItemLogic itemLogic = new ItemLogic();
           
           Map<String,String[]> kijijiItemMap = new HashMap<>();
           kijijiItemMap.put(ItemLogic.DESCRIPTION, new String[]{e.getDescription()});
           kijijiItemMap.put(ItemLogic.LOCATION, new String[]{e.getLocation()});
           kijijiItemMap.put(ItemLogic.PRICE, new String[]{e.getPrice()});
           kijijiItemMap.put(ItemLogic.URL, new String[]{e.getUrl()});
           kijijiItemMap.put(ItemLogic.TITLE, new String[]{e.getTitle()});
           kijijiItemMap.put(ItemLogic.DATE, new String[]{e.getDate()});
           kijijiItemMap.put(ItemLogic.ID, new String[]{e.getId()});
          
           
           Item item = new ItemLogic().createEntity(kijijiItemMap);
           item.setCategory(cat);
           item.setImage(image);
           
            new ItemLogic().add(item);
           
           
       }
       
       
       });
       
       processRequest(request,response);
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
