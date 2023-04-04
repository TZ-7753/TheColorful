package theColorful.Powers.Field;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Cards.Quicksand_TC;
import theColorful.Cards.SandstoneBarrier_TC;
import theColorful.Helpers.NameAssist;

public class Desert_pow extends AbstractPower{
    // 能力的ID
    public static final String POWER_ID = NameAssist.MakePath("Desert_pow");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Desert_pow(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = amount;

        String path128 = "TC_resources/img/powers/dummy2.png";
        String path48 = "TC_resources/img/powers/dummy2.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }


    public void atStartOfTurnPostDraw(){
        if(this.owner.hasPower(NameAssist.MakePath("ToneOrange")) || this.owner.hasPower(NameAssist.MakePath("ToneYellow")) || this.owner.hasPower(NameAssist.MakePath("ToneGreen"))){
            this.addToBot(new MakeTempCardInHandAction(new Quicksand_TC(),this.amount));
        }else{
            this.addToBot(new MakeTempCardInHandAction(new SandstoneBarrier_TC(),this.amount));
            this.addToBot(new ToningAction(AbstractDungeon.player, ToningCards.MainTone.YELLOW));
        }
    }

    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
