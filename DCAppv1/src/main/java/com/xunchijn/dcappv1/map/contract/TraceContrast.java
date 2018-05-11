package com.xunchijn.dcappv1.map.contract;

import com.xunchijn.dcappv1.base.BaseView;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public interface TraceContrast {

    interface Presenter{
        //获取用户轨迹
        void getUserTrace(String userAccount);


    }

    interface View extends BaseView<Presenter>{

    }
}
