package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import Holders.ClickableViewHolder;

public abstract class RecyclerViewAdapter<VH extends ClickableViewHolder> extends RecyclerView.Adapter<VH> implements ClickableViewHolder.OnViewHolderClickListener {

    protected OnItemClickListener mOnItemClickListener;
    public Context mContext;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, int position, View v);
    }

    @Override
    public void onViewHolderClick(RecyclerView.ViewHolder vh, int position, View v) {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(vh, position, v);
    }


}
