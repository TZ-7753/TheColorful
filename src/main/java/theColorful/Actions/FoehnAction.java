package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theColorful.Cards.Quicksand_TC;
import theColorful.Cards.SandstoneBarrier_TC;

public class FoehnAction extends AbstractGameAction {
    public AbstractPlayer p;
    public FoehnAction(AbstractPlayer player) {
        this.p = player;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCard c1 = new Quicksand_TC();
        AbstractCard c2 = new SandstoneBarrier_TC();
        for (AbstractCard c : p.drawPile.group) {
            if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
                this.addToBot(new ExhaustSpecificCardAction(c, p.drawPile));
                int j = AbstractDungeon.cardRandomRng.random(10);
                if(j <= 5){
                    this.addToBot(new MakeTempCardInDrawPileAction(c1,1,true,true));
                }else{
                    this.addToBot(new MakeTempCardInDrawPileAction(c2,1,true,true));
                }
            }
        }
        for (AbstractCard c : p.discardPile.group) {
            if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
                this.addToBot(new ExhaustSpecificCardAction(c, p.discardPile));
                int j = AbstractDungeon.cardRandomRng.random(10);
                if(j <= 5){
                    this.addToBot(new MakeTempCardInDiscardAction(c1,1));
                }else{
                    this.addToBot(new MakeTempCardInDiscardAction(c2,1));
                }
            }
        }
        for (AbstractCard c : p.hand.group) {
            if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
                this.addToBot(new ExhaustSpecificCardAction(c, p.hand));
                int j = AbstractDungeon.cardRandomRng.random(10);
                if(j <= 5){
                    this.addToBot(new MakeTempCardInHandAction(c1,1));
                }else{
                    this.addToBot(new MakeTempCardInHandAction(c2,1));
                }
            }
        }
        this.isDone = true;
    }
}
