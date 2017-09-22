package com.example.administrator.wangye2017_9_22.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.wangye2017_9_22.R;
import com.example.administrator.wangye2017_9_22.bean.Bean;
import com.example.administrator.wangye2017_9_22.utils.ImageLoadUtils;
import com.example.administrator.wangye2017_9_22.utils.RoundImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 王野
 * 适配器
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {



    private Context mContext;
    private LayoutInflater mInflater;
    private List<Bean.OthersBean> mList;

    public MyAdapter(Context context, List<Bean.OthersBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder holder = new ItemViewHolder(mInflater.inflate(R.layout.view_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        //圆角加载效果
        ImageLoadUtils.INSTANCE.loadRoundedImageView(holder.itemImage, mList.get(position).thumbnail);
        holder.itemTv.setText(mList.get(position).description);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_image)
        RoundImageView itemImage;
        @Bind(R.id.item_tv)
        TextView itemTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}