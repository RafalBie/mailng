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

}
// index na baze danych, nalozyc index na kolumne, indefikator w bazie danych, na postgresie
// scrypt do do bazy z milionem recordow, zeby zapchac baze, sprawdzic wydajnosc
//evenlisenrer, aplication ready event
// o projekcji sql hibernet