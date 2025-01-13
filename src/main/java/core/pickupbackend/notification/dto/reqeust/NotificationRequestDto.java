package core.pickupbackend.notification.dto.reqeust;

public class NotificationRequestDto<T> {

    private T targetToken;
    private String title;
    private String body;

    public NotificationRequestDto(final T targetToken, final String title, final String body) {
        this.targetToken = targetToken;
        this.title = title;
        this.body = body;
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
