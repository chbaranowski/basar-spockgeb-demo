package basar.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ErrorMessage implements Serializable {

    public static class Error implements Serializable {

        private static final long serialVersionUID = 1L;

        String field;

        String description;

        public Error(String field, String description) {
            super();
            this.field = field;
            this.description = description;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    private static final long serialVersionUID = 1L;

    private List<Error> errors;

    public ErrorMessage(List<Error> errors) {
        this.errors = errors;
    }

    public ErrorMessage(Error error) {
        this(Collections.singletonList(error));
    }

    public ErrorMessage(Error... errors) {
        this(Arrays.asList(errors));
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
