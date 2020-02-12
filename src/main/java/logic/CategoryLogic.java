package logic;

import dal.CategoryDAL;
import entity.Category;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * @author Shariar (Shawn) Emami
 */
public class CategoryLogic extends GenericLogic<Category,CategoryDAL>{

    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String ID = "id";
    
    public CategoryLogic() {
        super(new CategoryDAL());
    }
    
    @Override
    public List<Category> getAll(){
        return get(()->dao().findAll());
    }
    
    
    @Override
    public Category getWithId( int id){
        return get(()->dao().findById(id));
    }

    public Category getWithUrl( String url){
        return get(()->dao().findByUrl(url));
    }
   public Category getWithTitle( String title){
        return get(()->dao().findByTitle(title));
    }
   
   
  /*  @Override
    public List<Item> search( String search){
        return get(()->dao().findContaining(search));
    }*/

    @Override
    public Category createEntity(Map<String, String[]> parameterMap) {
        Category image = new Category();
        if(parameterMap.containsKey(ID)){
            image.setId(Integer.parseInt(parameterMap.get(ID)[0]));
        }
        image.setTitle(parameterMap.get(TITLE)[0]);
        image.setUrl(parameterMap.get(URL)[0]);
        return image;
    } 

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList( "Title","Url","Id");
    }

    @Override
    public List<String> getColumnCodes() {
        return Arrays.asList(TITLE,URL,ID);
    }

    @Override
    public List<?> extractDataAsList( Category e) {
        return Arrays.asList(e.getTitle(),e.getUrl(),e.getId());
    }
}
