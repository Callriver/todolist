package com.rxsoft.orm.model;

import java.io.Serializable;
import java.sql.Timestamp;


public class Remind implements Serializable {
    private String guid;

    private Integer taskId;

    private Integer listId;

    private String userId;

    private Timestamp remindDate;

    private static final long serialVersionUID = 1L;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

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

    public Timestamp getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Timestamp remindDate) {
        this.remindDate = remindDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guid=").append(guid);
        sb.append(", taskId=").append(taskId);
        sb.append(", listId=").append(listId);
        sb.append(", userId=").append(userId);
        sb.append(", remindDate=").append(remindDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}