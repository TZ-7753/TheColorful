package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class SunriseAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int energyOnUse = -1;

    public SunriseAction(AbstractPlayer pl, int energy) {
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

        if(this.p.hasPower("Pen Nib")){
            effect *= 2;
        }

        if (effect > 0) {
            this.p.energy.use(EnergyPanel.totalCount);
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if(!mo.isDying && !mo.isDead && !mo.escaped){
                    this.addToBot(new LoseHPAction(mo,this.p,(effect * 6)));
                }
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
            this.addToBot(new GainEnergyAction(effect - 1));
        }

        this.isDone = true;
    }
}
