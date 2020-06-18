package lt.gimbutiene.presidentElection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VoterServiceException extends RuntimeException {
    public VoterServiceException(final String message) {
        super(message);
    }
}
