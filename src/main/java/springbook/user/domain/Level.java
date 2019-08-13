package springbook.user.domain;

public enum Level {
    BASIC(1), SILVER(2), GOLD(3);

    static {
        BASIC.next = SILVER;
        SILVER.next = GOLD;
        GOLD.next = null;
    }

    private final int value;
    private Level next;

    Level(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    public Level nexLevel() {
        return next;
    }

    public static Level valueOf(int value) {
        switch (value) {
            case 1:
                return BASIC;
            case 2:
                return SILVER;
            case 3:
                return GOLD;
            default:
                throw new AssertionError("Unknown value: " + value);
        }
    }
}
