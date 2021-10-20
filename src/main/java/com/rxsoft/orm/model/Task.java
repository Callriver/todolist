package com.rxsoft.orm.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private Integer taskId;

    private Integer listId;

    private String userId;

    private String taskName;

    private Integer taskPriority;

    private Integer taskState;

    private Date taskDate;

    private Date taskRemindTime;

    private Integer taskRepetitionPeriod;

    private Integer taskCustDate;

    private String taskNote;

    private String taskAttachment;

    private static final long serialVersionUID = 1L;

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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public Integer getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Integer taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Integer getTaskState() {
        return taskState;
    }

    public void setTaskState(Integer taskState) {
        this.taskState = taskState;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Date getTaskRemindTime() {
        return taskRemindTime;
    }

    public void setTaskRemindTime(Date taskRemindTime) {
        this.taskRemindTime = taskRemindTime;
    }

    public Integer getTaskRepetitionPeriod() {
        return taskRepetitionPeriod;
    }

    public void setTaskRepetitionPeriod(Integer taskRepetitionPeriod) {
        this.taskRepetitionPeriod = taskRepetitionPeriod;
    }

    public Integer getTaskCustDate() {
        return taskCustDate;
    }

    public void setTaskCustDate(Integer taskCustDate) {
        this.taskCustDate = taskCustDate;
    }

    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote == null ? null : taskNote.trim();
    }

    public String getTaskAttachment() {
        return taskAttachment;
    }

    public void setTaskAttachment(String taskAttachment) {
        this.taskAttachment = taskAttachment == null ? null : taskAttachment.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskId=").append(taskId);
        sb.append(", listId=").append(listId);
        sb.append(", userId=").append(userId);
        sb.append(", taskName=").append(taskName);
        sb.append(", taskPriority=").append(taskPriority);
        sb.append(", taskState=").append(taskState);
        sb.append(", taskDate=").append(taskDate);
        sb.append(", taskRemindTime=").append(taskRemindTime);
        sb.append(", taskRepetitionPeriod=").append(taskRepetitionPeriod);
        sb.append(", taskCustDate=").append(taskCustDate);
        sb.append(", taskNote=").append(taskNote);
        sb.append(", taskAttachment=").append(taskAttachment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}