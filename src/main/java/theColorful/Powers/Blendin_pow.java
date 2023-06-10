package theColorful.Powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import theColorful.Helpers.NameAssist;

import java.util.ArrayList;

public class Blendin_pow extends AbstractPower {
    public static final String POWER_ID = NameAssist.MakePath("Blendin_pow");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Blendin_pow(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.amount = Amount;

        String path128 = "TC_resources/img/powers/dummy2.png";
        String path48 = "TC_resources/img/powers/dummy2.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    public void atStartOfTurn() {
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new BufferPower(this.owner, this.amount)));
        this.addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,NameAssist.MakePath("Blendin_pow")));
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            this.flash();
            this.addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,NameAssist.MakePath("Blendin_pow")));
        }
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
