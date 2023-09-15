package theColorful.Potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import theColorful.Cards.ReTone;
import theColorful.Helpers.NameAssist;
import com.megacrit.cardcrawl.helpers.PowerTip;
import static theColorful.Core.TheColorful.TC_COLOR;

public class BottledThoughts extends AbstractPotion {
    public static final String POTION_ID = NameAssist.MakePath("BottledThoughts");
    private static final PotionStrings potionStrings;

    public BottledThoughts() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.COMMON, PotionSize.CARD, PotionEffect.NONE, Color.PURPLE, (Color)null, (Color)null);
        this.isThrown = false;
        this.labOutlineColor = TC_COLOR;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature abstractCreature) {
        AbstractCard c = new ReTone();
        c.upgrade();
        this.addToBot(new MakeTempCardInHandAction(c,this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 2;
    }

    public AbstractPotion makeCopy() {
        return new BottledThoughts();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(NameAssist.MakePath(BottledThoughts.class.getSimpleName()));
    }
}
