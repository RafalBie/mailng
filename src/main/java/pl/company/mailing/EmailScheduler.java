package pl.company.mailing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
public class EmailScheduler {
    private final MailingRequestService mailingRequestService;

    public EmailScheduler(MailingRequestService mailingRequestService) {
        this.mailingRequestService = mailingRequestService;
    }
    @Scheduled(cron = "*/2 * * * * ?") // todo na następny raz zrobic taki sam scheduler tylko do statusów FAILED, max 3 próby wysyłki
    // todo w sqlce where status = FAILED and attempt_count < 3
//    public  void sendEmail() {
//        log.info("Sending email...");
//    }
    @Transactional
    public void sendEmail() {
        var pendingEmails = mailingRequestService.getPendingEmails();

        if (pendingEmails.isEmpty()) {
            log.info("No pending emails");
            return;
        }

        log.info("Found {} pending emails", pendingEmails.size());
        pendingEmails.forEach(email -> {
            log.info("Pending id={}, to={}", email.getId(), email.getEmail());

            try {
                // todo send email
                if (email.getEmail().equals("mm23@423wp.pl")) {
                    throw new Exception("Something goes wrong");
                }
                email.setStatus(EmailStatus.SENT);
                email.setAttemptCount(1);
                email.setSentAt(LocalDateTime.now());
            } catch (Exception e) {
                email.setStatus(EmailStatus.FAILED);
                int attemptCount = email.getAttemptCount();
                email.setAttemptCount(attemptCount + 1);
                email.setLastError(e.getMessage());
            }

        });
    }
}
// 325432543@wp.pl -> SENT
// mm23@423wp.pl -> FAILED x2



// pobrac z bazy maila ze statsuem pending, zrobic try catch, w try zmienic na sent, w catch failed.
