package pl.company.mailing;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailingStatsService {
    private final MailingRequestRepository repository;

    public MailingStatsService(MailingRequestRepository repository) {
        this.repository = repository;
    }
    public List<MailStatistic> getByStatus() {
        return repository.countByStatusRaw();
    }

}
// index na baze danych, nalozyc index na kolumne, indefikator w bazie danych, na postgresie
// scrypt do do bazy z milionem recordow, zeby zapchac baze, sprawdzic wydajnosc
//evenlisenrer, aplication ready event
// o projekcji sql hibernet