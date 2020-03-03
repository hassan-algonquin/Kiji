package logic;

import common.ValidationException;
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
        Category category = new Category();
        if(parameterMap.containsKey(ID)){
            category.setId(Integer.parseInt(parameterMap.get(ID)[0]));
        }
        
        String _title =parameterMap.get(TITLE)[0];
        if(_title==null || _title.isEmpty())
            throw new ValidationException("The title must exist");
        
        // Title varchar(255)
        if (_title.length()>255 )
            throw new ValidationException("Title can't be greater than 255 characters");
         category.setTitle(parameterMap.get(TITLE)[0]);
         
        String _url =parameterMap.get(URL)[0];
        if(_url==null || _url.isEmpty())
            throw new ValidationException("The url must exist");
        
        // Title varchar(255)
        if (_url.length()>255 )
            throw new ValidationException("Url can't be greater than 255 characters");
         
        category.setUrl(parameterMap.get(URL)[0]);
        return category;
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
