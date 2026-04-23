package com.example.notification_service.serviceImp;

import com.example.notification_service.model.Notification;
import com.example.notification_service.repository.NotificationRepo;
import com.example.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {

    private final NotificationRepo repo;

    @Override
    public Notification create(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        notification.setIsRead(false);
        return repo.save(notification);
    }

    @Override
    public Notification getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    @Override
    public List<Notification> getAll() {
        return repo.findAll();
    }

    @Override
    public Notification update(Long id, Notification notification) {
        Notification existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        existing.setUserId(notification.getUserId());
        existing.setMessage(notification.getMessage());
        existing.setType(notification.getType());
        existing.setIsRead(notification.getIsRead());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        Notification notification = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        repo.delete(notification);
    }
}
