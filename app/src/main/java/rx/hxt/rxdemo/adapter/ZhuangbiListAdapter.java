package rx.hxt.rxdemo.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.hxt.rxdemo.R;
import rx.hxt.rxdemo.model.ZhuangbiImage;

/**
 * Created by Administrator on 2016/12/22.
 */

public class ZhuangbiListAdapter extends RecyclerView.Adapter {
    List<ZhuangbiImage> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ZhuangbiImage zhuangbiImage = images.get(position);
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        Glide.with(holder.itemView.getContext()).load(zhuangbiImage.image_url).into(debounceViewHolder.imageTv);
        debounceViewHolder.descriptionTv.setText(zhuangbiImage.description);
    }

    @Override
    public int getItemCount() {
        return null == images ?0:images.size();
    }

    public void setImages(List<ZhuangbiImage> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.imageIv)
        ImageView imageTv;
        @Bind(R.id.descriptionTv)
        TextView descriptionTv;

        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
