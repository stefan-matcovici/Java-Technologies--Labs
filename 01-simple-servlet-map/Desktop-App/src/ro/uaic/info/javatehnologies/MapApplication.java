package ro.uaic.info.javatehnologies;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MapApplication {

    public static void main(String... args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < 1_000; i++)
            tasks.add(() -> {
                try {
                    URL url = new URL("http://localhost:8080/map");
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setDoOutput(true);

                    String urlParameters = String.format("key=%s&value=%s", "yes" + new Date().getTime(), "yes");
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

                    System.out.println(conn.getResponseCode());
                    // System.out.println(new BufferedReader(new InputStreamReader(conn.getInputStream()))
                    //         .lines().collect(Collectors.joining("\n")));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            });

        executor.invokeAll(tasks, 30, TimeUnit.SECONDS);
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("\nFinished all threads");
    }
}
