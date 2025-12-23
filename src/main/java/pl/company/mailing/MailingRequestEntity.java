package pl.company.mailing;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name="emails")
@Entity
public class MailingRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String message;
    @Enumerated(EnumType.STRING)
    private EmailStatus status;
    private int attemptCount;
    private String lastError;
    private LocalDateTime sentAt;
    @CreationTimestamp
    private  LocalDateTime createdAt;

    public MailingRequestEntity() {
    }
    public MailingRequestEntity(String email, String message) {
        this.status = EmailStatus.PENDING;
        this.attemptCount = 0;
        this.email = email;
        this.message = message;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(EmailStatus status) {
        this.status = status;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getAttemptCount() {
        return attemptCount;
    }
}
