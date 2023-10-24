package theColorful.characters;

import basemod.abstracts.CustomPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.relics.Vajra;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import theColorful.Cards.Strike_TC;
import theColorful.Cards.Defend_TC;
import theColorful.Cards.DustFog_TC;
import theColorful.Cards.Swamp_TC;
import theColorful.Core.TheColorful;
import theColorful.Relics.Painter;
import theColorful.Relics.Pallite;

import java.util.ArrayList;

import static theColorful.characters.TC_character.Enums.TC_CARD;
import static theColorful.characters.TC_character.Enums.TC_CHARACTER;



public class TC_character extends CustomPlayer {
    // 火堆的人物立绘（行动前）
    private static final String TC_CHARACTER_SHOULDER_1 = "TC_resources/img/char/shoulder1.png";
    // 火堆的人物立绘（行动后）
    private static final String TC_CHARACTER_SHOULDER_2 = "TC_resources/img/char/shoulder2.png";
    // 人物死亡图像
    private static final String CORPSE_IMAGE = "TC_resources/img/char/corpse.png";
    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
            "TC_resources/img/UI/orb/layer1.png",
            "TC_resources/img/UI/orb/layer2.png",
            "TC_resources/img/UI/orb/layer3.png",
            "TC_resources/img/UI/orb/layer4.png",
            "TC_resources/img/UI/orb/layer5.png",
            "TC_resources/img/UI/orb/layer6.png",
            "TC_resources/img/UI/orb/layer1d.png",
            "TC_resources/img/UI/orb/layer2d.png",
            "TC_resources/img/UI/orb/layer3d.png",
            "TC_resources/img/UI/orb/layer4d.png",
            "TC_resources/img/UI/orb/layer5d.png"
    };
    // 每个图层的旋转速度
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("TC_character");

    public TC_character(String name) {
        super(name, TC_CHARACTER,ORB_TEXTURES,"TC_resources/img/UI/orb/vfx.png", LAYER_SPEED, null, null);

        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);

        this.initializeClass(
                "TC_resources/img/char/character.png",
                TC_CHARACTER_SHOULDER_2, TC_CHARACTER_SHOULDER_1,
                CORPSE_IMAGE, // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F,
                new EnergyManager(3)
        );

        // 如果你的人物没有动画，那么这些不需要写
        // this.loadAnimation("TC_resources/img/char/character.atlas", "TC_resources/img/char/character.json", 1.8F);
        // AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        // e.setTime(e.getEndTime() * MathUtils.random());
        // e.setTimeScale(1.2F);
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Strike_TC.ID);
        retVal.add(Strike_TC.ID);
        retVal.add(Strike_TC.ID);
        retVal.add(Strike_TC.ID);
        retVal.add(DustFog_TC.ID);
        retVal.add(Defend_TC.ID);
        retVal.add(Defend_TC.ID);
        retVal.add(Defend_TC.ID);
        retVal.add(Defend_TC.ID);
        retVal.add(Swamp_TC.ID);
        return retVal;
    }


    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Painter.ID);
        retVal.add(Pallite.ID);
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                characterStrings.NAMES[0], // 人物名字
                characterStrings.TEXT[0], // 人物介绍
                70, // 当前血量
                70, // 最大血量
                0, // 初始充能球栏位
                99, // 初始携带金币
                5, // 每回合抽牌数量
                this, // 别动
                this.getStartingRelics(), // 初始遗物
                this.getStartingDeck(), // 初始卡组
                false // 别动
        );
    }

    // 人物名字（出现在游戏左上角）
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    // 你的卡牌颜色（这个枚举在最下方创建）
    public AbstractCard.CardColor getCardColor() {
        return TC_CARD;
    }

    // 翻牌事件出现的你的职业牌（一般设为打击）
    public AbstractCard getStartCardForEvent() {
        return new Strike_TC();
    }

    // 卡牌轨迹颜色
    public Color getCardTrailColor() {
        return TheColorful.TC_COLOR;
    }

    // 高进阶带来的生命值损失
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // 卡牌的能量字体，没必要修改
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    // 人物选择界面点击你的人物按钮时触发的方法，这里为屏幕轻微震动
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    // 碎心图片
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        // 有两个参数的，第二个参数表示出现图片时播放的音效
        panels.add(new CutscenePanel("TC_resources/img/char/Victory1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("TC_resources/img/char/Victory2.png"));
        panels.add(new CutscenePanel("TC_resources/img/char/Victory3.png"));
        return panels;
    }

    // 自定义模式选择你的人物时播放的音效
    public String getCustomModeCharacterButtonSoundKey() {
        return "SMASH";
    }

    // 游戏中左上角显示在你的名字之后的人物名称
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    // 创建人物实例，照抄
    public AbstractPlayer newInstance() {
        return new TC_character(this.name);
    }

    // 第三章面对心脏说的话（例如战士是“你握紧了你的长刀……”之类的）
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    // 打心脏的颜色，不是很明显
    public Color getSlashAttackColor() {
        return TheColorful.TC_COLOR;
    }

    // 吸血鬼事件文本，主要是他（索引为0）和她（索引为1）的区别（机器人另外）
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    // 卡牌选择界面选择该牌的颜色
    public Color getCardRenderColor() {
        return TheColorful.TC_COLOR;
    }

    // 第三章面对心脏造成伤害时的特效
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    // 为原版人物枚举、卡牌颜色枚举扩展的枚举，需要写，接下来要用
    // ***填在SpireEnum中的name需要一致***
    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass TC_CHARACTER;

        @SpireEnum(name = "TC_COLOR")
        public static AbstractCard.CardColor TC_CARD;

        @SpireEnum(name = "TC_COLOR")
        public static CardLibrary.LibraryType TC_LIBRARY;

        @SpireEnum
        public static AbstractCard.CardTags SPROUT;

        public Enums() {
        }
    }

}

