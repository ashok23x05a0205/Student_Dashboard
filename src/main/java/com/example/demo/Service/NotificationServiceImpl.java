package com.example.demo.Service;

import com.example.demo.Entity.Notification;
import com.example.demo.Repo.NotificationRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repo;

    public NotificationServiceImpl(NotificationRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Notification> getAllNotifications() {
        return repo.findAll();
    }

    @Override
    public Notification getNotificationById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return repo.save(notification);
    }

    @Override
    public void deleteNotification(Long id) {
        repo.deleteById(id);
    }
}