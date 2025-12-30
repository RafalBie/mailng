package pl.company.mailing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class FailedEmailRetryScheduler {
    private final MailingRequestService mailingRequestService;

    public FailedEmailRetryScheduler(MailingRequestService mailingRequestService) {
        this.mailingRequestService = mailingRequestService;
    }
    @Scheduled(cron = "*/30 * * * * ?")
    @Transactional
    public void retryFailedEmails() {
        List<MailingRequestEntity> failedToRetry =  mailingRequestService.getFailedEmailsToRetry();
        if (failedToRetry.isEmpty()){
            log.info("No failing emails found");
            return;
        }
        log.info("Failed emails found: {}", failedToRetry.size());
        failedToRetry.forEach(email -> {
            log.info("Retry FAILED id={}, to={}, attempts={}", email.getId(),email.getEmail(), email.getAttemptCount());
            try {
                if(email.getEmail().equals("mm23@423wp.pl")){
                    throw new Exception("Something go wrong");
                }
                email.setStatus(EmailStatus.SENT);
                email.setSentAt(LocalDateTime.now());
                email.setLastError(null);
                email.setAttemptCount(email.getAttemptCount() + 1);
            } catch (Exception e) {
                email.setAttemptCount(email.getAttemptCount() + 1);
                email.setLastError(e.getMessage());
                if (email.getAttemptCount() >= 3) {
                    log.warn("Giving up on email id={} to={} after {} attempts. Last error={}",
                            email.getId(), email.getEmail(), email.getAttemptCount(), e.getMessage());
                }
                else {
                    email.setStatus(EmailStatus.FAILED);
                }
            }
        });
    }

}
