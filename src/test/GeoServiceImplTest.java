import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.*;

public class GeoServiceImplTest {

    GeoService geoService = Mockito.mock(GeoServiceImpl.class);

    @Test
    public void byIpTest() {

        Mockito.when(geoService.byIp(LOCALHOST)).thenReturn(new Location(null, null, null, 0));
        Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp(NEW_YORK_IP)).thenReturn(new Location("New York", USA, " 10th Avenue", 32));
        Mockito.when(geoService.byIp(Mockito.startsWith("172."))).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.startsWith("96."))).thenReturn(new Location("New York", Country.USA, null, 0));

        Assertions.assertNull(geoService.byIp("127.0.0.1").getCountry());
        Assertions.assertEquals(RUSSIA, geoService.byIp("172.0.32.11").getCountry());
        Assertions.assertEquals(USA, geoService.byIp("96.44.183.149").getCountry());
        Assertions.assertEquals(RUSSIA, geoService.byIp("172.123.12.19").getCountry());

    }

    @Test
    public void byCoordinatesTest() {

        Mockito.when(geoService.byCoordinates(Mockito.anyDouble(), Mockito.anyDouble())).thenThrow(new RuntimeException("Not implemented"));

        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(124.56, 45.78));

    }
}
