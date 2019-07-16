import java.util.Arrays;
import java.util.List;

public class TestData {
    public static final List<Item> ALL_ITEMS = Arrays.asList(
            new Item("ITEM0001", "���˼�", 18.00),
            new Item("ITEM0013", "�����", 6.00),
            new Item("ITEM0022", "��Ƥ", 8.00),
            new Item("ITEM0030", "����", 2.00)
    );


    public static final List<SalesPromotion> ALL_SALES_PROMOTIONS = Arrays.asList(
            new SalesPromotion("BUY_30_SAVE_6_YUAN", "��30��6Ԫ", Arrays.asList()),
            new SalesPromotion("50%_DISCOUNT_ON_SPECIFIED_ITEMS", "ָ����Ʒ���", Arrays.asList(
                    "ITEM0001", "ITEM0022"
            ))
    );

}
