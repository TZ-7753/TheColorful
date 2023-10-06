package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

public class AuroraAction extends AbstractGameAction {
    public AuroraAction() {}

    @Override
    public void update() {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if(!mo.isDying && !mo.isDead && !mo.escaped){
                if(mo.hasPower("Painted_TC")){
                    this.addToBot(new LoseHPAction(mo,mo,(int)(mo.currentHealth * 0.25),AttackEffect.FIRE));
                }else{
                    this.addToBot(new PaintAction(mo));
                }
            }
        }

        this.isDone = true;
    }
}
