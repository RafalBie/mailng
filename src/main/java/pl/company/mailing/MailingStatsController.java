package pl.company.mailing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emails/stats")
public class MailingStatsController {
    private final MailingStatsService statsService;

    public MailingStatsController(MailingStatsService statsService) {
        this.statsService = statsService;
    }
    @GetMapping("/by-status")
    public List<EmailStatusCountDto>byStaus(){
        return statsService.getByStatus();
    }
    @GetMapping("/summary")
    public EmailSummaryDto summary() {
        return statsService.getSummary();
    }
}
