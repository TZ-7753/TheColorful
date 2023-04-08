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
import theColorful.Cards.Arma.Red.CrimsonMeteor;
import theColorful.Cards.Arma.Red.InfernoHull;
import theColorful.Cards.Arma.Red.ReIgnition;
import theColorful.Cards.Arma.Yellow.GoldenSpear;
import theColorful.Cards.Arma.Yellow.MirageCity;
import theColorful.Cards.Arma.Yellow.MirageDoppel;
import theColorful.Relics.Pallite;
import theColorful.StonePile;
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
    public static final Color TC_COLOR = new Color(220.0F / 255.0F, 200.0F / 255.0F, 230.0F / 255.0F, 1.0F);
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

        BaseMod.addCard(new Drought());     //干旱
        BaseMod.addCard(new Cirrus());      //卷云
        BaseMod.addCard(new Dune());        //塑造沙丘
        BaseMod.addCard(new GlimpseofDark());   //一瞬黑暗
        BaseMod.addCard(new Dawn());        //画中朝阳
        BaseMod.addCard(new Desertify());   //沙漠化
        BaseMod.addCard(new RockBreaker()); //碎岩击
        BaseMod.addCard(new LavaFlow());    //熔岩流
        BaseMod.addCard(new StonePile());   //石堆

        BaseMod.addCard(new MudstoneGolem());   //泥岩魔偶
        BaseMod.addCard(new FirstQuarter());    //上弦月
        BaseMod.addCard(new LastQuarter());     //下弦月
        BaseMod.addCard(new SunlightSpear());   //阳光枪
        BaseMod.addCard(new LiveVolcano());     //活火山
        BaseMod.addCard(new Desert());          //大沙漠
        BaseMod.addCard(new HueCalibrator());   //色相校准装置
        BaseMod.addCard(new ClearPallite());    //清空绘盘
        BaseMod.addCard(new RiftValley());      //裂谷

        BaseMod.addCard(new DarknessPaeon());   //深暗赞歌
        BaseMod.addCard(new OpusMagnum());      //尽归巨作
        BaseMod.addCard(new ElDorado());        //黄金国度
        BaseMod.addCard(new AshesPaeon());      //灰烬赞歌
        BaseMod.addCard(new BloodPaeon());      //鲜血赞歌

        BaseMod.addCard(new CrimsonMeteor());   //赤色流星
        BaseMod.addCard(new InfernoHull());     //灼燃机壳
        BaseMod.addCard(new ReIgnition());      //复燃
        BaseMod.addCard(new PrimaryArmaRed());  //原色武装-红
        BaseMod.addCard(new MirageCity());      //蜃楼之城
        BaseMod.addCard(new MirageDoppel());    //幻象双身
        BaseMod.addCard(new GoldenSpear());     //黄金长枪
        BaseMod.addCard(new PrimaryArmaYellow());//原色武装-黄

        BaseMod.addCard(new Minerals());    //化生矿床
        BaseMod.addCard(new UnusedColor()); //弃用杂色
        BaseMod.addCard(new ReTone());      //重新调色
        BaseMod.addCard(new PauseTone());   //暂停调色
        BaseMod.addCard(new Quicksand_TC());    //流沙陷阱
        BaseMod.addCard(new SandstoneBarrier_TC()); //砂岩屏障
        BaseMod.addCard(new Oasis());       //绿洲
    }

    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new TC_character(CardCrawlGame.playerName), TC_CHARACTER_BUTTON, TC_CHARACTER_PORTRAIT, TC_CHARACTER);
    }
    public void receiveEditStrings() {
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "TC_resources/localization/" + lang + "/cards.json"); // 加载相应语言的卡牌本地化内容。
        // 如果是中文，加载的就是"ExampleResources/localization/ZHS/cards.json"
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "TC_resources/localization/" + lang + "/characters.json"); // 加载属于角色的本地化内容。
        BaseMod.loadCustomStringsFile(PowerStrings.class, "TC_resources/localization/" + lang + "/powers.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "TC_resources/localization/" + lang + "/relics.json");
    }

    @Override
    public void receiveEditKeywords() {

        Gson gson = new Gson();
        String lang = "ENG";
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }

        String json = Gdx.files.internal("TC_resources/localization/"+ lang +"/Keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("thecolorful", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }


    }
    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new Pallite(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
    }
}
