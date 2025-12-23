package pl.company.mailing;

public class MailingRequestDto {
    private String email;
    private String message;
    public MailingRequestDto() {
    }
    public MailingRequestDto(String email, String message) {
        this.email = email;
        this.message = message;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
// dodac nowe pole np sentMode enum, 2 wartosci instant, async