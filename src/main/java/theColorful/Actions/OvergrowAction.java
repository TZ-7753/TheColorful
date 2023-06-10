package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theColorful.Cards.Quicksand_TC;
import theColorful.Cards.SandstoneBarrier_TC;

import java.util.Iterator;

public class OvergrowAction extends AbstractGameAction {
    public AbstractPlayer p;
    public OvergrowAction(AbstractPlayer player) {
        this.p = player;
    }

    @Override
    public void update() {
        for (AbstractCard c : this.p.hand.group) {
            AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster) null, true, AbstractDungeon.cardRandomRng);
            if (c.cost >= 0){
                this.addToTop(new NewQueueCardAction(c, m, false, true));
                if(c.cost > 0){
                    if(c.cost <= EnergyPanel.getCurrentEnergy()){
                        this.addToBot(new LoseEnergyAction(c.cost));
                    }else{
                        int diff = c.cost - EnergyPanel.getCurrentEnergy();
                        this.addToBot(new LoseEnergyAction(EnergyPanel.getCurrentEnergy()));
                        this.addToBot(new LoseHPAction(this.p,this.p,diff));
                    }
                }
            }
        }
        //this.addToBot(new PlayTopCardAction());
        this.isDone = true;
    }
}
