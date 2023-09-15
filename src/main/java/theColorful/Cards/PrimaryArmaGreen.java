package theColorful.Cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Actions.PrimaryArmaInitAction;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Cards.Arma.Green.Blendin;
import theColorful.Cards.Arma.Green.Overgrow;
import theColorful.Cards.Arma.Green.Profusion;
import theColorful.Cards.Arma.Orange.MeridiemChords;
import theColorful.Cards.Arma.Orange.SunriseVariine;
import theColorful.Cards.Arma.Orange.SunsetSonata;
import theColorful.Helpers.NameAssist;

import java.util.ArrayList;
import java.util.List;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class PrimaryArmaGreen extends ToningCards {
    public static final String ID = NameAssist.MakePath("PrimaryArmaGreen");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/PrimaryArmaGreen.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final MainTone TONE = MainTone.GREEN;


    public PrimaryArmaGreen() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.isArma = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new PrimaryArmaInitAction(ID));
        AbstractCard card1 = new Blendin();
        AbstractCard card2 = new Profusion();
        AbstractCard card3 = new Overgrow();
        if(this.upgraded){
            card1.upgrade();
            card2.upgrade();
            card3.upgrade();
        }
        this.addToBot(new MakeTempCardInDrawPileAction(card1,1,true,true,false));
        this.addToBot(new MakeTempCardInDrawPileAction(card2,2,true,true,false));
        this.addToBot(new MakeTempCardInDrawPileAction(card3,1,true,true,false));
        this.addToBot(new ToningAction(p,TONE));
    }

    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[0], cardStrings.EXTENDED_DESCRIPTION[1]));
        return tips;
    }

    public AbstractCard makeCopy(){
        return new PrimaryArmaGreen();
    }

    public void onChoseThisOption() {
        int a = 1;
        if(AbstractDungeon.player.hasRelic("SacredBark")){
            a += 1;
        }
        this.addToBot(new MakeTempCardInHandAction(this,a));
    }
}
