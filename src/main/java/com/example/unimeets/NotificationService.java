package com.example.unimeets;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private List<Notification> notifications;

    public NotificationService() {
        this.notifications = new ArrayList<>();
    }

    public void sendNotification(String message, User recipient) {
        Notification notification = new Notification(message, recipient);
        notifications.add(notification);
        // For simplicity, we're just printing the notification
        System.out.println("Notification sent to " + recipient.getName() + ": " + message);
    }

    public List<Notification> getUserNotifications(User user) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getRecipient().equals(user)) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }

    public void markAsRead(Notification notification) {
        notification.setRead(true);
    }

    public void markAllAsRead(User user) {
        for (Notification notification : notifications) {
            if (notification.getRecipient().equals(user)) {
                notification.setRead(true);
            }
        }
    }

    public void displayNotifications(User user) {
        List<Notification> userNotifications = getUserNotifications(user);
        System.out.println("Notifications for " + user.getName() + ":");
        for (Notification notification : userNotifications) {
            System.out.println(notification);
        }
    }
}

