package com.example.order_service.serviceImp;

import com.example.order_service.model.Notification;
import com.example.order_service.repository.NotificationRepo;
import com.example.order_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private final NotificationRepo repo;

    public NotificationServiceImpl(NotificationRepo repo) {
        this.repo = repo;
    }

    @Override
    public Notification create(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        notification.setIsRead(false);
        return repo.save(notification);
    }

    @Override
    public Notification getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id " + id));
    }

    @Override
    public List<Notification> getAll() {
        return repo.findAll();
    }

    @Override
    public Notification update(Long id, Notification notification) {
        Notification existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id " + id));

        existing.setUserId(notification.getUserId());
        existing.setMessage(notification.getMessage());
        existing.setType(notification.getType());
        existing.setIsRead(notification.getIsRead());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        Notification notification = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id " + id));

        repo.delete(notification);
    }
}
