package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

import java.util.Iterator;

public class InkCountAction extends AbstractGameAction {
    public InkCountAction() {}
    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasRelic(NameAssist.MakePath("Pallite"))){
            if(p.hasPower(NameAssist.MakePath("Ink_pow"))){
                this.addToTop(new ReducePowerAction(p,p,NameAssist.MakePath("Ink_pow"),1));
            }else{
                AbstractRelic r = p.getRelic(NameAssist.MakePath("Pallite"));
                r.counter--;
            }
        }
        this.isDone = true;
    }
}
