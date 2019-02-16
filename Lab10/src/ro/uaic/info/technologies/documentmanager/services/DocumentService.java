package ro.uaic.info.technologies.documentmanager.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DocumentService implements Serializable {

    public void uploadDocument(byte[] rawDocument, Integer registrationNumber) {
        try {
            Path path = Paths.get("D:\\Master\\Java Technologies\\docs\\" + registrationNumber);
            Files.write(path, rawDocument);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getDocumentByRegistrationNumber(Integer registrationNumber) {
        try {
            Path path = Paths.get("D:\\Master\\Java Technologies\\docs\\" + registrationNumber);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
