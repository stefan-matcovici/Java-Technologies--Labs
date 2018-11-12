package ro.uaic.info.javatechnologies.bomber;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String endpoint = args[0];

        long seconds = 0;
        ExecutorService executor = Executors.newFixedThreadPool(1000);
        List<Callable<Double>> tasks = new ArrayList<>();
        for (int i = 0; i < 100_000; i++)
            tasks.add(() -> {
                try {
                    URL url = new URL("http://localhost:8080/lab6/" + endpoint);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    if (endpoint.equals("insert")) {
                        conn.setDoOutput(true);
                        String urlParameters = String.format("key=%s&value=%s", "yes" + new Date().getTime(), "yes");
                        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
                        int postDataLength = postData.length;
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
                    } else {
                        conn.setRequestMethod("GET");
                    }

                    System.out.println("sent");
                    long startTime = System.nanoTime();
                    System.out.println(conn.getResponseCode());
                    long endTime = System.nanoTime();
                    return (double)(endTime - startTime) / 1_000_000_000.0;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            });

        List<Future<Double>> results = executor.invokeAll(tasks, 30, TimeUnit.SECONDS);
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("\nFinished all threads");
        System.out.println(String.format("\n%f", results.stream().mapToDouble(longFuture -> {
            try {
                return longFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return 0;
        }).sum()));
    }
}
