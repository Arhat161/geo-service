import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class LocalizationServiceImplTest {

    static LocalizationService localizationService;

    @BeforeAll
    public static void initSuite() {

        localizationService = Mockito.mock(LocalizationServiceImpl.class);

        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");

        System.out.println("Start tests for methods of class 'LocalizationServiceImpl' ....");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("All tests for methods of class 'LocalizationServiceImpl' complete!");
    }

    @Test
    @DisplayName("Test locale() for RUSSIA")
    public void localeRussiaTest(TestInfo localeTestInfo) {
        Assertions.assertEquals(localizationService.locale(RUSSIA), "Добро пожаловать",
                localeTestInfo.getDisplayName() + " is NO complete !");
        System.out.println(localeTestInfo.getDisplayName() + " complete !");
    }

    @Test
    @DisplayName("Test locale() for USA")
    public void localeUsaTest(TestInfo localeTestInfo) {
        Assertions.assertEquals(localizationService.locale(USA), "Welcome",
                localeTestInfo.getDisplayName() + " is NO complete !");
        System.out.println(localeTestInfo.getDisplayName() + " complete !");
    }
}
