package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

public class GoldenPage extends CustomRelic{

    public static final String ID = NameAssist.MakePath("GoldenPage");
    private static final Texture IMG = new Texture("TC_resources/img/relics/goldenpages.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/goldenpages.png");
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;

    public GoldenPage() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new GoldenPage();
    }

}
