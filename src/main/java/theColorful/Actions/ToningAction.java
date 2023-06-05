package theColorful.Actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.*;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public class ToningAction extends AbstractGameAction {
    public AbstractPlayer owner;
    public ToningCards.MainTone tone;
    public ToningAction(AbstractPlayer p,ToningCards.MainTone tone) {
        this.owner = p;
        this.tone = tone;
        this.duration = 0.5F;
    }

    @Override
    public void update() {

        //若无阻止调色的pow或其他特殊主色调，则正常清理
        this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Calibrating")));
        if(this.owner.hasPower(NameAssist.MakePath("NoTone_pow"))){
            this.addToBot(new ReducePowerAction(this.owner,this.owner,NameAssist.MakePath("NoTone_pow"),1));
        }else {
            if (this.owner.hasPower(NameAssist.MakePath("TonePurple")) && this.tone != ToningCards.MainTone.PURPLE) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("TonePurple")));
            } else if (this.owner.hasPower(NameAssist.MakePath("ToneRed")) && this.tone != ToningCards.MainTone.RED) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("ToneRed")));
            } else if (this.owner.hasPower(NameAssist.MakePath("ToneOrange")) && this.tone != ToningCards.MainTone.ORANGE) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("ToneOrange")));
            } else if (this.owner.hasPower(NameAssist.MakePath("ToneYellow")) && this.tone != ToningCards.MainTone.YELLOW) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("ToneYellow")));
            } else if (this.owner.hasPower(NameAssist.MakePath("ToneGreen")) && this.tone != ToningCards.MainTone.GREEN) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("ToneGreen")));
            } else if (this.owner.hasPower(NameAssist.MakePath("ToneBlue")) && this.tone != ToningCards.MainTone.BLUE) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("ToneBlue")));
            }

            //添加主色调
            switch (tone) {
                case RED:
                    this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ToneRed(this.owner)));
                    //处理燎原
                    if(AbstractDungeon.player.hasPower(NameAssist.MakePath("PrairieBlaze_pow"))){
                        int amt = this.owner.getPower(NameAssist.MakePath("PrairieBlaze_pow")).amount;
                        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, amt), amt));
                        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new LoseStrengthPower(this.owner, amt), amt));
                    }
                    break;
                case ORANGE:
                    this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ToneOrange(this.owner)));
                    break;
                case YELLOW:
                    this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ToneYellow(this.owner)));
                    break;
                case PURPLE:
                    this.addToBot(new ApplyPowerAction(this.owner, this.owner, new TonePurple(this.owner)));
                    break;
                case GREEN:
                    this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ToneGreen(this.owner)));
                    //处理荆棘秘牢
                    if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ThornsLayer_pow"))){
                        AbstractPower p = AbstractDungeon.player.getPower(NameAssist.MakePath("ThornsLayer_pow"));
                        this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new ThornsPower(AbstractDungeon.player,p.amount)));
                    }
                    break;
                case BLUE:
                    this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ToneBlue(this.owner)));
                    break;
            }

            //处理丛生蔓叶
            Predicate<AbstractCard> GS = AbstractCard -> Objects.equals(AbstractCard.cardID, NameAssist.MakePath("GrassShield"));
            this.addToBot(new MoveCardsAction(AbstractDungeon.player.drawPile,AbstractDungeon.player.discardPile,GS));
            AbstractDungeon.player.drawPile.shuffle();

            //处理洗笔桶
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (Objects.equals(c.cardID, NameAssist.MakePath("WashBucket"))) {
                    this.addToBot(new ModifyDamageAction(c.uuid, c.magicNumber));
                }
            }
        }

        this.isDone = true;
    }


}
