package pl.company.mailing;

public class EmailStatusCountDto {
    private String status;
    private Long count;

    public EmailStatusCountDto(String status, Long count) {
        this.status = status;
        this.count = count;
    }

    public String getStatus() {
        return status;
    }
    public Long getCount() {
        return count;
    }
}
