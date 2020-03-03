package scraper.kijiji;

import java.util.Objects;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Shariar (Shawn) Emami
 */
public final class ItemBuilder {
    
    private static final String URL_BASE = "https://www.kijiji.ca";

    private static final String ATTRIBUTE_ID = "data-listing-id";
    private static final String ATTRIBUTE_IMAGE = "image";
    private static final String ATTRIBUTE_IMAGE_SRC = "data-src";
    private static final String ATTRIBUTE_IMAGE_NAME = "alt";
    private static final String ATTRIBUTE_PRICE = "price";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String ATTRIBUTE_LOCATION = "location";
    private static final String ATTRIBUTE_DATE = "date-posted";
    private static final String ATTRIBUTE_DESCRIPTION = "description";

    private Element element;
    private KijijiItem item;
    
    public ItemBuilder() {
       item = new KijijiItem();
    }

    public ItemBuilder setElement( Element element){
        this.element=element;
        return this;
    }
    
    public KijijiItem build(){
      
        
       item.setId(element.attr(ATTRIBUTE_ID).trim());
      item.setUrl(URL_BASE+element.getElementsByClass(ATTRIBUTE_TITLE).get(0).child(0).attr("href").trim());
   
      // Elements elementURL = element.getElementsByClass(URL_BASE);
   // item.setUrl(URL_BASE + element.getElementsByClass(ATTRIBUTE_TITLE).get(0).child(0).text().trim());
    //item.setUrl(URL_BASE+element.getElementsByClass(ATTRIBUTE_TITLE).get(0).child(0).attr("herf").trim());
       
   
        
   
 //      Elements elementID = element.getElementsByClass(ATTRIBUTE_ID);
       
     
       Elements elementPrice = element.getElementsByClass(ATTRIBUTE_PRICE);
       if(elementPrice.isEmpty())
          item.setPrice("");
       else 
         item.setPrice(elementPrice.get(0).text().trim());
         

        Elements elementTitle = element.getElementsByClass(ATTRIBUTE_TITLE);
        if(elementTitle.isEmpty())
               item.setTitle("");
       else 
         item.setTitle(elementTitle.get(0).child(0).text().trim());
       
        
       Elements elementLocation = element.getElementsByClass(ATTRIBUTE_LOCATION);
           if(elementLocation.isEmpty())
               item.setLocation("");
       else 
         item.setLocation(elementLocation.get(0).childNode(0).outerHtml().trim());
       
       Elements elementDate = element.getElementsByClass(ATTRIBUTE_DATE);
       if(elementDate.isEmpty())
               item.setDate("");
       else 
         item.setDate(elementDate.get(0).text().trim());
       
       Elements elementDescription = element.getElementsByClass(ATTRIBUTE_DESCRIPTION);
       if(elementDescription.isEmpty())
               item.setDescription("");
       else 
         item.setDescription(elementDescription.get(0).text().trim());
        
      
        Elements elements = element.getElementsByClass(ATTRIBUTE_IMAGE);
        if(elements.isEmpty()){
           item.setImageName("");
           item.setImageUrl("");}
        else{
          
              String imageName = elements.get(0).child(0).attr(ATTRIBUTE_IMAGE_NAME).trim();
              if (imageName.isEmpty()) {
              imageName = elements.get(0).child(0).child(1).attr(ATTRIBUTE_IMAGE_NAME).trim();
             item.setImageName(imageName);
             }else{
                  item.setImageName(imageName);}
         
             String imageScr = elements.get(0).child(0).attr(ATTRIBUTE_IMAGE_SRC).trim();
             if (imageScr.isEmpty()) {
             imageScr = elements.get(0).child(0).attr("src").trim();
        
               if (imageScr.isEmpty()) {
               imageScr = elements.get(0).child(0).child(1).attr(ATTRIBUTE_IMAGE_SRC).trim();
               item.setImageUrl(imageScr);
               } else 
                    {item.setImageUrl(imageScr);}
             }else
                 item.setImageUrl(imageScr);
        }
        
        
        
        
     return item;
    }
    
    
           
}