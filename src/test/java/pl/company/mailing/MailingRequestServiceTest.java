package pl.company.mailing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MailingRequestServiceTest {
    @InjectMocks

    private MailingRequestService mailingRequestService;
    @Mock
    private MailingRequestRepository mailingRequestRepository;

    @Test
    void schouldCreateMailingRequest() {
        String email = "rafal@op.pl";
        String message = "hello";
        MailingRequestEntity mailingRequestEntity = mailingRequestService.create(email, message);
        verify(mailingRequestRepository).save(any());
    }
}