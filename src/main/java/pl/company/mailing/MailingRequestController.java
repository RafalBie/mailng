package pl.company.mailing;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
public class MailingRequestController {
    private MailingRequestService mailingRequestService;
    private MailingRequestValidator mailingRequestValidator;

    public MailingRequestController(
            MailingRequestService mailingRequestService,
            MailingRequestValidator mailingRequestValidator)
    {
        this.mailingRequestService = mailingRequestService;
        this.mailingRequestValidator = mailingRequestValidator;
    }




    @PostMapping
    public ResponseEntity<MailingRequestEntity>createEmail
            (@RequestBody MailingRequestDto request) {
        mailingRequestValidator.validate(request);
     MailingRequestEntity saved = mailingRequestService.create(
                request.getEmail(),
                request.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
//zzrobic walidacje,wywolywany w serwisie