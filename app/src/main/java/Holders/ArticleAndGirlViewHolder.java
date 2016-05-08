package Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a18855127160.gankio.R;

/**
 * Created by 18855127160 on 2016/5/2.
 */
public class ArticleAndGirlViewHolder extends ClickableViewHolder {
    public TextView desc;
    public TextView time;
    public ImageView imageView;

    /**
     * @param itemView
     * @param onViewHolderClickListener 实现了点击事件接口的对象，点击item 会回调它的onClick 方法。
     */
    public ArticleAndGirlViewHolder(View itemView, OnViewHolderClickListener onViewHolderClickListener) {
        super(itemView, onViewHolderClickListener);
        desc= (TextView) itemView.findViewById(R.id.Desc);
        time= (TextView) itemView.findViewById(R.id.time);
        imageView= (ImageView) itemView.findViewById(R.id.meizi);
        itemView.setOnClickListener(this);
    }
}
