package ua.vn.talkos.util.contact;

/**
 * @author oleg.sukhov
 */
public enum ContactStateEnum {
    OFFLINE(0, "offline"), ONLINE(1, "online");

    private int code;
    private String title;

    ContactStateEnum(int code, String title) {
        this.code = code;
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

