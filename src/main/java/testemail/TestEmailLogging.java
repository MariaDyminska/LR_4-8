package testemail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestEmailLogging {
    private static final Logger logger = LogManager.getLogger(TestEmailLogging.class);

    public static void main(String[] args) {
        logger.error("Це критична помилка!!!");
        System.out.println("Лог відправлено, перевір пошту!");
    }
}
