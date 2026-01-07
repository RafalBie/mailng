package pl.company.mailing;

public class EmailSummaryDto {
    private Long sent;
    private Long failed;

    public EmailSummaryDto(Long sent, Long failed) {
        this.sent = sent;
        this.failed = failed;
    }
    public Long getSent() {
        return sent;
    }
    public Long getFailed() {
        return failed;
    }

}
