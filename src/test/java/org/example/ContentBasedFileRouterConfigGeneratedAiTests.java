package org.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ContentBasedFileRouterGeneratedAiTests extends CamelTestSupport {

    private AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(ContentBasedFileRouterConfig.class);
        startCamelContext();
    }

    @Test
    void testRouteForTxtFiles() throws Exception {
        // GIVEN a file with the extension "txt" in the source folder
        String sourceFilePath = "src/test/source-folder/test.txt";
        // WHEN the file is processed by the route
        template.sendBodyAndHeader(sourceFilePath, null, "CamelFileName", "test.txt");
        // THEN the file should be moved to the destination folder for txt files
        String expectedDestinationPath = "src/test/destination-folder-txt/test.txt";
        assertTrue(fileExist(expectedDestinationPath));
    }

    @Test
    void testRouteForNonTxtFiles() throws Exception {
        // GIVEN a file with an extension other than "txt" in the source folder
        String sourceFilePath = "src/test/source-folder/test.pdf";
        // WHEN the file is processed by the route
        template.sendBodyAndHeader(sourceFilePath, null, "CamelFileName", "test.pdf");
        // THEN the file should be moved to the destination folder for other files
        String expectedDestinationPath = "src/test/destination-folder-other/test.pdf";
        assertTrue(fileExist(expectedDestinationPath));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        applicationContext.close();
    }
    private boolean fileExist(String filePath) {
        return new java.io.File(filePath).exists();
    }
}
