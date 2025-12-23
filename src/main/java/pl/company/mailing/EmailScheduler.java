package pl.company.mailing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class EmailScheduler {
    private final MailingRequestService mailingRequestService;

    public EmailScheduler(MailingRequestService mailingRequestService) {
        this.mailingRequestService = mailingRequestService;
    }
    @Scheduled(cron = "*/2 * * * * ?")
//    public  void sendEmail() {
//        log.info("Sending email...");
//    }
    public void sendEmail() {
        var pending = mailingRequestService.getPendingEmails();

        if (pending.isEmpty()) {
            log.info("No pending emails");
            return;
        }

        log.info("Found {} pending emails", pending.size());
        pending.forEach(e -> log.info("Pending id={}, to={}", e.getId(), e.getEmail()));
    }
}

// pobrac z bazy maila ze statsuem pending, zrobic try catch, w try zmienic na sent, w catch failed.
