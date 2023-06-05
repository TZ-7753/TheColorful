package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;

import java.util.Iterator;

public class DrawMaterialsAction extends AbstractGameAction {
    private int amount;
    private AbstractPlayer player;
    public DrawMaterialsAction(AbstractPlayer p,int amt) {
        this.player = p;
        this.amount = amt;
    }

    @Override
    public void update() {
        if(this.player.hasRelic(NameAssist.MakePath("Pallite"))) {
            AbstractRelic r = this.player.getRelic(NameAssist.MakePath("Pallite"));
            r.counter += this.amount;
        }
        this.isDone = true;
    }
}
