package pl.company.mailing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MailingRequestRepository extends JpaRepository<MailingRequestEntity, Long> {

    // select * from emails where status = ?
    List<MailingRequestEntity> findByStatus(EmailStatus status);

    @Query(value = "select * from emails where status = 'FAILED' and attempt_count < 3",nativeQuery = true)
    List<MailingRequestEntity> findFailedEmails();
    @Query(value = "select status as status, count(*) as count from emails group by status", nativeQuery = true)
    List<Object[]> countByStatusRaw();
    @Query (value = """
        select
            sum(case when status = 'FAILED' then 1 else 0 end) as failed,
            sum(case when status = 'SENT' then 1 else 0 end) as sent
        from emails
        """, nativeQuery = true)
    Object[]summaryRaw();

}
// to do przygotowac endpoit zwroci dane z w/w sql
//select status, count(*) from emails  group by status;
// select sum(case when status = 'FAILED' then 1 else 0 END ) As FAILED,
// sum(case when status = 'SENT' then 1 else 0 END ) As sent  from emails