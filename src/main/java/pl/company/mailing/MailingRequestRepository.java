package pl.company.mailing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MailingRequestRepository extends JpaRepository<MailingRequestEntity, Long> {

    // select * from emails where status = ?
    List<MailingRequestEntity> findByStatus(EmailStatus status);

    @Query("select * from emails where status = 'FAILED' and attempt_count < 3")
    List<MailingRequestEntity> findFailedEmails();
}
