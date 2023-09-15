package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

public class TransmutationReactor extends CustomRelic{

    public static final String ID = NameAssist.MakePath("Reactor");
    private static final String IMG_PATH = "TC_resources/img/relics/pallite.png";
    private static final RelicTier RELIC_TIER = RelicTier.SHOP;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public TransmutationReactor() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
        this.energyBased = true;
        this.counter = 3;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new TransmutationReactor();
    }

    public void atPreBattle() {
        if (this.counter > 0) {
            --this.counter;
            if (this.counter == 0) {
                this.setCounter(-2);
                this.description = this.DESCRIPTIONS[1];
                this.tips.clear();
                this.tips.add(new PowerTip(this.name, this.description));
                this.initializeTips();
            }
        }
    }

    public void atTurnStart() {
        if(this.counter > 0) {
            this.flash();
            this.addToTop(new GainEnergyAction(1));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }

    public void setCounter(int setCounter) {
        this.counter = setCounter;
        if (setCounter <= 0) {
            this.usedUp();
        }

    }
}
