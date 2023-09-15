package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

public class LivePaint extends CustomRelic{

    public static final String ID = NameAssist.MakePath("LivePaint");
    private static final String IMG_PATH = "TC_resources/img/relics/pallite.png";
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;
    private boolean firstTurn = true;
    public LivePaint() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
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

    public void atPreBattle() {
        this.firstTurn = true;
    }

    public void atTurnStart() {
        if (this.firstTurn) {
            this.firstTurn = false;
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            int j = AbstractDungeon.cardRandomRng.random(5);
            switch (j){
                case 0:
                    this.addToBot(new ToningAction(AbstractDungeon.player, ToningCards.MainTone.RED));
                    break;
                case 1:
                    this.addToBot(new ToningAction(AbstractDungeon.player, ToningCards.MainTone.ORANGE));
                    break;
                case 2:
                    this.addToBot(new ToningAction(AbstractDungeon.player, ToningCards.MainTone.YELLOW));
                    break;
                case 3:
                    this.addToBot(new ToningAction(AbstractDungeon.player, ToningCards.MainTone.GREEN));
                    break;
                case 4:
                    this.addToBot(new ToningAction(AbstractDungeon.player, ToningCards.MainTone.BLUE));
                    break;
                case 5:
                    this.addToBot(new ToningAction(AbstractDungeon.player, ToningCards.MainTone.PURPLE));
                    break;
            }
        }
    }
}
