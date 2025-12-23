package pl.company.mailing;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
public class MailingRequestValidator {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public void validate(MailingRequestDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Request body nie może być pusty");
        }
        validateEmail(dto.getEmail());
        validateMessage(dto.getMessage());
    }
    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email nie moze byc pusty");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Niepoprawny format e-mail");
        }

    }
    private void validateMessage(String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message nie moze byc pusty");
        }
        if(message.length()>100){
            throw new IllegalArgumentException("Message too long");
        }
        if (message.length()<3){
            throw new IllegalArgumentException("Message za krotki");
        }
    }
}
//todo dorobic testy