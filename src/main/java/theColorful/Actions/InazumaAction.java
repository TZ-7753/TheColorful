package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theColorful.Powers.Painted;

import java.util.Iterator;

public class InazumaAction extends AbstractGameAction {
    private boolean upgraded;
    private int damage;
    public InazumaAction(boolean upg,int dmg) {
        this.upgraded = upg;
        this.damage = dmg;
    }

    @Override
    public void update() {
        int total = 0;

        AbstractPlayer p = AbstractDungeon.player;
        if(upgraded) {
            total += p.powers.size();
        }else{
        Iterator<AbstractPower> var1 = p.powers.iterator();
            while(var1.hasNext()){
                AbstractPower pow = var1.next();
                if(pow.type == AbstractPower.PowerType.DEBUFF){
                    total ++;
                }
            }
        }

        int tmp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        for(int i = 0; i < tmp; i++){
            if (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDeadOrEscaped()) {
                AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if(upgraded){
                    total += m.powers.size();
                }else{
                    for (AbstractPower pow : m.powers) {
                        if (pow.type == AbstractPower.PowerType.DEBUFF) {
                            total++;
                        }
                    }
                }
            }
        }

        if(total != 0){
            this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, (total * this.damage), DamageInfo.DamageType.NORMAL, AttackEffect.LIGHTNING));
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.isDone = true;
    }
}
