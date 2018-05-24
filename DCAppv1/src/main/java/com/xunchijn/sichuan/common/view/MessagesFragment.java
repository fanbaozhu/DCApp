package com.xunchijn.sichuan.common.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.adapter.MessagesAdapter;
import com.xunchijn.sichuan.common.module.MessageItem;
import com.xunchijn.sichuan.common.presenter.MessagesContrast;

import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment implements MessagesContrast.View {
    private MessagesContrast.Presenter mPresenter;
    private MessagesAdapter mMessagesAdapter;
    private List<MessageItem> mList;
    private int pageIndex = 0;
    private int pageCount = 20;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mList = new ArrayList<>();
        mMessagesAdapter = new MessagesAdapter(mList);
        recyclerView.setAdapter(mMessagesAdapter);
        initData();
    }

    private void initData() {
        if (mPresenter != null) {
            mPresenter.getMessages(pageIndex, pageCount);
        }
    }

    @Override
    public void showMessages(List<MessageItem> list) {
        if (list.size() == 20) {
            pageIndex++;
        }
        mList.addAll(list);
        mMessagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MessagesContrast.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}