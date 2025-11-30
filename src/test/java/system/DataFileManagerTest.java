package system;

import model.AutoCredit;
import model.Credit;
import model.CreditType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class DataFileManagerTest {

    @Test
    void testSaveAndLoadObject() throws Exception {


        File tempFile = Files.createTempFile("credit", ".dat").toFile();
        tempFile.deleteOnExit();


        Credit credit = new AutoCredit(1, "AutoTest", 10000, 12, 10.0, "BMW");

        DataFileManager.saveObject(credit, tempFile.getAbsolutePath());

        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);

        Object loaded = DataFileManager.loadObject(tempFile.getAbsolutePath());

        assertNotNull(loaded);
        assertTrue(loaded instanceof Credit);

        Credit c = (Credit) loaded;

        assertEquals(1, c.getId());
        assertEquals("AutoTest", c.getName());
        assertEquals(10000, c.getSum());
        assertEquals(12, c.getTermMonths());
        assertEquals(10.0, c.getInterestRate());
        assertEquals(CreditType.AUTO, c.getType());
    }

    @Test
    void testLoadNonExistingFile() {
        assertThrows(Exception.class, () -> {
            DataFileManager.loadObject("file_that_does_not_exist.123");
        });
    }
}
