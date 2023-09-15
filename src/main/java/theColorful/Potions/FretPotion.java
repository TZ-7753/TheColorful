package theColorful.Potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theColorful.Cards.*;
import theColorful.Helpers.NameAssist;

import java.util.ArrayList;

import static theColorful.Core.TheColorful.TC_COLOR;

public class FretPotion extends AbstractPotion {
    public static final String POTION_ID = NameAssist.MakePath("FretPotion");
    private static final PotionStrings potionStrings;

    public FretPotion() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.COMMON, PotionSize.S, PotionEffect.NONE, Color.CORAL, Color.FIREBRICK, Color.DARK_GRAY);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature abstractCreature) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new VigorPower(AbstractDungeon.player,this.potency)));
    }

    public int getPotency(int ascensionLevel) {
        return 12;
    }

    public AbstractPotion makeCopy() {
        return new FretPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(NameAssist.MakePath(FretPotion.class.getSimpleName()));
    }
}
