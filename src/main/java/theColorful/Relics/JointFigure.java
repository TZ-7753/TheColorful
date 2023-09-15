package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

public class JointFigure extends CustomRelic{

    public static final String ID = NameAssist.MakePath("JointFigure");
    private static final Texture IMG = new Texture("TC_resources/img/relics/jointfigure.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/jointfigure.png");
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    private static final LandingSound LANDING_SOUND = LandingSound.SOLID;

    public JointFigure() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new JointFigure();
    }

}
