package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

import java.util.ArrayList;
import java.util.Iterator;

public class ConstructFormAction extends AbstractGameAction {
    public ConstructFormAction() {}
    @Override
    public void update() {
        //构念形态
        if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ConstructForm_pow"))){
            ArrayList<AbstractCard> groupCopy = new ArrayList<>();
            Iterator var4 = AbstractDungeon.player.hand.group.iterator();
            while(true) {
                int amt = AbstractDungeon.player.getPower(NameAssist.MakePath("ConstructForm_pow")).amount;
                for(int i = 0; i < amt; i++) {
                    while(var4.hasNext()) {
                        AbstractCard c = (AbstractCard)var4.next();
                        if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce) {
                            groupCopy.add(c);
                        }
                    }
                    var4 = AbstractDungeon.actionManager.cardQueue.iterator();
                    while(var4.hasNext()) {
                        CardQueueItem cc = (CardQueueItem)var4.next();
                        if (cc.card != null) {
                            groupCopy.remove(cc.card);
                        }
                    }
                    AbstractCard c = null;
                    if (!groupCopy.isEmpty()) {
                        c = (AbstractCard) groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
                    }
                    if (c != null && c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce) {
                        c.setCostForTurn(0);
                    }
                }
                break;
            }
        }
        this.isDone = true;
    }
}
