package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;
    Varasto varasto5;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto5 = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysMeneeYli() {
        varasto.lisaaVarastoon(11);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisays() {
        varasto.lisaaVarastoon(-8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivisenMaaranOttaminenVarastosta() {
        Varasto uusi3 = new Varasto(100, 10);
        varasto.otaVarastosta(-1);

        assertEquals(10, uusi3.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(3);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(5, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktorissa() {
        Varasto uusi3 = new Varasto(100, 0);
        assertEquals(100, uusi3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktorissaNegatiivinenTilavuus() {
        Varasto uusi3 = new Varasto(-100, 0);
        assertEquals(0.0, uusi3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktorissaNegatiivinenAlkusaldo() {
        Varasto uusi3 = new Varasto(100, -5);
        assertEquals(0.0, uusi3.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void KonstruktorissaNegatiivinenAlkusaldo() {
        Varasto uusi = new Varasto(-100);
        assertEquals(0.0, uusi.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastotaEnemmanKuinSaldo() {
        Varasto uusi = new Varasto(100, 10);
        uusi.otaVarastosta(20);
        assertEquals(0.0, uusi.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoEsityss() {

        String merkkijono = varasto.toString();

        assertEquals(merkkijono, "saldo = 0.0, viel� tilaa 10.0");

        assertEquals(merkkijono, "saldo = 0.0, vielä tilaa 10.0");
    }

}
