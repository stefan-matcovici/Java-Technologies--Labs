package ro.uaic.info.javatehnologies;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class MapApplication extends Application {

    ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");

        // Have one (or more) threads ready to do the async tasks. Do this during startup of your app.


        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);


        //Defining the Key text field
        final TextField keyTextField = new TextField();
        keyTextField.setPromptText("Enter the value");
        GridPane.setConstraints(keyTextField, 0, 0);
        grid.getChildren().add(keyTextField);

        //Defining the Value text field
        final TextField valueTextField = new TextField();
        valueTextField.setPromptText("Enter the key");
        valueTextField.setPrefColumnCount(10);
        valueTextField.getText();
        GridPane.setConstraints(valueTextField, 0, 1);
        grid.getChildren().add(valueTextField);

        //Defining the Submit button
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 0, 3);
        GridPane.setHalignment(submit, HPos.CENTER);

        //Creating a Text object
        Text text = new Text();
        text.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(submit, HPos.CENTER);
        GridPane.setConstraints(text, 0, 2);
        grid.getChildren().add(text);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setVisible(false);
        GridPane.setConstraints(text, 0, 4);
        grid.getChildren().add(progressIndicator);

        submit.setOnAction(event -> {
            Task<String> getResponseTask = new Task<String>() {
                @Override
                protected String call() throws Exception {
                    URL url = new URL("http://localhost:8080/MapHTTPServlet");
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setDoOutput(true);

                    String urlParameters = String.format("key=%s&value=%s", keyTextField.getCharacters(), valueTextField.getCharacters());
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

                    return new BufferedReader(new InputStreamReader(conn.getInputStream()))
                            .lines().collect(Collectors.joining("\n"));
                }
            };

            getResponseTask.setOnRunning(event1 -> {
                progressIndicator.setProgress(event1.getSource().getProgress());
            });

            getResponseTask.setOnSucceeded(event1 -> {
                text.setText(event1.getSource().getValue().toString());
                progressIndicator.setVisible(false);
            });

            Thread th = new Thread(getResponseTask);
            th.start();
            progressIndicator.setVisible(true);


        });
        grid.getChildren().add(submit);

        Scene scene = new Scene(grid, grid.getPrefWidth(), grid.getPrefHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        executor.shutdown();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
