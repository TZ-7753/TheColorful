package theColorful.Potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Cards.ReTone;
import theColorful.Helpers.NameAssist;

import static theColorful.Core.TheColorful.TC_COLOR;

public class BottledInk extends AbstractPotion {
    public static final String POTION_ID = NameAssist.MakePath("BottledInk");
    private static final PotionStrings potionStrings;

    public BottledInk() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.H, PotionEffect.NONE, Color.CYAN, Color.MAGENTA, Color.GOLD);
        this.isThrown = false;
        this.labOutlineColor = TC_COLOR;
    }

    public boolean canUse() {
            return AbstractDungeon.getCurrRoom().event == null || !(AbstractDungeon.getCurrRoom().event instanceof WeMeetAgain);
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature abstractCreature) {
        if(AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(NameAssist.MakePath("Pallite"))){
            AbstractRelic r = AbstractDungeon.player.getRelic(NameAssist.MakePath("Pallite"));
            r.flash();
            r.counter += this.potency;
        }
    }

    public int getPotency(int ascensionLevel) {
        return 2;
    }

    public AbstractPotion makeCopy() {
        return new BottledInk();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(NameAssist.MakePath(BottledInk.class.getSimpleName()));
    }
}
