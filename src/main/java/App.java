import model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    private ItemRepository itemRepository;
    private SalesPromotionRepository salesPromotionRepository;

    public App(ItemRepository itemRepository, SalesPromotionRepository salesPromotionRepository) {
        this.itemRepository = itemRepository;
        this.salesPromotionRepository = salesPromotionRepository;
    }

    public String bestCharge(List<String> inputs) {
        //TODO: write code here

        String menu_input = String.join(",", inputs);

        // System.out.println(menu_input);

        String[] menu = menu_input.split(",");

        List<Item> menu_total = this.itemRepository.findAll();
        List<SalesPromotion> sales_total = this.salesPromotionRepository.findAll();

        //Item 数据字典
        Map<String, String> id_name = new HashMap<>();
        for(Item item : menu_total){
            id_name.put(item.getId(), item.getName());
        }

        Map<String, Double> id_price = new HashMap<>();
        for(Item item : menu_total) {
            id_price.put(item.getId(), item.getPrice());
        }


        double total_money = 0;     //  未打折总费用

        //创建菜品的对象
        int len = menu.length;
        ItemInput iteminput[]  = new ItemInput[len];

        for(int i=0; i<len; i++){

            String[] menu_item = menu[i].split(" ");

            iteminput[i] = new ItemInput(menu_item[0], id_name.get(menu_item[0]),
                    id_price.get(menu_item[0]), Integer.parseInt(menu_item[2]),
                    Integer.parseInt(menu_item[2])*id_price.get(menu_item[0]));
            total_money = total_money + iteminput[i].total_price;
        }


        //创建优惠方式的对象
        int method_num = sales_total.size();
        PromotionInput promotioninput[]  = new PromotionInput[method_num];

        int method_kind = 0;
        for(SalesPromotion salespromotion : sales_total){
            String relateditems = String.join(",", salespromotion.getRelatedItems());
            promotioninput[method_kind++] = new PromotionInput(salespromotion.getType(), salespromotion.getDisplayName(), relateditems);
          //  System.out.println(promotioninput[method_kind-1].relatedItems);
        }

        //计算优惠后的金额
        int method_final = 0;                       // 定义最终所采用的方法

        //采用满30减6方法优惠
        double  promotion_money1 = total_money;      //优惠一打折后的价格
        String[] promotion_price = promotioninput[0].type.split("_");
        if (total_money >= Integer.parseInt(promotion_price[1])) {
            promotion_money1 -= Integer.parseInt(promotion_price[3]);
            method_final = 1;
           // System.out.println("优惠一减后的金额：" + promotion_money1);
        }

          //采用部分菜品半价优惠
        double  promotion_money2 = total_money;      //优惠二打折后的价格
        double reduced_money = 0;                //定义优惠的金额
        String discount_str = promotioninput[1].type.substring(0,2);
        double discount = Double.parseDouble(discount_str)/100;
        String[] promotionid = promotioninput[1].relatedItems.split(",");
        for (int i=0; i<promotionid.length; i++){
            for (int j=0; j<len; j++)
            if (promotionid[i].equals(iteminput[j].id)){
                reduced_money += iteminput[j].total_price*(1-discount);
                method_final = 2;
            }
        }
        if(method_final==2) {
            promotion_money2 -= reduced_money;
           // System.out.println("优惠二减后的金额：" + promotion_money2);
        }


        //判断那种方法价格低
        if (promotion_money1 < promotion_money2) {
            total_money = promotion_money1;
            method_final = 1;
        } else {
            total_money = promotion_money2;
            // method_final = 2;        //此时method_final为2或0
        }


        //输出菜单
        String menu_output;
        if (method_final == 1) {
            StringBuffer buf = new StringBuffer();
            buf.append("============= 订餐明细 =============\n");
            for(int i=0; i<len; i++){
                buf.append(iteminput[i].name +" x " +iteminput[i].num +" = " + Math.round(iteminput[i].total_price) + "元\n");
            }
            buf.append("-----------------------------------\n" + "使用优惠:\n");
            buf.append(promotioninput[method_final-1].displayName + "，省" + promotion_price[3] + "元\n" +
                    "-----------------------------------\n");
            buf.append("总计：" + Math.round(total_money) + "元\n" +
                    "===================================");
            menu_output = buf.toString();
        } else if (method_final==2) {
            StringBuffer buf = new StringBuffer();
            buf.append("============= 订餐明细 =============\n");
            for(int i=0; i<len; i++){
                buf.append(iteminput[i].name +" x " +iteminput[i].num +" = " + Math.round(iteminput[i].total_price) + "元\n");
            }
            buf.append("-----------------------------------\n" + "使用优惠:\n");
            buf.append(promotioninput[method_final-1].displayName + "(黄焖鸡，凉皮)，省" + Math.round(reduced_money) + "元\n" +
                    "-----------------------------------\n");
            buf.append("总计：" + Math.round(total_money) + "元\n" +
                    "===================================");
            menu_output = buf.toString();

        } else {
            StringBuffer buf = new StringBuffer();
            buf.append("============= 订餐明细 =============\n");
            for(int i=0; i<len; i++){
                buf.append(iteminput[i].name +" x " +iteminput[i].num +" = " + Math.round(iteminput[i].total_price) + "元\n");
            }
            buf.append("-----------------------------------\n");
            buf.append("总计：" + Math.round(total_money) + "元\n" +
                    "===================================");
            menu_output = buf.toString();
        }
        System.out.println(menu_output);
       return menu_output;

    }
}
