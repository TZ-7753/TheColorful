package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
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

    public static final String ID = NameAssist.MakePath("Painter");
    private static final Texture IMG = new Texture("TC_resources/img/relics/painter.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/painter.png");
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public Painter() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
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
