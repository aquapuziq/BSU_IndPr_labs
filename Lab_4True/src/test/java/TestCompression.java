import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCompression {

    @Test
    void testCompression1() {
        String text1 = """
                hi mmmy naaame iss NIKITTTTTAA!!!!
                nice to meeeet u???
                """;
        String textExpected1 = """
                hi m3y na3me is2 NIKIT5A2!4
                nice to me4t u?3
                """;
        String textActual1 = TextCompression.compressLine(text1);
        assertEquals(textExpected1.trim(), textActual1.trim());
    }
    @Test
    void testCompression2() {
        String text2 = """
                Once thhhhhhere lived an old maaaaaan and old woman.
                Thee old man said, «Old woman,,,,,,,, baake mee aa bun.» «Whhhhhat can I makee iit from???
                I haveee no ffffflour.....» «Eh, eh, olld wwoman! Scrrape the cupbooooard, sweep the flour bin, and you will find enough flour.»
                The old woman picked up a duster,
                scrapeddddd the cupboard, ssssswept the flour biiin and gathered aaaabout two handfuls of flour.
                She mixxxxed the dougggggh witth sour creammmmm, fried it in buttttter,
                annd puttt the bbun on the winnnndow sill to cool.
                """;

        String textExpected2 = """
                Once th6ere lived an old ma6n and old woman.
                The2 old man said, «Old woman,8 ba2ke me2 a2 bun.» «Wh5at can I make2 i2t from?3
                I have3 no f5lour.5» «Eh, eh, ol2d w2oman! Scr2ape the cupbo4ard, swe2p the flour bin, and you wil2 find enough flour.»
                The old woman picked up a duster,
                scraped5 the cupboard, s5wept the flour bi3n and gathered a4bout two handfuls of flour.
                She mix4ed the doug5h wit2h sour cream5, fried it in but5er,
                an2d put3 the b2un on the win4dow sil2 to co2l.
                """;
        String textActual2 = TextCompression.compressLine(text2);
        assertEquals(textExpected2.trim(), textActual2.trim());
    }
}
