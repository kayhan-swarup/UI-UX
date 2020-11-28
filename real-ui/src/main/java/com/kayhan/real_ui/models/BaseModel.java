package com.kayhan.real_ui.models;import androidx.annotation.NonNull;public class BaseModel {    protected String name;    protected String common_name;    protected String id;    protected String category;    public BaseModel() {        super();    }    public BaseModel(String name, String common_name) {        super();        this.name = name;        this.common_name = common_name;    }    public BaseModel(String name, String common_name, String id) {        super();        this.name = name;        this.common_name = common_name;        this.id = id;    }    public BaseModel(String name, String common_name, String id, String category) {        super();        this.name = name;        this.common_name = common_name;        this.id = id;        this.category = category;    }    public String getName() {        return name;    }    public void setName(String name) {        this.name = name;    }    public String getCommon_name() {        return common_name;    }    public void setCommon_name(String common_name) {        this.common_name = common_name;    }    public String getId() {        return id;    }    public void setId(String id) {        this.id = id;    }    public String getCategory() {        return category;    }    public void setCategory(String category) {        this.category = category;    }    @NonNull    @Override    public String toString() {        return name;    }}