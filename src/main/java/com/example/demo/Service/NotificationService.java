package com.example.demo.Service;

import com.example.demo.Entity.Notification;
import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotifications();
    Notification getNotificationById(Long id);
    Notification saveNotification(Notification notification);
    void deleteNotification(Long id);
}