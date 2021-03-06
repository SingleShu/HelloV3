package com.abings.baby.ui.album;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abings.baby.R;
import com.hellobaby.library.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

public class PhotoPagerAdapter extends PagerAdapter {

  private List<WaterFallItem> paths = new ArrayList<>();
  private Context mContext;
  private LayoutInflater mLayoutInflater;


  public PhotoPagerAdapter(Context mContext, List<WaterFallItem> paths) {
    this.mContext = mContext;
    this.paths = paths;
    mLayoutInflater = LayoutInflater.from(mContext);
  }

//  public void setPhotoViewClickListener(PhotoViewClickListener listener){
//    this.listener = listener;
//  }

  @Override public Object instantiateItem(ViewGroup container, int position) {

    View itemView = mLayoutInflater.inflate(R.layout.item_preview, container, false);

    PhotoView imageView = (PhotoView) itemView.findViewById(R.id.iv_pager);

    final String url = paths.get(position).getUrl();
//    final Uri uri;
//    if (url.startsWith("http")) {
//      uri = Uri.parse(url);
//    } else {
//      uri = Uri.fromFile(new File(url));
//    }
    /**
    Glide.with(mContext)
            .load(url)
//            .placeholder(R.mipmap.default_error)
//            .error(R.mipmap.default_error)
            .crossFade()
            .into(imageView);
*/
    ImageLoader.load(mContext,url,imageView);//gif动态图
//    imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
//        @Override
//        public void onPhotoTap(View view, float v, float v1) {
//          if(listener != null){
//            listener.OnPhotoTapListener(view, v, v1);
//          }
//        }
//    });

    container.addView(itemView);

    return itemView;
  }


  @Override public int getCount() {
    return paths.size();
  }


  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }


  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override
  public int getItemPosition (Object object) { return POSITION_NONE; }

}