package com.xunchijn.tongshan.common.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.xunchijn.tongshan.R;
import com.xunchijn.tongshan.util.TitleFragment;
import com.xunchijn.tongshan.adapter.SearchAdapter;
import com.xunchijn.tongshan.common.presenter.SearchContrast;
import com.xunchijn.tongshan.common.module.SearchItem;
import com.xunchijn.tongshan.common.presenter.SearchPresenter;

import java.util.List;

/**
 * Author：Fan BaoZhu
 * Time:2018/5/9   下午5:12
 * Description:通用搜索页面
 **/
public class SearchActivity extends AppCompatActivity implements SearchContrast.View{
    private SearchContrast.Presenter mPresenter;
private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_common_search);
        initTitle();
        mPresenter = new SearchPresenter(this);
        mRecyclerView = findViewById(R.id.recycler_view_search);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initTitle() {
        TitleFragment fragment = TitleFragment.newInstance("搜索页面", true, false);
        fragment.setShowEditSearch(true);
        fragment.setTextWatcher(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String name = editable.toString();
                Log.d("search", "afterTextChanged: " + name);
                if (mPresenter != null) {
                    mPresenter.search(name);
                }
            }
        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, fragment)
                .show(fragment).commit();

    }


    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SearchContrast.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void searchSuccess(List<SearchItem> list) {
        SearchAdapter adapter = new SearchAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }
}
