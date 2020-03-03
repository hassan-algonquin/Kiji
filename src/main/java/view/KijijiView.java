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
            out.println("<h1>Servlet KijijiView at " + request.getContextPath() + "</h1>");
            out.println("<table style=\"margin-left: auto; margin-right: auto;\" border=\"1\">");

            /* TODO output your page here. You may use following sample code. */
            ItemLogic logic = new ItemLogic();
            List<Item> entities = logic.getAll();
            for (Item e : entities) {
                //for other tables replace the code bellow with
                //extractDataAsList in a loop to fill the data.
            
                
                
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet KijijiView</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table>");
            
            out.println("<div class='center-column'>");
            out.println("<div class='item'>");
            out.println("<tr><td>");
            
            out.println("<div class='image'>");
            out.printf("<img src=\"image/%s.jpg\" style=\"width: 250px; height: 200px;\" />",e.getId());
            out.println("</div>");
            out.println("</td><td>");
            
            out.println("<div class='details'>");
            out.println("<div class='title'>");
            out.printf("<a href='%s' target='_blank'>%s</a>",e.getUrl(),e.getTitle());
            out.println("</div>");
            out.println("<div class='price'>");
            out.println(e.getPrice());
            out.println("</div>");
            out.println("<div class='date'>");
            out.println(e.getDate());
            out.println("</div>");
            out.println("<div class='location'>");
            out.println(e.getLocation());
            out.println("</div>");
            out.println("<div class='description'>");
        //    out.printf("<script src='https://maps.googleapis.com/maps/api/js?v=3.exp&key=HassanHakim'></script>"
          //          + "<div style='overflow:hidden;height:332px;width:509px;'>"
           //         + "<div id='gmap_canvas' style='height:332px;width:509px;'>"
            //        + "</div><style>#gmap_canvas img{max-width:none!important;background:none!important}</style></div> "
             //       + "<a href='https://www.add-map.net/'>google map wordpress widget</a>"
              //      + " <script type='text/javascript' src='https://embedmaps.com/google-maps-authorization/script.js?id=bc5c144279a9c69f2e0ee79f70056f31e240f964'>"
              //      + "</script><script type='text/javascript'>function init_map(){var myOptions = {zoom:12,center:new google.maps.LatLng(45.3286,-75.7703),mapTypeId: google.maps.MapTypeId.HYBRID};map = new google.maps.Map(document.getElementById('gmap_canvas'), myOptions);marker = new google.maps.Marker({map: map,position: new google.maps.LatLng(45.3286,-75.7703)});infowindow = new google.maps.InfoWindow({content:'<strong></strong><br><br> %s<br>'});google.maps.event.addListener(marker, 'click', function(){infowindow.open(map,marker);});infowindow.open(map,marker);}google.maps.event.addDomListener(window, 'load', init_map);</script>", e.getLocation());
            out.println(e.getDescription());
            
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</td></tr>");
          
            out.println("</table>");
            out.println("<br>");
            out.println("</body>");
            out.println("</html>");
            }
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
        //processRequest(request, response);
       String mydir = System.getProperty("user.home") + "/KijijiImages/";
       File myfiles = new File(mydir);
       if (!myfiles.exists())
               myfiles.mkdir();
       Category cat = new CategoryLogic().getWithId(1);
 
       Kijiji myKijiji = new Kijiji();
       myKijiji.downloadPage(cat.getUrl());
       myKijiji.findAllItems();
       ImageLogic iLogic= new ImageLogic();
       myKijiji.proccessItems(e ->{
           
       FileUtility.downloadAndSaveFile(e.getImageUrl(),mydir,e.getId()+".jpg");
//       if(iLogic.getWithUrl(e.getUrl()).isEmpty()){
//        
//       } 
       
           Map<String,String[]> itemMap = new HashMap<>();      
           itemMap.put(ItemLogic.DESCRIPTION, new String[]{e.getDescription()});
           itemMap.put(ItemLogic.LOCATION, new String[]{e.getLocation()});
           itemMap.put(ItemLogic.PRICE, new String[]{e.getPrice()});
           itemMap.put(ItemLogic.URL, new String[]{e.getUrl()});
           itemMap.put(ItemLogic.TITLE, new String[]{e.getTitle()});
           itemMap.put(ItemLogic.DATE, new String[]{e.getDate()});
           itemMap.put(ItemLogic.ID, new String[]{e.getId()});
           Item item = new ItemLogic().createEntity(itemMap);
  

           String imagePath = mydir +e.getId() +".jpg";
           String imageUrl =e.getImageUrl();
           String imageName=e.getImageName();
                 
           Map<String,String[]>imageMap = new HashMap<>();
           imageMap.put(ImageLogic.PATH, new String[]{mydir +e.getId() +".jpg"});
           imageMap.put(ImageLogic.NAME, new String[]{e.getImageName()});
           imageMap.put(ImageLogic.URL, new String[]{e.getImageUrl()});
           Image image= iLogic.createEntity(imageMap);
           
            if(iLogic.getWithPath(imagePath) ==null){
            ItemLogic itemLogic = new ItemLogic();
            iLogic.add(image);
            item.setCategory(cat);
            item.setImage(image);
            itemLogic.add(item);
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
