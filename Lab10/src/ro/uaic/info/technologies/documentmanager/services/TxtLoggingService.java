package ro.uaic.info.technologies.documentmanager.services;

import ro.uaic.info.technologies.documentmanager.models.Document;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;

@ApplicationScoped
public class TxtLoggingService implements Serializable {

    private Path logFile;

    @PostConstruct
    public void init() {
        logFile = Paths.get("D:\\Master\\Java Technologies\\docs\\logfile.txt");
    }

    public void onDocumentsUpload(@Observes Document document) {
        try {
            Files.write(logFile, Collections.singleton("New document added: " + document.toString() + " at " + new Date() + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
