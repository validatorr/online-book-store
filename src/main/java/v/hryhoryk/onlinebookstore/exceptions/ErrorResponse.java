package v.hryhoryk.onlinebookstore.exceptions;

public record ErrorResponse(
        String message,
        String details
) {}
