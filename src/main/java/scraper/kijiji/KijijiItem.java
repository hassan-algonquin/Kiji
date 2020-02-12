package scraper.kijiji;

import java.util.Objects;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Shariar (Shawn) Emami
 */
public final class KijijiItem {
    private String id;
    private String url;
    private String imageUrl;
    private String imageName;
    private String price;
    private String title;
    private String date;
    private String location;
    private String description;
 
    KijijiItem() {
    }
     
    void setId(String id){
    this.id=id;    
    } 
    void setUrl(String url){
        this.url=url;
    }
    
    void setImageUrl (String imageUrl){
        this.imageUrl=imageUrl;
    }
    
    void setImageName (String imageName){
        this.imageName=imageName;
    }
    
    void setPrice (String price){
        this.price =price;
    }
    
    void setTitle (String title){
        this.title=title;
        }
 
    void setDate(String date){
        this.date=date;
        
    }
    
    void setLocation (String location){
        this.location = location;
    }
    
    void setDescription (String description){
        this.description =description;
    }
    
    
  /*  @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KijijiItem other = (KijijiItem) obj;
        return Objects.equals(setId(id), other.setId(id));
    }

    @Override
    public String toString() {
        return String.format("[id:%s, image_url:%s, image_name:%s, price:%s, title:%s, date:%s, location:%s, description:%s]",
                getId(), getImageUrl(), getImageName(), getPrice(), getTitle(), getDate(), getLocation(), getDescription());
    }*/
}
