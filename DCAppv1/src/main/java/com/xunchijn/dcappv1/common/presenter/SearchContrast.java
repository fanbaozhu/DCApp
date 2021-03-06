package com.xunchijn.dcappv1.common.presenter;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.common.module.SearchItem;

import java.util.List;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public interface SearchContrast {
    interface Presenter{
        void search (String name);
    }

    interface View extends BaseView<Presenter>{
        void searchSuccess(List<SearchItem> list);
    }
}
