package com.example.order_service.service;

import com.example.order_service.model.Notification;
import java.util.List;

public interface NotificationService {
    Notification create(Notification notification);

    Notification getById(Long id);

    List<Notification> getAll();

    Notification update(Long id, Notification notification);

    void delete(Long id);
}
