package com.abings.baby.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abings.baby.R;
import com.abings.baby.ui.album.AlbumActivity;
import com.abings.baby.ui.main.MainActivity;
import com.abings.baby.ui.onlytext.TextActivity;
import com.abings.baby.ui.publishvideo.VideoPlayActivity;
import com.hellobaby.library.Const;
import com.hellobaby.library.data.model.AlbumModel;
import com.hellobaby.library.utils.ImageLoader;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private boolean isFirst = true;
    private List<AlbumModel> albumModels;
    private Activity mContext;

    private int item_normal_height;
    private int item_max_height;
    private float item_normal_alpha;
    private float item_max_alpha;
    private float item_normal_font_size;
    private float item_max_font_size;
    private float item_normal_iv_size;
    private float item_max_iv_size;

    public MyAdapter(Activity context, List<AlbumModel> albumModels) {
        this.mContext = context;
        this.albumModels = albumModels;
        item_max_height = (int) context.getResources().getDimension(R.dimen.item_max_height);
        item_normal_height = (int) context.getResources().getDimension(R.dimen.item_normal_height);
        item_normal_font_size = context.getResources().getDimension(R.dimen.item_normal_font_size);
        item_max_font_size = context.getResources().getDimension(R.dimen.item_max_font_size);
        item_normal_iv_size = context.getResources().getDimension(R.dimen.item_normal_font_size);
        item_max_iv_size = context.getResources().getDimension(R.dimen.item_max_font_size);
        item_normal_alpha = context.getResources().getFraction(R.fraction.item_normal_mask_alpha, 1, 1);
        item_max_alpha = context.getResources().getFraction(R.fraction.item_max_mask_alpha, 1, 1);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wall, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.imageIcon = (ImageView) view.findViewById(R.id.iv_icon);
        viewHolder.imageView = (ImageView) view.findViewById(R.id.image);
        viewHolder.mark = view.findViewById(R.id.mark);
        viewHolder.text = (TextView) view.findViewById(R.id.text);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final AlbumModel albumModel = albumModels.get(position);
        final Bundle b = new Bundle();
        b.putSerializable("AlbumModel", albumModel);
        if (AlbumModel.TYPE_ALBUM.equals(albumModel.getType())) {
            holder.text.setText(albumModel.getTitle());
            ImageLoader.loadImg(holder.imageView.getContext(), albumModel.getImageUrlAbs(), holder.imageView);
            holder.imageIcon.setImageResource(R.drawable.mainlist_tagalubm);
            holder.mark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AlbumActivity.class);
                    intent.putExtras(b);
                    mContext.startActivityForResult(intent, MainActivity.BabyFragmentRequestCode);
                }
            });
        } else if (AlbumModel.TYPE_TEXT.equals(albumModel.getType())) {
            //色块色块
            holder.text.setText(albumModel.getTitle());
            ImageLoader.loadImg(holder.imageView.getContext(), null, holder.imageView);
            holder.imageIcon.setImageResource(R.drawable.mainlist_tagtext);
            holder.mark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TextActivity.class);
                    intent.putExtras(b);
                    mContext.startActivityForResult(intent, MainActivity.BabyFragmentRequestCode);
                }
            });
        } else if (AlbumModel.TYPE_VIDEO.equals(albumModel.getType())) {
            holder.text.setText(albumModel.getContent());
            ImageLoader.loadImg(holder.imageView.getContext(), albumModel.getVideoImageUrlAbs(), holder.imageView);
            holder.imageIcon.setImageResource(R.drawable.mainlist_tagvideo);
            holder.mark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = VideoPlayActivity.getIntentVideoPlayNet(Const.videoPath, albumModel.getVideoUrlAbs(), albumModel.getVideoUrl(), VideoPlayActivity.MORE_TYPE_ALBUM);
                    intent.putExtras(b);
                    intent.setClass(mContext, VideoPlayActivity.class);
                    mContext.startActivityForResult(intent, MainActivity.BabyFragmentRequestCode);
                }
            });
        }
        if (isFirst && position == 0) {
            isFirst = false;
            holder.mark.setAlpha(item_max_alpha);
            holder.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, item_max_font_size);
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, item_max_height));
        } else {
            holder.mark.setAlpha(item_normal_alpha);
            holder.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, item_normal_font_size);
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, item_normal_height));
        }
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.imageIcon.setAlpha(0f);
    }

    @Override
    public int getItemCount() {
        return albumModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public View mark;
        public TextView text;
        public ImageView imageIcon;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
