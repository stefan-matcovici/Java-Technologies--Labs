package ro.uaic.info.javatehnologies;

import java.io.InputStream;

public class Response {
    private InputStream body;

    public Response(InputStream body) {
        this.body = body;
    }

    public InputStream getBody() {
        return body;
    }
}