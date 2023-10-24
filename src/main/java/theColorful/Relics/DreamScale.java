package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

public class DreamScale extends CustomRelic{

    public static final String ID = NameAssist.MakePath("DreamScale");
    private static final Texture IMG = new Texture("TC_resources/img/relics/dreamscale.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/dreamscale.png");
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;

    public DreamScale() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
        this.counter = 0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new DreamScale();
    }

    public void atTurnStart() {
        this.counter++;
        if(this.counter == 2){
            this.counter = 0;
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player,this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new BufferPower(AbstractDungeon.player,1)));
            int n = random(1,5);
            switch (n){
                case 1:
                    this.addToBot(new MakeTempCardInDiscardAction(new Burn(),1));
                    break;
                case 2:
                    this.addToBot(new MakeTempCardInDiscardAction(new Slimed(),1));
                    break;
                case 3:
                    this.addToBot(new MakeTempCardInDiscardAction(new Wound(),1));
                    break;
                case 4:
                    this.addToBot(new MakeTempCardInDiscardAction(new Dazed(),1));
                    break;
                case 5:
                    this.addToBot(new MakeTempCardInDiscardAction(new VoidCard(),1));
                    break;
            }
        }
    }

}
