package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

public class BigHorn extends CustomRelic{

    public static final String ID = NameAssist.MakePath("BigHorn");
    private static final Texture IMG = new Texture("TC_resources/img/relics/bighorn.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/bighornO.png");
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.HEAVY;

    private boolean used = false;
    public BigHorn() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new BigHorn();
    }

    public void atPreBattle() {
        this.used = false;
    }

    public void onBlockBroken(AbstractCreature m) {
        if(!this.used){
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(m, this));
            this.addToBot(new GainEnergyAction(1));
            this.used = true;
        }
    }

}
