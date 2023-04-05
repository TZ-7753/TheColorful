package theColorful.Actions;

import basemod.patches.com.megacrit.cardcrawl.cards.CardGroup.MoveToExhaustPileHook;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theColorful.Powers.Painted;

import java.util.Iterator;

public class ClearPalliteAction extends AbstractGameAction {
    public AbstractPlayer target;
    public ClearPalliteAction(AbstractPlayer p) {
        this.target = p;
    }

    @Override
    public void update() {
        Iterator<AbstractCard> var2 = this.target.hand.group.iterator();
        while(var2.hasNext()) {
            AbstractCard c = var2.next();
            if (c.color == AbstractCard.CardColor.COLORLESS) {
                this.addToBot(new ExhaustSpecificCardAction(c,this.target.hand));
                this.addToBot(new DrawCardAction(1));
            }
        }
        this.isDone = true;
    }
}
