package com.example.administrator.wangye2017_9_22.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wangye2017_9_22.R;
import com.example.administrator.wangye2017_9_22.utils.ImageLoadUtils;
import com.example.administrator.wangye2017_9_22.utils.RoundImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * 王野
 * 适配器
 */
public class MyAdapterGride extends RecyclerView.Adapter<MyAdapterGride.ItemViewHolder> {



    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mList;

    public MyAdapterGride(Context context, List<String> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder holder = new ItemViewHolder(mInflater.inflate(R.layout.view_grideitem, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        //圆角加载效果
        ImageLoadUtils.INSTANCE.loadRoundedImageView(holder.itemGrideimage, mList.get(position));
        if (onItemClickListener != null) {
            holder.itemGrideimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_grideimage)
        RoundImageView itemGrideimage;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}