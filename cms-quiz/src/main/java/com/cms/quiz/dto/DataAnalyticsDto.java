package com.cms.quiz.dto;

import java.time.LocalDateTime;

public class DataAnalyticsDto {
    private int channel_id;
    private String userId;
    private int usersRegistered;
    private String winnerId;
    private String mostAnsQ;
    private LocalDateTime startTime;

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUsersRegistered() {
        return usersRegistered;
    }

    public void setUsersRegistered(int usersRegistered) {
        this.usersRegistered = usersRegistered;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }

    public String getMostAnsQ() {
        return mostAnsQ;
    }

    public void setMostAnsQ(String mostAnsQ) {
        this.mostAnsQ = mostAnsQ;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
