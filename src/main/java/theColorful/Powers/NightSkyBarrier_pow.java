package theColorful.Powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theColorful.Helpers.NameAssist;

public class NightSkyBarrier_pow extends AbstractPower {

    public static final String POWER_ID = NameAssist.MakePath("NightSkyBarrier_pow");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NightSkyBarrier_pow(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.amount = amount;

        String path128 = "TC_resources/img/powers/dummy2.png";
        String path48 = "TC_resources/img/powers/dummy2.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageType.THORNS && info.type != DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && info.owner.hasPower(NameAssist.MakePath("Painted"))) {
            this.flash();
            return 0;
        }
        return damageAmount;
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }

    public void atEndOfRound() {
        if(this.amount > 1) {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }else{
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }
}
