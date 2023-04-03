package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theColorful.Powers.Painted;

import java.util.Iterator;

public class PaintRandomEnemyAction extends AbstractGameAction {
    public AbstractMonster target;
    public PaintRandomEnemyAction(AbstractMonster m) {
        this.target = m;
    }

    @Override
    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this.target, new Painted(this.target)));
        }
        this.isDone = true;
    }
}
