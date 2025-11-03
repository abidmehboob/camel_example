package org.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContentBasedFileRouterGeneratedAiTests extends CamelTestSupport {

    @BeforeEach
    void setUp() {
        // Set up any necessary resources or configurations before each test
    }

    @Test
    void testRouteHandlesTxtFilesCorrectly() throws Exception {
        // GIVEN a file with a .txt extension in the source folder
        String sourceFilePath = "src/test/source-folder/example.txt";
        // WHEN the file is processed by the route
        template.sendBodyAndHeader("file://" + ContentBasedFileRouter.SOURCE_FOLDER, "Test content", "CamelFileName", "example.txt");
        // THEN the file should be moved to the destination folder for txt files
        assertFileExists(ContentBasedFileRouter.DESTINATION_FOLDER_TXT + "/example.txt");
    }

    @Test
    void testRouteHandlesOtherFilesCorrectly() throws Exception {
        // GIVEN a file with an extension other than .txt in the source folder
        String sourceFilePath = "src/test/source-folder/example.pdf";
        // WHEN the file is processed by the route
        template.sendBodyAndHeader("file://" + ContentBasedFileRouter.SOURCE_FOLDER, "Test content", "CamelFileName", "example.pdf");
        // THEN the file should be moved to the destination folder for other files
        assertFileExists(ContentBasedFileRouter.DESTINATION_FOLDER_OTHER + "/example.pdf");
    }

}
