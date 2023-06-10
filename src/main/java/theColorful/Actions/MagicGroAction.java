package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theColorful.Cards.Quicksand_TC;
import theColorful.Cards.SandstoneBarrier_TC;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static theColorful.characters.TC_character.Enums.SPROUT;

public class MagicGroAction extends AbstractGameAction {
    private boolean upgraded;
    public MagicGroAction(boolean upgraded) {
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> list = new ArrayList();
        AbstractCard c = null;

        Iterator var2 = srcCommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(SPROUT)) {
                list.add(c);
            }
        }

        var2 = srcUncommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(SPROUT)) {
                list.add(c);
            }
        }

        var2 = srcRareCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(SPROUT)) {
                list.add(c);
            }
        }

        if(!list.isEmpty()){
            c = list.get(cardRandomRng.random(list.size() - 1));
        }

        if(this.upgraded && c != null){
            c.upgrade();
        }

        this.addToBot(new MakeTempCardInDrawPileAction(c,1,true,true,false));
        this.isDone = true;
    }
}
