package com.cms.admin.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class NotificationToPush {
    private String title;
    private String message;
    private String topic;
    private String token;
    private Map<String,String> data;
}
