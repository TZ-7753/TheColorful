package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theColorful.Actions.InkCountAction;
import theColorful.Actions.ReduceAllDebuffAction;
import theColorful.Cards.ReTone;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.Ink.*;

public class Painter extends CustomRelic{
    // 遗物ID
    public static final String ID = NameAssist.MakePath("Painter");
    // 图片路径
    private static final String IMG_PATH = "TC_resources/img/relics/pallite.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public Painter() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Painter();
    }

    public void atBattleStart() {
        this.addToBot(new MakeTempCardInHandAction(new ReTone()));
    }
}
