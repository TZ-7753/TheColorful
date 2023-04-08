package theColorful.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.NoTone_pow;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class Dawn extends ToningCards {
    public static final String ID = NameAssist.MakePath("Dawn");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/Dawn.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final MainTone TONE = MainTone.ORANGE;

    public Dawn() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.block = this.baseBlock = 5;
        this.cardsToPreview = new PauseTone();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            AbstractCard c = new PauseTone();
            c.upgrade();
            this.cardsToPreview = c;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = new PauseTone();
        if(this.upgraded){
            c.upgrade();
        }
        this.addToBot(new GainBlockAction(p,this.block));
        if(p.hasPower(NameAssist.MakePath("ToneOrange")) || p.hasPower(NameAssist.MakePath("ToneYellow")) || p.hasPower(NameAssist.MakePath("ToneRed"))){
            this.addToBot(new GainEnergyAction(1));
        }else{
            this.addToBot(new MakeTempCardInHandAction(c));
            this.addToBot(new ToningAction(p,TONE));
        }
    }


    public AbstractCard makeCopy(){
        return new Dawn();
    }
}
