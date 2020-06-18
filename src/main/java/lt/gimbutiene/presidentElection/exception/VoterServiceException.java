package lt.gimbutiene.presidentElection.exception;

public class VoterServiceException extends RuntimeException {
    public VoterServiceException(final String message) {
        super(message);
    }
}
