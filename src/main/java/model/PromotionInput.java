package model;

public class PromotionInput {
    public String type;
    public String displayName;
    public String relatedItems;

    public PromotionInput(String type, String displayName, String relatedItems) {
        this.type = type;
        this.displayName = displayName;
        this.relatedItems = relatedItems;
    }
}
