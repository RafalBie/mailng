package pl.company.mailing;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MailingRequestService {
    private final MailingRequestRepository mailingRequestRepository;

    public MailingRequestService(MailingRequestRepository mailingRequestRepository) {
        this.mailingRequestRepository = mailingRequestRepository;
    }

    @Transactional
    public MailingRequestEntity create(String email, String message){
        MailingRequestEntity entity = new MailingRequestEntity(email,message);
        return mailingRequestRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<MailingRequestEntity> getPendingEmails() {

        return mailingRequestRepository.findByStatus(EmailStatus.PENDING);
    }

}


//zzrobic walidacje mail ,message,wywolywany w serwisie
// plan o wysylke maili asynchronicznie, co 5 min, dolozyc kolumny status pendiinng, failed.Scheduler wysyla maile
// nowe kolumna ze statusem do taabelki emails, (pending, failed, sent)