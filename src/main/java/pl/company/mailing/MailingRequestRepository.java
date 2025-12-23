package pl.company.mailing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MailingRequestRepository extends JpaRepository<MailingRequestEntity, Long> {

    // select * from emails where status = ?
    List<MailingRequestEntity> findByStatus(EmailStatus status);
}
