package theColorful.Potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import theColorful.Cards.*;
import theColorful.Helpers.NameAssist;

import java.util.ArrayList;

import static theColorful.Core.TheColorful.TC_COLOR;

public class PrimaryExtractive extends AbstractPotion {
    public static final String POTION_ID = NameAssist.MakePath("PrimaryExtractive");
    private static final PotionStrings potionStrings;

    public PrimaryExtractive() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.RARE, PotionSize.FAIRY, PotionEffect.RAINBOW, Color.WHITE, (Color)null, (Color)null);
        this.isThrown = false;
        this.labOutlineColor = TC_COLOR;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
            this.description = potionStrings.DESCRIPTIONS[1];
        } else {
            this.description = potionStrings.DESCRIPTIONS[0];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature abstractCreature) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new PrimaryArmaBlue());
        stanceChoices.add(new PrimaryArmaGreen());
        stanceChoices.add(new PrimaryArmaOrange());
        stanceChoices.add(new PrimaryArmaRed());
        stanceChoices.add(new PrimaryArmaPurple());
        stanceChoices.add(new PrimaryArmaYellow());
        this.addToBot(new ChooseOneAction(stanceChoices));
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new PrimaryExtractive();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(NameAssist.MakePath(PrimaryExtractive.class.getSimpleName()));
    }
}
