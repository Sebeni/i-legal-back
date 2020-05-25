package pl.seb.czech.ilegal.back.services;

public class ElementNotFound extends RuntimeException {
    public ElementNotFound(Object id) {
        super("Element with given id [" + id  + "]was not found");
    }
}
