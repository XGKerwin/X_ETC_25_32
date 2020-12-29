package com.example.x_etc_25_32.bean;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 16:05
 */
public class DTGH {

    /**
     *             "id": "1",
     *             "name": "北京地铁1号线（M1）线路图",
     *             "site": [
     *                 "天安门站",
     *                 "苹果园",
     *                 "将军陵",
     *                 "故园"
     *             ]
     */

    private String id,name;
    private List<String> site;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSite() {
        return site;
    }

    public void setSite(List<String> site) {
        this.site = site;
    }
}
