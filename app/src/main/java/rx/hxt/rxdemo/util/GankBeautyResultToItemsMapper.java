package rx.hxt.rxdemo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.functions.Func1;
import rx.hxt.rxdemo.model.GankBeauty;
import rx.hxt.rxdemo.model.GankBeautyResult;
import rx.hxt.rxdemo.model.Item;

/**
 * Created by Administrator on 2016/12/22.
 */

public class GankBeautyResultToItemsMapper implements Func1<GankBeautyResult,List<Item>>{
    private GankBeautyResultToItemsMapper() {
    }
    public static GankBeautyResultToItemsMapper INSTANCE = new GankBeautyResultToItemsMapper();

    @Override
    public List<Item> call(GankBeautyResult gankBeautyResult) {
        List<GankBeauty> beauties = gankBeautyResult.beauties;
        List<Item> items = new ArrayList<>(beauties.size());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for (GankBeauty gankBeauty : beauties) {
            Item item = new Item();
            try {
                Date date = inputFormat.parse(gankBeauty.createdAt);
                item.description = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                item.description = "unknown date";
            }
            item.imageUrl = gankBeauty.url;
            items.add(item);
        }
        return items;
    }
}
