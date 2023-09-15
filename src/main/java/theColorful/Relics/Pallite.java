package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theColorful.Actions.InkCountAction;
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

    public static final String ID = NameAssist.MakePath("Pallite");
    private static final Texture IMG = new Texture("TC_resources/img/relics/pallite.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/pallite.png");
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private boolean used;

    public Pallite() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
        this.counter = 6;
    }

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
                if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneRed")) && !AbstractDungeon.player.hasPower(NameAssist.MakePath("InkRed"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InkRed(AbstractDungeon.player)));
                    this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
                    this.addToBot(new InkCountAction());
                }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneOrange")) && !AbstractDungeon.player.hasPower(NameAssist.MakePath("InkOrange"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,new InkOrange(AbstractDungeon.player)));
                    this.addToBot(new GainEnergyAction(1));
                    this.addToBot(new InkCountAction());
                }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneYellow")) && !AbstractDungeon.player.hasPower(NameAssist.MakePath("InkYellow"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,new InkYellow(AbstractDungeon.player)));
                    this.addToBot(new InkCountAction());
                }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneGreen")) && !AbstractDungeon.player.hasPower(NameAssist.MakePath("InkGreen"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InkGreen(AbstractDungeon.player)));
                    this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 2), 2));
                    this.addToBot(new InkCountAction());
                }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ToneBlue")) && !AbstractDungeon.player.hasPower(NameAssist.MakePath("InkBlue"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InkBlue(AbstractDungeon.player)));
                    this.addToTop(new DrawCardAction(3));
                    this.addToBot(new InkCountAction());
                }else if(AbstractDungeon.player.hasPower(NameAssist.MakePath("TonePurple")) && !AbstractDungeon.player.hasPower(NameAssist.MakePath("InkPurple"))) {
                    this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,new InkPurple(AbstractDungeon.player)));
                    this.addToBot(new ReduceAllDebuffAction(AbstractDungeon.player));
                    this.addToBot(new InkCountAction());
                }
                this.used = true;
                this.flash();
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
