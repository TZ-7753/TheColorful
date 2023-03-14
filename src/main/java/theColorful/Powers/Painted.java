package theColorful.Powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theColorful.Helpers.NameAssist;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;

public class Painted extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = NameAssist.MakePath("Painted");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Painted(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;

        // 添加一大一小两张能力图
        String path128 = "TC_resources/img/powers/TonePurple84.png";
        String path48 = "TC_resources/img/powers/TonePurple32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 22, 22, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 8, 8, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }

    public void atEndOfRound() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Painted")));
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageType.NORMAL) {
            if(AbstractDungeon.player.hasPower(NameAssist.MakePath("OpusMagnum_pow"))){
                return damage * 2.00000000F;
            }else {
                return damage * 1.5F;
            }
        } else {
            return damage;
        }
    }

    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if(AbstractDungeon.player.hasPower(NameAssist.MakePath("OpusMagnum_pow"))){
            this.description = DESCRIPTIONS[1];
        }
    }
}
