package com.example.demo.templates.metaData;

import cn.hutool.core.util.StrUtil;

public class TableColumn {
    private String code;

    private String attrName;

    private String type;

    private String comment;

    public String getAttrName() {
        this.attrName = StrUtil.toCamelCase(code);
        return attrName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
