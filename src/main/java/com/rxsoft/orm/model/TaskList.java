package com.rxsoft.orm.model;

import java.io.Serializable;

public class TaskList implements Serializable {
    private Integer listId;

    private String userId;

    private String listName;

    private Integer listPriority;

    private static final long serialVersionUID = 1L;

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName == null ? null : listName.trim();
    }

    public Integer getListPriority() {
        return listPriority;
    }

    public void setListPriority(Integer listPriority) {
        this.listPriority = listPriority;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", listId=").append(listId);
        sb.append(", userId=").append(userId);
        sb.append(", listName=").append(listName);
        sb.append(", listPriority=").append(listPriority);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}