package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.Iterator;

public class AvalancheAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int energyOnUse = -1;

    public AvalancheAction(AbstractPlayer pl,int energy) {
        this.p = pl;
        this.energyOnUse = energy;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for(int i=0;i<effect;i++){
                this.addToBot(new GainBlockAction(AbstractDungeon.player,6));
            }
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DrawCardNextTurnPower(AbstractDungeon.player,effect)));

            this.p.energy.use(EnergyPanel.totalCount);
        }

        this.isDone = true;
    }
}
