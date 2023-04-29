package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theColorful.Cards.Abstract.ToningCards;

import java.util.Iterator;

public class PrimaryArmaInitAction extends AbstractGameAction {
    public String ID;
    public PrimaryArmaInitAction(String ID) {
        this.ID = ID;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        for (AbstractCard c : p.drawPile.group) {
            if (c.cardID.contains("PrimaryArma") && !c.cardID.equals(this.ID)) {
                this.addToTop(new ExhaustSpecificCardAction(c, p.drawPile));
            }
        }
        Iterator<AbstractCard> var6 = p.discardPile.group.iterator();
        while(var6.hasNext()){
            AbstractCard c = var6.next();
            if(c.cardID.contains("PrimaryArma") && !c.cardID.equals(this.ID)){
                this.addToTop(new ExhaustSpecificCardAction(c,p.discardPile));
            }
        }
        Iterator<AbstractCard> var7 = p.hand.group.iterator();
        while(var7.hasNext()){
            AbstractCard c = var7.next();
            if(c.cardID.contains("PrimaryArma") && !c.cardID.equals(this.ID)){
                this.addToTop(new ExhaustSpecificCardAction(c,p.hand));
            }
        }
        this.isDone = true;
    }
}
