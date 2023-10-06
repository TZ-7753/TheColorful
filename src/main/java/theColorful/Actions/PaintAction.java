package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.LosePercentHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.Painted;

public class PaintAction extends AbstractGameAction {
    public AbstractMonster target;
    public PaintAction(AbstractMonster m) {
        this.target = m;
    }

    @Override
    public void update() {
        if (this.target != null) {
            if(AbstractDungeon.player.hasRelic(NameAssist.MakePath("JointFigure")) && this.target.hasPower(NameAssist.MakePath("Painted"))){
                AbstractRelic r = AbstractDungeon.player.getRelic(NameAssist.MakePath("JointFigure"));
                r.flash();
                this.addToBot(new LoseHPAction(this.target,this.target,(int)(this.target.maxHealth * 0.1)));
            }
            else{
                this.addToTop(new ApplyPowerAction(this.target, AbstractDungeon.player, new Painted(this.target)));
            }
        }
        this.tickDuration();
        this.isDone = true;
    }
}
