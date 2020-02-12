package logic;

import dal.ItemDAL;
import entity.Item;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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

    
    public List<Item> getWithPrice( String price){
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
    public List<Item> getWithCategory( String categoryId){
        return get(()->dao().findByCategory(categoryId));
    }
    
    
    
    

    
  /*  @Override
    public List<Item> search( String search){
        return get(()->dao().findContaining(search));
    }*/

    @Override
    public Item createEntity(Map<String, String[]> parameterMap) {
        Item item = new Item();
        if(parameterMap.containsKey(CATEGORY_ID)){
            item.setId(Integer.parseInt(parameterMap.get(CATEGORY_ID)[0]));
        }
        item.setDescription(parameterMap.get(DESCRIPTION)[0]);
//        item.setCategory(parameterMap.get(CATEGORY_ID)[0]);
  //      item.setImage(parameterMap.get(IMAGE_ID)[0]);
    //    item.setLocation(parameterMap.get(LOCATION));
        return item;
    } 

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("Description", "Category Id", "Image Id", "Location","Price","Title","Date","Url","Id");
    }

    @Override
    public List<String> getColumnCodes() {
        return Arrays.asList(DESCRIPTION, CATEGORY_ID, IMAGE_ID, LOCATION,PRICE,TITLE,DATE,URL,ID);
    }

    @Override
    public List<?> extractDataAsList( Item e) {
        return Arrays.asList(e.getDescription(), e.getCategory(),e.getImage(),e.getLocation(),e.getPrice(),e.getTitle(),e.getUrl(),e.getId());
    }
}
