package theColorful.Powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

public class MountainsHeart_pow extends AbstractPower {
    public static final String POWER_ID = NameAssist.MakePath("MountainsHeart_pow");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MountainsHeart_pow(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        this.amount = amount;

        String path128 = "TC_resources/img/powers/MountainsHeart_84.png";
        String path48 = "TC_resources/img/powers/MountainsHeart_32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(power.ID.equals("Strength") && target == this.owner && power.amount > 0){
            if(AbstractDungeon.player.hasPower("Regeneration")){
                if(AbstractDungeon.player.getPower("Regeneration").amount < this.amount) {
                    this.flash();
                    this.addToBot(new ApplyPowerAction(this.owner, this.owner, new RegenPower(this.owner, 1)));
                }
            }else{
                this.flash();
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new RegenPower(this.owner, 1)));
            }
        }
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }


}
