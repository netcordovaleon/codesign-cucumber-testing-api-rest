package wellsky.apiRestTest.httpExtensions;

import io.restassured.response.Response;

import java.util.concurrent.TimeUnit;

public abstract class HttpAbstractExtension {

    public int GetStatusCode(Response res) {
        return res.getStatusCode();
    }

    public boolean CompareStatusCode(Response res, int expectStatus) {
        return res.getStatusCode() == expectStatus;
    }

    public long GetTimeToResponse(Response res) {
        return res.getTime();
    }

    public long GetSecondsToResponse(Response res) {
        return res.getTimeIn(TimeUnit.SECONDS);
    }

    public String GetHeaderProperty(Response res, String headerProperty) {
        return res.getHeader(headerProperty);
    }

}
