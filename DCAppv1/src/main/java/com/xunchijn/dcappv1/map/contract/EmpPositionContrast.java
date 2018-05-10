package com.xunchijn.dcappv1.map.contract;

import com.xunchijn.dcappv1.base.BaseView;
import com.xunchijn.dcappv1.common.module.UserInfo;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public interface EmpPositionContrast {
    interface Presenter {
        void getEmpPosition(String empSimid);
    }

    interface View extends BaseView<Presenter> {
        void showEmpPosition(UserInfo userInfo);
    }

}
