package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

public class ReduceAllDebuffAction extends AbstractGameAction {
    public AbstractPlayer owner;
    public ReduceAllDebuffAction(AbstractPlayer p) {
        this.owner = p;
    }

    @Override
    public void update() {
        Iterator<AbstractPower> var5 = this.owner.powers.iterator();
        while(var5.hasNext()){
            AbstractPower p = var5.next();
            if(p.type == AbstractPower.PowerType.DEBUFF && p.amount != -1){
                if(p.amount>1){
                    p.amount-=1;
                }else if(p.amount == 1){
                    this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, p.ID));
                }
            }
        }
        this.isDone = true;
    }
}
