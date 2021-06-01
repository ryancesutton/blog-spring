package com.codeup.blog.models;




import javax.persistence.*;

@Entity
@Table(name = "chatNotifications" )
public class ChatNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long senderId;

    private String senderName;

    public ChatNotification() {
    }

    public ChatNotification(long id, long senderId, String senderName) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
    }

    public ChatNotification(long senderId, String senderName) {
        this.senderId = senderId;
        this.senderName = senderName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
