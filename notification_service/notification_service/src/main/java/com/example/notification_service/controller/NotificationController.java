package com.example.notification_service.controller;

import com.example.notification_service.model.Notification;
import com.example.notification_service.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepo notificationRepository;

    // Get all notifications
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Get a notification by ID
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        return notification.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new notification
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }

    // Update a notification (mark as read, change message, etc.)
    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification updatedNotification) {
        return notificationRepository.findById(id).map(notification -> {
            notification.setMessage(updatedNotification.getMessage());
            notification.setType(updatedNotification.getType());
            notification.setIsRead(updatedNotification.getIsRead());
            notificationRepository.save(notification);
            return ResponseEntity.ok(notification);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a notification
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNotification(@PathVariable Long id) {
        return notificationRepository.findById(id)
                .map(notification -> {
                    notificationRepository.delete(notification);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


    // Get notifications by userId
    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationsByUserId(@PathVariable Long userId) {
        return notificationRepository.findByUserId(userId);
    }
}
