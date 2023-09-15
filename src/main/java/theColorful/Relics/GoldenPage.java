package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

public class GoldenPage extends CustomRelic{

    public static final String ID = NameAssist.MakePath("GoldenPage");
    private static final String IMG_PATH = "TC_resources/img/relics/pallite.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;

    public GoldenPage() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new GoldenPage();
    }

}
