package Frament;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a18855127160.gankio.R;

import Adapter.RecyclerViewAdapter;

/**
 * Created by 18855127160 on 2016/5/1.
 */
public abstract class BaseFragment extends Fragment {
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected RecyclerViewAdapter<?> recyclerViewAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter.OnItemClickListener mOnItemClickListener=new RecyclerViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(RecyclerView.ViewHolder viewHolder, int position, View v) {
            onRecyclerItemClick(viewHolder,position,v);
        }
    };
    private RecyclerView.OnScrollListener mScrollListener=new RecyclerView.OnScrollListener() {
        int lastVisibleItem;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == layoutManager.getItemCount()) {
                doInitData(true);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            lastVisibleItem =findLastVisibleItemPosition();
        }
    };

    public abstract int  findLastVisibleItemPosition();
    abstract void onRecyclerItemClick(final RecyclerView.ViewHolder viewHolder, final int position, View v) ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.android_layout,container,false);
        return root;
    }
    public abstract void setDefaultLayoutManager();
    public abstract void setAdapter();
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout=(SwipeRefreshLayout) view.findViewById(R.id.swipe);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        setAdapter();
        mRecyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemClickListener(mOnItemClickListener);
        setDefaultLayoutManager();
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(mScrollListener);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doInitData(false);
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        doInitData(false);
    }

    //获取网络数据
    public abstract void doInitData(boolean loadMore);
}
