import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class LocalizationServiceImplTest {

    LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);

    @Test
    public void localeTest() {

        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");

        Assertions.assertEquals(localizationService.locale(RUSSIA), "Добро пожаловать");
        Assertions.assertEquals(localizationService.locale(USA), "Welcome");

    }
}
