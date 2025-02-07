package core.pickupbackend.notification.dto.reqeust;

import java.util.List;

public class NotificationCommand<T> {

    private T targetToken;
    private String title;
    private String body;

    public NotificationCommand(final String title, final String body) {
        targetToken = (T) List.of();
        this.title = title;
        this.body = body;
    }

    public NotificationCommand(final T targetToken, final String title, final String body) {
        this.targetToken = targetToken;
        this.title = title;
        this.body = body;
    }

    public void setTargetToken(final T targetToken) {
        this.targetToken = targetToken;
    }

    public T getTargetToken() {
        return targetToken;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
