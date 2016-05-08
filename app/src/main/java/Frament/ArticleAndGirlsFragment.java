package Frament;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a18855127160.gankio.DetailActivity;
import com.example.a18855127160.gankio.FragmentType;
import com.example.a18855127160.gankio.R;

import java.util.Arrays;

import Adapter.ArticleAndGirlAdapter;
import Adapter.RecyclerViewAdapter;
import Holders.ArticleAndGirlViewHolder;
import Net.AllResult;
import Net.DefaultRetrofit;
import Net.GankUrl;
import Net.ICallBack;

/**
 * Created by 18855127160 on 2016/5/2.
 */
public class ArticleAndGirlsFragment extends BaseFragment {
    private AllResult articles;
    private AllResult Girls;
    private int contentQuantity =20;
    private FragmentType mType;
    private String keyType;
    private RecyclerViewAdapter mAdapter;
    private Activity mActivity;
    public ArticleAndGirlsFragment(FragmentType type){
        mType=type;
        switch (type){
            case Android:
                keyType=GankUrl.FLAG_Android;
                break;
            case Xiatuijian:
                keyType=GankUrl.FLAG_Recommend;
                break;
            case tuozhanzhiyuan:
                keyType=GankUrl.FLAG_Expand;
                break;
            case Xiuxishipin:
                keyType=GankUrl.FLAG_Video;
                break;
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter=new ArticleAndGirlAdapter(mType,getActivity());
    }

    ICallBack<AllResult> iCallBack =new ICallBack<AllResult>() {
        @Override
        public void onSuccess(String flag, String key, AllResult result) {
            if(!flag.equals(GankUrl.FLAG_GIRLS)){
                articles =result;
                ((ArticleAndGirlAdapter)mAdapter).setArticleData(articles);
                DefaultRetrofit.getAllResult(GankUrl.FLAG_GIRLS,contentQuantity,1,iCallBack);

            }else {
                Girls=result;
                ((ArticleAndGirlAdapter)mAdapter).setMeizis(Girls);
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }

        @Override
        public void onFailure(String flag, String key, String why) {
            Toast.makeText(mActivity,why,Toast.LENGTH_SHORT).show();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }
    @Override
    public int findLastVisibleItemPosition() {
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        int lastVisibleItemPosition=0;

        switch (mType) {
            case Android:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case Xiatuijian:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case tuozhanzhiyuan:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case Xiuxishipin:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                int [] lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(lastPositions);
                Arrays.sort(lastPositions);
                lastVisibleItemPosition=lastPositions[1];
                break;
    }
        return lastVisibleItemPosition;
    }

    @Override
    void onRecyclerItemClick(RecyclerView.ViewHolder viewHolder, int position, View v) {
        Intent intent =new Intent(getActivity(),DetailActivity.class);
        intent.putExtra("girls",Girls.getResults().get(position));
        intent.putExtra("articles", articles.getResults().get(position));
        switch (mType){
            case Android:
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                ((ArticleAndGirlViewHolder)viewHolder).imageView, getString(R.string.transition_img));
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                break;
            case Xiatuijian:
                startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            case tuozhanzhiyuan:
                startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            case Xiuxishipin:
                Uri content_url=  Uri.parse((String) viewHolder.itemView.getTag()) ;
                Intent ie = new Intent(Intent.ACTION_VIEW,content_url);
                startActivity(ie);

        }

    }

    @Override
    public void setDefaultLayoutManager() {
        switch (mType){
            case Android:
                layoutManager=new LinearLayoutManager(getActivity());
                break;
            case Xiatuijian:
                layoutManager=new GridLayoutManager(getActivity(),2);
                break;
            case tuozhanzhiyuan:
                layoutManager=new GridLayoutManager(getActivity(),2);
                break;
            case Xiuxishipin:
                layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                break;
        }
    }

    @Override
    public void setAdapter() {
        recyclerViewAdapter=mAdapter;

    }

    @Override
    public void doInitData(boolean loadMore) {
        mSwipeRefreshLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
       mSwipeRefreshLayout.setRefreshing(true);
        if(loadMore) contentQuantity = contentQuantity +10;
        DefaultRetrofit.getAllResult(keyType, contentQuantity,1,iCallBack);

    }
}
