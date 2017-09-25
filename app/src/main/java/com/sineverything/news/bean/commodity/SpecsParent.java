package com.sineverything.news.bean.commodity;

import com.sineverything.news.bean.BaseBean.BaseBean;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/17.
 * email : huangjinping@hdfex.com
 */

public class SpecsParent extends BaseBean {

    private String specsId;
    private String specsName;

    private List<SpecsChild> specsPros;

    public List<SpecsChild> getSpecsPros() {
        return specsPros;
    }

    public void setSpecsPros(List<SpecsChild> specsPros) {
        this.specsPros = specsPros;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getSpecsId() {
        return specsId;
    }

    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }
}
