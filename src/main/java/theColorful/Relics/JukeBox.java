package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

public class JukeBox extends CustomRelic{

    public static final String ID = NameAssist.MakePath("MusicBox");
    private static final Texture IMG = new Texture("TC_resources/img/relics/musicbox.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/musicbox.png");
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public JukeBox() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
        this.energyBased = true;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new JukeBox();
    }

    public void onPlayerEndTurn() {
        if(AbstractDungeon.player.hand.size() >= 6){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new EnergizedPower(AbstractDungeon.player,1)));
        }
    }

}
