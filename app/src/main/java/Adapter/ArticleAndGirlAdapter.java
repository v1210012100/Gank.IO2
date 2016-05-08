package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a18855127160.gankio.FragmentType;
import com.example.a18855127160.gankio.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Holders.ArticleAndGirlViewHolder;
import Holders.ClickableViewHolder;
import Net.AllResult;

/**
 * Created by 18855127160 on 2016/5/2.
 */
public class ArticleAndGirlAdapter extends RecyclerViewAdapter<ClickableViewHolder>{
    private List<AllResult.Results> articleData =new ArrayList<>();
    private List<AllResult.Results> Girls =new ArrayList<>();
    private FragmentType fragmentType;
    public ArticleAndGirlAdapter(FragmentType type, Context context){
        fragmentType=type;
        mContext=context;
    }
    public void setArticleData(AllResult data){
        articleData.clear();
        articleData.addAll(data.getResults());
    }
    public void setMeizis(AllResult meizis){
        this.Girls.clear();
        this.Girls.addAll(meizis.getResults());
    }
    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = null;
        switch (fragmentType){
            case Android:
               item  = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.android_item_layout, parent, false);
                break;
            case Xiatuijian:
                item = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.grid_item_layout, parent, false);
                break;
            case tuozhanzhiyuan:
                item = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.grid_item_layout, parent, false);
                break;
            case Xiuxishipin:
                item = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.stagged_item_layout, parent, false);
        }

        return new ArticleAndGirlViewHolder(item,this);
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        //  Android ，瞎推荐， 拓展资源  都是图片，加描述。
        if(fragmentType!=FragmentType.Xiuxishipin) {
            ArticleAndGirlViewHolder mHolder = (ArticleAndGirlViewHolder) holder;
            AllResult.Results article = articleData.get(position);
            int girlSize=Girls.size();
            AllResult.Results mezhi = position<girlSize ?Girls.get(position) : Girls.get((int)Math.random()*girlSize);
            mHolder.time.setText(article.getCreatedAt().substring(0, 10));
            mHolder.desc.setText(article.getDesc());
            String url = mezhi.getUrl();
            // Trigger the download of the URL asynchronously into the image view.
            Picasso.with(mContext)
                    .load(url)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .fit()
                    .centerCrop()
                    .tag(mHolder.imageView.getContext())
                    .into(mHolder.imageView);
        }else{
            //休息视频，只有文字描述。

            ArticleAndGirlViewHolder mHolder = (ArticleAndGirlViewHolder) holder;
            AllResult.Results article = articleData.get(position);
            mHolder.itemView.setTag(article.getUrl());
            mHolder.desc.setText(article.getDesc());
        }
    }

    @Override
    public int getItemCount() {
        return articleData.size();
    }
}
