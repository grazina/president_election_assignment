package lt.gimbutiene.presidentElection.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GenericErrorController extends AbstractErrorController {
    private final static String MESSAGE_KEY = "message";
    private final static String STATUS_KEY = "status";
    private final static String ERROR_KEY = "error";

    public GenericErrorController(final ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override


    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> error(final HttpServletRequest request) {
        final HttpStatus status = this.getStatus(request);

        final Map<String, Object> errorAttributes = this.getErrorAttributes(request,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));

        final Map<String, Object> outputAttributes = new HashMap<>();
        outputAttributes.put(MESSAGE_KEY, errorAttributes.get(MESSAGE_KEY));
        outputAttributes.put(STATUS_KEY, errorAttributes.get(STATUS_KEY));
        outputAttributes.put(ERROR_KEY, errorAttributes.get(ERROR_KEY));

        return ResponseEntity.status(status).body(outputAttributes);
    }
}
