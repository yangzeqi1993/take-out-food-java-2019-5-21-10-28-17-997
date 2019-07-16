import java.util.Arrays;
import java.util.List;

public class TestData {
    public static final List<Item> ALL_ITEMS = Arrays.asList(
            new Item("ITEM0001", "»ÆìË¼¦", 18.00),
            new Item("ITEM0013", "Èâ¼ÐâÉ", 6.00),
            new Item("ITEM0022", "Á¹Æ¤", 8.00),
            new Item("ITEM0030", "±ù·æ", 2.00)
    );


    public static final List<SalesPromotion> ALL_SALES_PROMOTIONS = Arrays.asList(
            new SalesPromotion("BUY_30_SAVE_6_YUAN", "Âú30¼õ6Ôª", Arrays.asList()),
            new SalesPromotion("50%_DISCOUNT_ON_SPECIFIED_ITEMS", "Ö¸¶¨²ËÆ·°ë¼Û", Arrays.asList(
                    "ITEM0001", "ITEM0022"
            ))
    );

}
