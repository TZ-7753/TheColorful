package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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

        if (effect > 0) {
            this.p.energy.use(EnergyPanel.totalCount);
            this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, (6 * effect), DamageInfo.DamageType.HP_LOSS, AttackEffect.LIGHTNING));
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
            this.addToBot(new GainEnergyAction(effect - 1));
        }

        this.isDone = true;
    }
}
