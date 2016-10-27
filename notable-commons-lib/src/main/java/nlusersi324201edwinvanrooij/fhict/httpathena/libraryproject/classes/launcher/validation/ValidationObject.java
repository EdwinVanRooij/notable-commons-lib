package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.launcher.validation;

/**
 * Created by Edwin on 12/29/2015.
 */
class ValidationObject {
    //region Fields
    private final boolean isCorrect;
    private String informativeString;
    //endregion

    //region Properties
    public boolean isCorrect() {
        return isCorrect;
    }

    public String getInformativeString() {
        return informativeString;
    }
    //endregion

    //region Constructors
    public ValidationObject(String informativeString) {
        this.isCorrect = false;
        this.informativeString = informativeString;
    }
    public ValidationObject() {
        this.isCorrect = true;
    }
    //endregion

    //region Methods

    //endregion

    //region Enums

    //endregion
}