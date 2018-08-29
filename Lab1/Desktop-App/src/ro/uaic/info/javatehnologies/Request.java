package ro.uaic.info.javatehnologies;

import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

public class Request implements Callable<Response> {
    private final String value;
    private final String key;
    private URL url;

    public Request(URL url, CharSequence key, CharSequence value) {
        this.url = url;
        this.key = key.toString();
        this.value = value.toString();
    }

    @Override
    public Response call() throws Exception {
        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoOutput(true);

        String urlParameters = String.format("key=%s&value=%s", this.key, this.value);
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
            wr.flush();
        }

        return new Response(conn.getInputStream());
    }
}
