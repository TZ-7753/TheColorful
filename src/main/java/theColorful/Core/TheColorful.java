package theColorful.Core;

import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.BaseMod;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.badlogic.gdx.graphics.Color;

import static com.megacrit.cardcrawl.core.Settings.language;
import static theColorful.characters.TC_character.Enums.TC_CARD;
import static theColorful.characters.TC_character.Enums.TC_CHARACTER;

import theColorful.Cards.*;
import theColorful.Cards.Arma.Blue.Avalanche;
import theColorful.Cards.Arma.Blue.Current;
import theColorful.Cards.Arma.Blue.SpringTide;
import theColorful.Cards.Arma.Blue.TigerShark;
import theColorful.Cards.Arma.Green.Blendin;
import theColorful.Cards.Arma.Green.Overgrow;
import theColorful.Cards.Arma.Green.Profusion;
import theColorful.Cards.Arma.Orange.MeridiemChords;
import theColorful.Cards.Arma.Orange.SunriseVariine;
import theColorful.Cards.Arma.Orange.SunsetSonata;
import theColorful.Cards.Arma.Purple.Aurora;
import theColorful.Cards.Arma.Purple.BlackHole;
import theColorful.Cards.Arma.Purple.NightSkyBarrier;
import theColorful.Cards.Arma.Red.CrimsonMeteor;
import theColorful.Cards.Arma.Red.InfernoCasing;
import theColorful.Cards.Arma.Red.ReIgnition;
import theColorful.Cards.Arma.Yellow.GoldenSpear;
import theColorful.Cards.Arma.Yellow.MirageCity;
import theColorful.Cards.Arma.Yellow.MirageDoppel;
import theColorful.Cards.Sprout.*;
import theColorful.Helpers.NameAssist;
import theColorful.Potions.BottledInk;
import theColorful.Potions.BottledThoughts;
import theColorful.Potions.FretPotion;
import theColorful.Potions.PrimaryExtractive;
import theColorful.Relics.*;
import theColorful.Cards.StonePile;
import theColorful.characters.TC_character;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class TheColorful implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber, EditRelicsSubscriber {
    private static final String TC_CHARACTER_BUTTON = "TC_resources/img/char/Character_Button.png";
    // 人物选择界面的立绘
    private static final String TC_CHARACTER_PORTRAIT = "TC_resources/img/char/Character_Portrait.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "TC_resources/img/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "TC_resources/img/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "TC_resources/img/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "TC_resources/img/char/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "TC_resources/img/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "TC_resources/img/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "TC_resources/img/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "TC_resources/img/char/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENERGY_ORB = "TC_resources/img/char/cost_orb.png";
    public static final Color TC_COLOR = new Color(218.0F / 255.0F, 168.0F / 255.0F, 32.0F / 255.0F, 1.0F);
    public TheColorful(){
        BaseMod.subscribe(this);
        BaseMod.addColor(TC_CARD, TC_COLOR, TC_COLOR, TC_COLOR,
                TC_COLOR, TC_COLOR, TC_COLOR, TC_COLOR, BG_ATTACK_512,
                BG_SKILL_512, BG_POWER_512, ENERGY_ORB, BG_ATTACK_1024,
                BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB
        );
    }
    public static void initialize() {
        new TheColorful();
    }

    @Override
    public void receiveEditCards(){
        BaseMod.addCard(new Strike_TC());   //打击
        BaseMod.addCard(new Defend_TC());   //防御
        BaseMod.addCard(new DustFog_TC());  //尘幕
        BaseMod.addCard(new Swamp_TC());    //化生泥沼

        BaseMod.addCard(new Drought());         //干旱
        BaseMod.addCard(new Cirrus());          //卷云
        BaseMod.addCard(new Dune());            //塑造沙丘
        BaseMod.addCard(new GlimpseofDark());   //一瞬黑暗
        BaseMod.addCard(new Dawn());            //画中朝阳
        BaseMod.addCard(new Desertify());       //沙漠化
        BaseMod.addCard(new RockBreaker());     //碎岩击
        BaseMod.addCard(new LavaFlow());        //熔岩流
        BaseMod.addCard(new StonePile());       //石堆
        BaseMod.addCard(new Peak());            //孤峰
        BaseMod.addCard(new Warmth());          //温暖的火
        BaseMod.addCard(new GrassShield());     //丛生蔓叶
        BaseMod.addCard(new Tar());             //焦油
        BaseMod.addCard(new Drill());           //钻探
        BaseMod.addCard(new ShoalStrike());     //鱼群打击
        BaseMod.addCard(new TideWaves());       //潮汐
        BaseMod.addCard(new Migrate());         //洄游
        BaseMod.addCard(new Draught());         //冻气
        BaseMod.addCard(new VineWhip());        //藤鞭
        BaseMod.addCard(new BirdsStrike());     //鸟群打击
        BaseMod.addCard(new MagicGro());        //催生秘术

        BaseMod.addCard(new MudstoneGolem());   //泥岩魔偶
        BaseMod.addCard(new FirstQuarter());    //上弦月
        BaseMod.addCard(new LastQuarter());     //下弦月
        BaseMod.addCard(new SunlightSpear());   //阳光枪
        BaseMod.addCard(new LiveVolcano());     //活火山
        BaseMod.addCard(new Desert());          //大沙漠
        BaseMod.addCard(new HueCalibrator());   //色相校准装置
        BaseMod.addCard(new ClearPallite());    //清空绘盘
        BaseMod.addCard(new RiftValley());      //裂谷
        BaseMod.addCard(new BirdsWatchout());   //鸟群警戒
        BaseMod.addCard(new WindBreak());       //防风林
        BaseMod.addCard(new StarLight());       //星辉
        BaseMod.addCard(new MilkyWay());        //银河
        BaseMod.addCard(new Spores());          //孢子
        BaseMod.addCard(new Leafgem());         //新叶
        BaseMod.addCard(new PrairieBlaze());    //燎原
        BaseMod.addCard(new GoldCraft());       //黄金造物
        BaseMod.addCard(new Drizzle());         //细雨
        BaseMod.addCard(new GatheringClouds()); //云层聚集
        BaseMod.addCard(new RogueWave());       //巨浪
        BaseMod.addCard(new FrostBite());       //霜凌
        BaseMod.addCard(new Tundra());          //化生冻原
        BaseMod.addCard(new Stream());          //溪流
        BaseMod.addCard(new WashBucket());      //洗笔桶
        BaseMod.addCard(new Iridescence());     //彩云
        BaseMod.addCard(new RosyClouds());      //云霞漫天
        BaseMod.addCard(new Wilderness());      //原野

//        BaseMod.addCard(new DarknessPaeon());   //深暗赞歌
        BaseMod.addCard(new OpusMagnum());      //尽归巨作
        BaseMod.addCard(new ElDorado());        //黄金国度
        BaseMod.addCard(new AshesPaeon());      //灰烬赞歌
        BaseMod.addCard(new BloodPaeon());      //鲜血赞歌
        BaseMod.addCard(new Nirvana());         //涅槃之焰
        BaseMod.addCard(new Inspiration());     //灵感爆发
        BaseMod.addCard(new ScorchingSun());    //烈阳
        BaseMod.addCard(new ImperfectGlacialist()); //粗暴的冰结
        BaseMod.addCard(new Storm());           //暴雨
        BaseMod.addCard(new Enlightning());     //鸣雷一闪
        BaseMod.addCard(new DrawMaterials());   //万界取材
        BaseMod.addCard(new MountainsHeart());  //山脉之心
        BaseMod.addCard(new Creation());        //宇宙创生
        BaseMod.addCard(new ConstructForm());   //构念形态
        BaseMod.addCard(new Foehn());           //焚风
        BaseMod.addCard(new LeavesHail());      //锐叶箭雨

        BaseMod.addCard(new CrimsonMeteor());   //赤色流星
        BaseMod.addCard(new InfernoCasing());     //灼燃机壳
        BaseMod.addCard(new ReIgnition());      //复燃
        BaseMod.addCard(new PrimaryArmaRed());  //原色武装-红
        BaseMod.addCard(new MirageCity());      //蜃楼之城
        BaseMod.addCard(new MirageDoppel());    //幻象双身
        BaseMod.addCard(new GoldenSpear());     //黄金长枪
        BaseMod.addCard(new PrimaryArmaYellow());//原色武装-黄
        BaseMod.addCard(new BlackHole());       //黑洞
        BaseMod.addCard(new NightSkyBarrier()); //夜色帷幕
        BaseMod.addCard(new Aurora());          //极光
        BaseMod.addCard(new PrimaryArmaPurple());//原色武装-紫
        BaseMod.addCard(new TigerShark());      //虎鲨爆破拳
        BaseMod.addCard(new SpringTide());      //碧海潮升
        BaseMod.addCard(new Avalanche());       //雪崩
        BaseMod.addCard(new Current());         //洋流
        BaseMod.addCard(new PrimaryArmaBlue()); //原色武装-蓝
        BaseMod.addCard(new SunriseVariine());  //黎明变奏
        BaseMod.addCard(new MeridiemChords());  //骄阳和弦
        BaseMod.addCard(new SunsetSonata());    //黄昏奏鸣
        BaseMod.addCard(new PrimaryArmaOrange());//原色武装-橙
        BaseMod.addCard(new Blendin());         //藏木于林
        BaseMod.addCard(new Profusion());       //花海
        BaseMod.addCard(new Overgrow());        //野蛮生长
        BaseMod.addCard(new PrimaryArmaGreen());//原色武装-绿


//        BaseMod.addCard(new Minerals());        //化生矿床
        BaseMod.addCard(new UnusedColor());     //弃用杂色
        BaseMod.addCard(new ReTone());          //重新调色
        BaseMod.addCard(new PauseTone());       //暂停调色
        BaseMod.addCard(new Quicksand_TC());    //流沙陷阱
        BaseMod.addCard(new SandstoneBarrier_TC()); //砂岩屏障
        BaseMod.addCard(new Oasis());           //绿洲
        BaseMod.addCard(new ColossalMatsutake());   //巨松茸
        BaseMod.addCard(new Decomposer());      //分解者
        BaseMod.addCard(new ParasiteFungus());  //寄生毒蕈
        BaseMod.addCard(new MegaShroom());      //肥硕大蘑菇
        BaseMod.addCard(new Penicillium());     //青霉菌
        BaseMod.addCard(new Hypha());           //菌丝
        BaseMod.addCard(new RainbowFlower());   //虹色之花
        BaseMod.addCard(new Rafflesia());       //大王花
        BaseMod.addCard(new Bud());             //花苞
        BaseMod.addCard(new MegaTree());        //巨木
        BaseMod.addCard(new ThornsLayer());     //荆棘秘牢
        BaseMod.addCard(new Branch());          //硬枝
        BaseMod.addCard(new VineWhipBig());     //长藤鞭
        BaseMod.addCard(new VineWhipMega());    //巨藤鞭

    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TC_character(CardCrawlGame.playerName), TC_CHARACTER_BUTTON, TC_CHARACTER_PORTRAIT, TC_CHARACTER);
        this.receiveEditPotions();
    }
    public void receiveEditStrings() {
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "TC_resources/localization/" + lang + "/cards.json"); // 加载相应语言的卡牌本地化内容。
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "TC_resources/localization/" + lang + "/characters.json"); // 加载属于角色的本地化内容。
        BaseMod.loadCustomStringsFile(PowerStrings.class, "TC_resources/localization/" + lang + "/powers.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, "TC_resources/localization/" + lang + "/potions.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "TC_resources/localization/" + lang + "/relics.json");
    }

    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "ENG";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }

        String json = Gdx.files.internal("TC_resources/localization/" + lang + "/Keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = (Keyword[])gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            Keyword[] var5 = keywords;
            int var6 = keywords.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Keyword keyword = var5[var7];
                BaseMod.addKeyword("thecolorful", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    public void receiveEditPotions() {
        BaseMod.addPotion(BottledThoughts.class, (Color)null, (Color)null, (Color)null, NameAssist.MakePath(BottledThoughts.class.getSimpleName()), TC_CHARACTER);
        BaseMod.addPotion(BottledInk.class, (Color)null, (Color)null, (Color)null, NameAssist.MakePath(BottledInk.class.getSimpleName()), TC_CHARACTER);
        BaseMod.addPotion(PrimaryExtractive.class, (Color)null, (Color)null, (Color)null, NameAssist.MakePath(PrimaryExtractive.class.getSimpleName()), TC_CHARACTER);
        BaseMod.addPotion(FretPotion.class, (Color)null, (Color)null, (Color)null, NameAssist.MakePath(FretPotion.class.getSimpleName()));
    }
    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new Pallite(), TC_CARD);
        BaseMod.addRelicToCustomPool(new Painter(), TC_CARD);
        BaseMod.addRelic(new MysticPowerCell(),RelicType.SHARED);
        BaseMod.addRelic(new JukeBox(),RelicType.SHARED);
        BaseMod.addRelicToCustomPool(new LivePaint(), TC_CARD);
        BaseMod.addRelic(new BigHorn(),RelicType.SHARED);
        BaseMod.addRelicToCustomPool(new Tripling(),TC_CARD);
        BaseMod.addRelicToCustomPool(new JointFigure(),TC_CARD);
        BaseMod.addRelicToCustomPool(new GoldenPage(),TC_CARD);
    }
}
