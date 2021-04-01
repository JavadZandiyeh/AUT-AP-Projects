package Main;

import java.io.Serializable;

/**
 * this class used for handling errors
 * mostly used between client and server
 */
public class ErrorClass implements Serializable {
    private String error;

    /**
     * @param error error which has occurred
     */
    public ErrorClass(String error){
        this.error = error;
    }

    /**
     * @return errors text
     */
    public String getError() {
        return error;
    }
}
