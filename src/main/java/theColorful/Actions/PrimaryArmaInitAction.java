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
        for (AbstractCard c : p.discardPile.group) {
            if (c.cardID.contains("PrimaryArma") && !c.cardID.equals(this.ID)) {
                this.addToTop(new ExhaustSpecificCardAction(c, p.discardPile));
            }
        }
        for (AbstractCard c : p.hand.group) {
            if (c.cardID.contains("PrimaryArma") && !c.cardID.equals(this.ID)) {
                this.addToTop(new ExhaustSpecificCardAction(c, p.hand));
            }
        }
        this.isDone = true;
    }
}
