package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

public class MysticPowerCell extends CustomRelic{

    public static final String ID = NameAssist.MakePath("MysticPowerCell");
    private static final Texture IMG = new Texture("TC_resources/img/relics/MysticPowerCell.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/MysticPowerCell.png");
    private static final RelicTier RELIC_TIER = RelicTier.SHOP;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public MysticPowerCell() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
        this.energyBased = true;
        this.counter = 3;
    }

    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((AbstractPlayer.PlayerClass)null);
    }

    private String setDescription(AbstractPlayer.PlayerClass c) {
        return this.DESCRIPTIONS[0] + 3 + this.DESCRIPTIONS[1];
    }

    public void updateDescription(AbstractPlayer.PlayerClass c) {
        this.description = this.setDescription(c);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    public AbstractRelic makeCopy() {
        return new MysticPowerCell();
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void atPreBattle() {
        if (this.counter > 0) {
            this.description = this.DESCRIPTIONS[0] + this.counter + this.DESCRIPTIONS[1];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.initializeTips();
            this.beginLongPulse();
            this.flash();
        }else{
            this.description = this.DESCRIPTIONS[2];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.initializeTips();
        }
    }

    public void onVictory() {
        if (this.pulse) {
            this.stopPulse();
        }
        if (this.counter > 0) {
            --this.counter;
        }
        if (this.counter == 0) {
            this.setCounter(-2);
            this.description = this.DESCRIPTIONS[2];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.initializeTips();
            --AbstractDungeon.player.energy.energyMaster;
        }else{
            this.description = this.DESCRIPTIONS[0] + this.counter + this.DESCRIPTIONS[1];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.initializeTips();
        }
    }

    public void setCounter(int setCounter) {
        this.counter = setCounter;
        if (setCounter <= 0) {
            this.usedUp();
        }
    }
}
