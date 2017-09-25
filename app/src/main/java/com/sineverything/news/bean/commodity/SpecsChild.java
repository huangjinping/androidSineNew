package com.sineverything.news.bean.commodity;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/9/17.
 * email : huangjinping@hdfex.com
 */

public class SpecsChild extends BaseBean {
    private String specsProId;
    private String specsProName;


    public String getSpecsProId() {
        return specsProId;
    }

    public void setSpecsProId(String specsProId) {
        this.specsProId = specsProId;
    }

    public String getSpecsProName() {
        return specsProName;
    }

    public void setSpecsProName(String specsProName) {
        this.specsProName = specsProName;
    }

    @Override
    public String toString() {
        return "SpecsChild{" +
                "specsProId='" + specsProId + '\'' +
                ", specsProName='" + specsProName + '\'' +
                '}';
    }
}
