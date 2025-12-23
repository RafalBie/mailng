package pl.company.mailing;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailingRequestService {
    private final MailingRequestRepository mailingRequestRepository;

    public MailingRequestService(MailingRequestRepository mailingRequestRepository) {
        this.mailingRequestRepository = mailingRequestRepository;
    }
    public MailingRequestEntity create (String email, String message){
        MailingRequestEntity entity = new MailingRequestEntity(email,message);
        return mailingRequestRepository.save(entity);
    }
    public List<MailingRequestEntity> getPendingEmails() {

        return mailingRequestRepository.findDistinctTopByStatusOrderByCreatedAtAsc(EmailStatus.PENDING);
    }

}


//zzrobic walidacje mail ,message,wywolywany w serwisie
// plan o wysylke maili asynchronicznie, co 5 min, dolozyc kolumny status pendiinng, failed.Scheduler wysyla maile
// nowe kolumna ze statusem do taabelki emails, (pending, failed, sent)