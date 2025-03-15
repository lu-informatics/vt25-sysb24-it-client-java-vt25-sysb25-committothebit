// models/Ingredient.java
package appetite.java.client.models;

public class Ingredient {
    private int id;
    private String name;
    private String category;
    private String unit;
    private String dietTag;
    
    public Ingredient() { }
    
    public Ingredient(int id, String name, String category, String unit, String dietTag) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.unit = unit;
        this.dietTag = dietTag;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    
    public String getDietTag() { return dietTag; }
    public void setDietTag(String dietTag) { this.dietTag = dietTag; }
}
