package rx.hxt.rxdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */

public class GankBeautyResult {
    public boolean error;
    public @SerializedName("results") List<GankBeauty> beauties;
}
