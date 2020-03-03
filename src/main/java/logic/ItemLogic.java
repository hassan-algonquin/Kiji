package logic;

import dal.ItemDAL;
import entity.Item;
import entity.Category;
import entity.Image;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shariar (Shawn) Emami
 */
public class ItemLogic extends GenericLogic<Item,ItemDAL>{
    
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY_ID = "categoryId";
    public static final String IMAGE_ID = "imageId";
    public static final String LOCATION = "location";
    public static final String PRICE = "price";
    public static final String TITLE = "title";
    public static final String DATE = "date";
    public static final String URL = "url";
    public static final String ID = "id";
    
    public ItemLogic() {
        super(new ItemDAL());
    }
    
    @Override
    public List<Item> getAll(){
        return get(()->dao().findAll());
    }
    
    
    @Override
    public Item getWithId( int id){
        return get(()->dao().findById(id));
    }

    
    public List<Item> getWithPrice( BigDecimal price){
    //public List<Item> getWithPrice( BigDecimal price){
        return get(()->dao().findByPrice(price));
    }

    public List<Item> getWithTitle( String title){
        return get(()->dao().findByTitle(title));
    }

    public List<Item> getWithDate( String date){
        return get(()->dao().findByDate(date));
    }

    
    public List<Item> getWithLocation( String location){
        return get(()->dao().findByLocation(location));
    }

    
    public List<Item> getWithDescription( String description){
        return get(()->dao().findByDescription(description));
    }
    
    public Item getWithUrl( String url){
        return get(()->dao().findByUrl(url));
    }
    public List<Item> getWithCategory( int categoryId){
        return get(()->dao().findByCategory(categoryId));
    }
    
    
    
    

    
    @Override
    public List<Item> search( String search){
        return get(()->dao().findContaining(search));
    }

    
    
    @Override
    public Item createEntity(Map<String, String[]> parameterMap) {
      
        Item item = new Item();
        if(parameterMap.containsKey(ID)){
            item.setId(Integer.parseInt(parameterMap.get(ID)[0]));}
        item.setDescription(parameterMap.get(DESCRIPTION)[0]);
     

          String testPrice=parameterMap.get(PRICE)[0].replace("$", "").trim();
          testPrice=testPrice.replace(",", "").trim();
           try{
           item.setPrice( new BigDecimal (testPrice));
           }
           catch(Exception e){
             item.setPrice( new BigDecimal (0));
           }

        item.setLocation(parameterMap.get(LOCATION)[0]);
        item.setTitle(parameterMap.get(TITLE)[0]);
        item.setUrl(parameterMap.get(URL)[0]);

    
  
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
         try {
            item.setDate(dateFormat.parse(parameterMap.get(DATE)[0]));
        } catch (ParseException e) {
          item.setDate(date);
        }    
 
           
       
        return item;
    } 

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("Id","Title","Price", "Location","Date","Url","Description", "Category Id", "Image Id");
    }

    @Override
    public List<String> getColumnCodes() {
        return Arrays.asList(ID,TITLE,PRICE,LOCATION,DATE,URL,DESCRIPTION, CATEGORY_ID, IMAGE_ID);
    }

    @Override
    public List<?> extractDataAsList( Item e) {
        return Arrays.asList(e.getId(),e.getTitle(),e.getPrice(),e.getLocation(),e.getDate(),e.getDescription(), e.getUrl(),e.getCategory(),e.getImage());
    }
    
}
