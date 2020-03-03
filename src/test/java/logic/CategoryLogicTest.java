package logic;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import entity.Category;
import common.TomcatStartUp;
import common.ValidationException;
import dal.EMFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Shariar
 */
class CategoryLogicTest {

    private CategoryLogic logic;
    private Map<String, String[]> sampleMap;
    private Category expectedCategory;

    @BeforeAll
    final static void setUpBeforeClass() throws Exception {
        TomcatStartUp.createTomcat();
    }

    @AfterAll
    final static void tearDownAfterClass() throws Exception {
        TomcatStartUp.stopAndDestroyTomcat();
    }

    @BeforeEach
    final void setUp() throws Exception {
        logic = new CategoryLogic();
        sampleMap = new HashMap<>();
        sampleMap.put(CategoryLogic.TITLE, new String[]{"Tilte of Category"});
        sampleMap.put(CategoryLogic.URL, new String[]{"www.myweb.com"});
        expectedCategory =logic.createEntity(sampleMap);
        //expectedItem.setCategory(new CategoryLogic().getWithId(1));
        
    }

    @AfterEach
    final void tearDown() throws Exception {
        if (expectedCategory != null) {
            logic.delete(expectedCategory);
        }
    }
    

    private void assertCategoryEquals(Category expected, Category actual) {
        //assert all field to guarantee they are the same
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getUrl(), actual.getUrl());
    }
    
    @Test
    final void testGetWithUrl(){
        logic.add(expectedCategory);
        Category trCat =logic.getWithUrl(expectedCategory.getUrl());
        assertEquals(expectedCategory.getUrl(),trCat.getUrl());
        assertEquals(expectedCategory.getTitle(),trCat.getTitle());
        
    }
    
 /*   
       @Test
    final void testGetWithTitle() {
        int foundFull = 0;
        List<Category> returnedCategory = logic.getWithTitle(expectedCategory.getTitle());
        for (Category category : returnedCategory) {
            //all accounts must have the same password
            assertEquals(expectedCategory.getTitle(), category.getTitle());
            //exactly one account must be the same
            if (category.getId().equals(expectedCategory.getId())) {
                assertCategoryEquals(expectedCategory, category);
                foundFull++;
            }
        }
        assertEquals(1, foundFull, "if zero means not found, if more than one means duplicate");
    }
*/
    
 /*   @Test
    final void testGetWithTitle(){
        List<Category> list = logic.getAll();
        Category testCategory = list.get(0);
        List<Category> expectedCategory = (List<Category>) logic.getWithTitle(testCategory.getTitle());
             for(Category ee : expectedCategory){
        assertEquals(testCategory.getUrl(),ee.getUrl());
        assertEquals(testCategory.getTitle(),ee.getTitle());
             }
        
    }*/
    
    
    
}