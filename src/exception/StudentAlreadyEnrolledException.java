package exception;

public class StudentAlreadyEnrolledException extends Exception {

    public StudentAlreadyEnrolledException(String messages) {

        super(messages);
    }
}