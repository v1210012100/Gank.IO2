package Holders;

/**
 * Created by 18855127160 on 2016/5/1.
 */

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ClickableViewHolder extends ViewHolder implements OnClickListener {
    private OnViewHolderClickListener mOnViewHolderClickListener;

    //  item 点击事件回调接口
    public interface OnViewHolderClickListener{
        void onViewHolderClick(ViewHolder vh, int position, View v);
    }

    /**
     *
     * @param itemView
     * @param onViewHolderClickListener   实现了点击事件接口的对象，点击item 会回调它的onClick 方法。
     */
    public ClickableViewHolder(View itemView, OnViewHolderClickListener onViewHolderClickListener) {
        super(itemView);
         mOnViewHolderClickListener = onViewHolderClickListener;
    }

    @Override
    public void onClick(View v) {
        if (mOnViewHolderClickListener != null) {
            mOnViewHolderClickListener.onViewHolderClick(this, getLayoutPosition(), v);
        }
    }


}
