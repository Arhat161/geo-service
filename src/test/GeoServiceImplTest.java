import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.*;

public class GeoServiceImplTest {

    static GeoService geoService;

    @BeforeAll
    public static void initSuite() {
        geoService = Mockito.mock(GeoServiceImpl.class);

        Mockito.when(geoService.byIp(LOCALHOST)).thenReturn(new Location(null, null, null, 0));
        Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp(NEW_YORK_IP)).thenReturn(new Location("New York", USA, " 10th Avenue", 32));
        Mockito.when(geoService.byIp(Mockito.startsWith("172."))).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.startsWith("96."))).thenReturn(new Location("New York", Country.USA, null, 0));

        Mockito.when(geoService.byCoordinates(Mockito.anyDouble(), Mockito.anyDouble())).thenThrow(new RuntimeException("Not implemented"));

        System.out.println("Start tests for methods of class 'GeoServiceImpl' ....");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("All tests for methods of class 'GeoServiceImpl' complete!");
    }

    @Test
    @DisplayName("Test byIp() for localhost")
    public void byIpLocalhostTest(TestInfo byIpLocalhostTestInfo) {
        Assertions.assertNull(geoService.byIp("127.0.0.1").getCountry(), byIpLocalhostTestInfo.getDisplayName() + " is NO complete!");
        System.out.println(byIpLocalhostTestInfo.getDisplayName() + " complete!");
    }

    @ParameterizedTest
    @DisplayName("Test byIp() for russian IP ")
    @ValueSource(strings = {"172.0.32.11", "172.123.12.19"})
    public void byIpRussiaTest(String ip) {
        Assertions.assertEquals(RUSSIA, geoService.byIp(ip).getCountry());
        System.out.println("Test byIp(" + ip + ") complete!");
    }

    @ParameterizedTest
    @DisplayName("Test byIp() for USA IP ")
    @ValueSource(strings = {"96.44.183.149", "96.25.145.201"})
    public void byIpUsaTest(String ip) {
        Assertions.assertEquals(USA, geoService.byIp(ip).getCountry());
        System.out.println("Test byIp(" + ip + ") complete!");
    }

    @Test
    @DisplayName("Test byCoordinatesTest()")
    public void byCoordinatesTest(TestInfo byCoordinatesTestInfo) {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(124.56, 45.78),
                byCoordinatesTestInfo.getDisplayName() + " is NO complete!");
        System.out.println(byCoordinatesTestInfo.getDisplayName() + " complete!");
    }
}
