package pl.company.mailing;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailingStatsService {
    private final MailingRequestRepository repository;

    public MailingStatsService(MailingRequestRepository repository) {
        this.repository = repository;
    }
    public List<EmailStatusCountDto> getByStatus() {
        return repository.countByStatusRaw().stream()
        .map(row -> new EmailStatusCountDto(
                row[0] != null ? row[0].toString() : null,
                row[1] != null ? ((Number) row[1]).longValue() : 0L

        ) )
                .toList();
    }
    public EmailSummaryDto getSummary() {
        Object[] row = repository.summaryRaw();
        Long failed = row[0] != null ? ((Number) row[0]).longValue() : 0L;
        Long sent = row[1] != null ? ((Number) row[1]).longValue() : 0L;

        return new EmailSummaryDto(failed, sent);


    }
}
