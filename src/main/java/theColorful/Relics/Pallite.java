package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
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

import java.util.ArrayList;
import java.util.Iterator;

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
                if(!AbstractDungeon.player.hasPower(NameAssist.MakePath("ConstructForm_pow"))){
                    this.used = true;
                }
                this.flash();

                //处理黄金纸
                if(AbstractDungeon.player.hasRelic(NameAssist.MakePath("GoldenPage"))){
                    AbstractRelic page = AbstractDungeon.player.getRelic(NameAssist.MakePath("GoldenPage"));
                    page.flash();
                    this.addToBot(new GainGoldAction(6));
                }

                //构念形态
                if(AbstractDungeon.player.hasPower(NameAssist.MakePath("ConstructForm_pow"))){
                    ArrayList<AbstractCard> groupCopy = new ArrayList<>();
                    Iterator var4 = AbstractDungeon.player.hand.group.iterator();
                    while(true) {
                        while(var4.hasNext()) {
                            AbstractCard c = (AbstractCard)var4.next();
                            if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce) {
                                groupCopy.add(c);
                            }
                        }
                        var4 = AbstractDungeon.actionManager.cardQueue.iterator();
                        while(var4.hasNext()) {
                            CardQueueItem i = (CardQueueItem)var4.next();
                            if (i.card != null) {
                                groupCopy.remove(i.card);
                            }
                        }
                        AbstractCard c = null;
                        int amt = AbstractDungeon.player.getPower(NameAssist.MakePath("ConstructForm_pow")).amount;
                        for(int i = 0; i < amt; i++) {
                            if (!groupCopy.isEmpty()) {
                                c = (AbstractCard) groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
                            }
                            if (c != null) {
                                c.setCostForTurn(0);
                            }
                        }
                        break;
                    }
                }

            }
        }
    }

    public void onMonsterDeath(AbstractMonster m) {
        if (m.isDying && !m.halfDead) {
            if(m.hasPower(NameAssist.MakePath("Painted"))) {
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(m, this));
                if(AbstractDungeon.player.hasRelic(NameAssist.MakePath("PainterW"))){
                    AbstractDungeon.player.getRelic(NameAssist.MakePath("PainterW")).flash();
                    this.counter += 2;
                }else{
                    this.counter++;
                }
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
