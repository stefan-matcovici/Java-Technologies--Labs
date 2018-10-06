package ro.uaic.info.javatehnologies;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MapApplication {

    public static int calls = 0;

    public static void main(String... args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < 1_000; i++)
            tasks.add(() -> {
                try {
                    URL url = new URL("http://localhost:8080/map");
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setDoOutput(true);

                    String urlParameters = String.format("key=%s&value=%s", "yes"+String.valueOf(calls), "yes");
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

                    System.out.println("sent");

                    System.out.println(new BufferedReader(new InputStreamReader(conn.getInputStream()))
                            .lines().collect(Collectors.joining("\n")) + calls);
                    calls += 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            });

        executor.invokeAll(tasks, 30, TimeUnit.SECONDS);
        System.out.println(executor.isTerminated());
        System.out.println(executor.isShutdown());
    }
}
