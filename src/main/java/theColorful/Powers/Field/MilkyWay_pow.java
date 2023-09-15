package theColorful.Powers.Field;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theColorful.Actions.PaintAction;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.Painted;

import java.util.Iterator;

public class MilkyWay_pow extends AbstractPower{

    public static final String POWER_ID = NameAssist.MakePath("MilkyWay_pow");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MilkyWay_pow(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.amount = amount;

        String path128 = "TC_resources/img/powers/MilkyWay_84.png";
        String path48 = "TC_resources/img/powers/MilkyWay_32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }


    public void atStartOfTurn(){
        if(this.owner.hasPower(NameAssist.MakePath("TonePurple")) || this.owner.hasPower(NameAssist.MakePath("ToneRed")) || this.owner.hasPower(NameAssist.MakePath("ToneBlue"))){
            Iterator<AbstractMonster> var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            AbstractMonster mo;
            while(var3.hasNext()) {
                mo = var3.next();
                this.addToBot(new PaintAction(mo));
            }
        }else{
            this.addToBot(new ToningAction(AbstractDungeon.player, ToningCards.MainTone.PURPLE));
            this.addToBot(new GainBlockAction(this.owner,this.amount));
        }
    }

    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
