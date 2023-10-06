package theColorful.Powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

public class Iridescence_pow extends AbstractPower {
    public static final String POWER_ID = NameAssist.MakePath("Iridescence_pow");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Iridescence_pow(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;

        this.amount = -1;

        String path128 = "TC_resources/img/powers/Iridescence_84.png";
        String path48 = "TC_resources/img/powers/Iridescence_32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    public void atEndOfRound() {
        this.amount = EnergyPanel.getCurrentEnergy();
    }

    public void atStartOfTurnPostDraw() {
        this.addToBot(new GainEnergyAction(this.amount));
        this.addToBot(new ToningAction(AbstractDungeon.player, ToningCards.MainTone.ORANGE));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Iridescence_pow")));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }


}
