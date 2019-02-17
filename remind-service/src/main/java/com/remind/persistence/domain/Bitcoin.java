package com.remind.persistence.domain;

import com.remind.persistence.BaseDomain;

public class Bitcoin extends BaseDomain {

    private String name;
    private String url ;
    private Integer state;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
