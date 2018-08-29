package ro.uaic.info.javatehnologies;

import com.sun.deploy.net.BasicHttpRequest;
import com.sun.deploy.net.HttpRequest;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MapApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");

        // Have one (or more) threads ready to do the async tasks. Do this during startup of your app.
        ExecutorService executor = Executors.newFixedThreadPool(10);

        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        //Defining the Name text field
        final TextField valueTextField = new TextField();
        valueTextField.setPromptText("Enter the value");
        valueTextField.setPrefColumnCount(10);
        valueTextField.getText();
        GridPane.setConstraints(valueTextField, 0, 0);
        grid.getChildren().add(valueTextField);

        //Defining the Last Name text field
        final TextField keyTextField = new TextField();
        keyTextField.setPromptText("Enter the key");
        GridPane.setConstraints(keyTextField, 0, 1);
        grid.getChildren().add(keyTextField);

        //Defining the Submit button
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 0, 2);
        GridPane.setHalignment(submit, HPos.CENTER);


        submit.setOnAction(event -> {

            try {
                // Fire a request.
                Future<Response> response = executor.submit(new Request(new URL("http://google.com")));

                // Do your other tasks here (will be processed immediately, current thread won't block).
                // ...
                ProgressIndicator progressIndicator = new ProgressIndicator();
                grid.getChildren().add(progressIndicator);

                // Get the response (here the current thread will block until response is returned).
                InputStream body = response.get().getBody();
                grid.getChildren().remove(progressIndicator);
                Scanner s = new Scanner(body).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                // Shutdown the threads during shutdown of your app.
                executor.shutdown();
            }

        });

        grid.getChildren().add(submit);

        Scene scene = new Scene(grid, grid.getPrefWidth(), grid.getPrefHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
