package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

public class Tripling extends CustomRelic{

    public static final String ID = NameAssist.MakePath("Tripling");
    private static final Texture IMG = new Texture("TC_resources/img/relics/tripling.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/triplingO.png");
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public Tripling() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
        this.counter = 0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Tripling();
    }

    public void onManualDiscard() {
        this.flash();
        this.counter ++;
        if(this.counter == 3){
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DrawCardNextTurnPower(AbstractDungeon.player,3)));
            this.counter = 0;
        }
    }

}
