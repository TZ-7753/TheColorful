package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.Ink.*;

public class InkAction extends AbstractGameAction {
    public AbstractPlayer owner;

    public InkAction(AbstractPlayer p) {
        this.owner = p;
    }
    @Override
    public void update() {
        if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneRed"))) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InkRed(AbstractDungeon.player)));
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
        }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneOrange"))) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,new InkOrange(AbstractDungeon.player)));
            this.addToBot(new GainEnergyAction(1));
        }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneYellow"))) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,new InkYellow(AbstractDungeon.player)));
        }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneGreen"))) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InkGreen(AbstractDungeon.player)));
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 2), 2));
        }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneBlue"))) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InkBlue(AbstractDungeon.player)));
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 2), 2));
        }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("TonePurple"))) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,new InkPurple(AbstractDungeon.player)));
            this.addToBot(new ReduceAllDebuffAction(AbstractDungeon.player));
        }

        this.isDone = true;
    }
}
