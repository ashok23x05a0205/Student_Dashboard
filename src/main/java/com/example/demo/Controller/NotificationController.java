package com.example.demo.Controller;

import com.example.demo.Entity.Notification;
import com.example.demo.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping
    public String viewAll(Model model) {
        List<Notification> notifications = service.getAllNotifications();
        model.addAttribute("notifications", notifications);
        return "notifications";
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long id, Model model) {
        Notification notification = (id != null) ? service.getNotificationById(id) : new Notification();
        model.addAttribute("notification", notification);
        return "notification_form";
    }

    @PostMapping("/save")
    public String saveNotification(@ModelAttribute Notification notification) {
        if (notification.getDate() == null) {
            notification.setDate(LocalDate.now());
        }
        service.saveNotification(notification);
        return "redirect:/notifications";
    }

    @GetMapping("/delete")
    public String deleteNotification(@RequestParam Long id) {
        service.deleteNotification(id);
        return "redirect:/notifications";
    }
}