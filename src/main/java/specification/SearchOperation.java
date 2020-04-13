package specification;

public enum SearchOperation {
    EQUALITY, IN;

    public static final String[] OPERATION_SET = {"=", ":"};

    public static SearchOperation getOperation(char input) {
        switch (input) {
            case '=':
                return EQUALITY;
            case ':':
                return IN;
            default:
                return null;
        }
    }
}
