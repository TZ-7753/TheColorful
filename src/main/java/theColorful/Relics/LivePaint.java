package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.Ink_pow;

public class LivePaint extends CustomRelic{

    public static final String ID = NameAssist.MakePath("LivePaint");
    private static final Texture IMG = new Texture("TC_resources/img/relics/livepaint.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/livepaint.png");
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;
    public LivePaint() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new LivePaint();
    }

    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(5, true);
    }

    public void atBattleStart() {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new Ink_pow(AbstractDungeon.player,1)));
    }
}
