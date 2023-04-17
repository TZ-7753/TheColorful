package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theColorful.Actions.ReduceAllDebuffAction;
import theColorful.Helpers.NameAssist;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import theColorful.Powers.Ink.*;

public class Pallite extends CustomRelic implements ClickableRelic {
    // 遗物ID
    public static final String ID = NameAssist.MakePath("Pallite");
    // 图片路径
    private static final String IMG_PATH = "TC_resources/img/relics/pallite.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    private boolean used;

    public boolean firstTone;

    public Pallite() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
        this.counter = 6;
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Pallite();
    }

    @Override
    public void onRightClick() {
        //点击时根据当前色调降低计数产生活墨pow
        if(AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {

            if (this.counter >= 1 && !this.used) {
                this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneRed"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InkRed(AbstractDungeon.player)));
                    this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
                }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneOrange"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,new InkOrange(AbstractDungeon.player)));
                    this.addToBot(new GainEnergyAction(1));
                }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneYellow"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,new InkYellow(AbstractDungeon.player)));
                }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneGreen"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InkGreen(AbstractDungeon.player)));
                    this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 2), 2));
                }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneBlue"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InkBlue(AbstractDungeon.player)));
                    this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 2), 2));
                }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("TonePurple"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,new InkPurple(AbstractDungeon.player)));
                    this.addToBot(new ReduceAllDebuffAction(AbstractDungeon.player));
                }
                this.used = true;
                this.flash();
                this.counter--;
            }


        }
    }

    public void onMonsterDeath(AbstractMonster m) {
        if (m.isDying && !m.halfDead) {
            if(m.hasPower(NameAssist.MakePath("Painted"))) {
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(m, this));
                this.counter++;
            }
        }
    }

    public void atPreBattle() {
        this.used = false;
    }

    public void atTurnStart() {
        this.used = false;
    }
}
