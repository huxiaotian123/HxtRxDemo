package rx.hxt.rxdemo.module.cache6.data;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import rx.hxt.rxdemo.App;
import rx.hxt.rxdemo.model.Item;

/**
 * Created by Administrator on 2016/12/26.
 */

public class DataBase {
    private static String DATA_FILE_NAME = "data.db";

    private static DataBase INSTANCE;

    File dataFile = new File(App.getInstance().getFilesDir(),DATA_FILE_NAME);
    Gson gson = new Gson();

    private DataBase(){}

    public static DataBase getINSTANCE(){
        if(null != INSTANCE){
            INSTANCE = new DataBase();
        }
        return  INSTANCE;
    }

    public List<Item> readItems(){
        try {
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Reader reader = new FileReader(dataFile);
            return gson.fromJson(reader, new TypeToken<List<Item>>(){}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeItems(List<Item> items){
        String json = gson.toJson(items);
        try {
            if(!dataFile.exists()){
                dataFile.createNewFile();
            }
            Writer writer = new FileWriter(dataFile);
            writer.write(json);
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete() {
        dataFile.delete();
    }
}
