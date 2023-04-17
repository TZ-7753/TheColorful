package theColorful.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Helpers.NameAssist;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class Peak extends CustomCard {
    public static final String ID = NameAssist.MakePath("Peak");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/Peak.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;


    public Peak() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = 6;
        this.cardsToPreview = new ReTone();
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            AbstractCard c = new ReTone();
            c.upgrade();
            this.cardsToPreview = c;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void triggerOnEndOfPlayerTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCard c = new ReTone();
        if(this.upgraded){
            c.upgrade();
        }
        if(p.hasPower(NameAssist.MakePath("TonePurple")) || p.hasPower(NameAssist.MakePath("ToneRed")) || p.hasPower(NameAssist.MakePath("ToneOrange"))){
            this.addToBot(new GainBlockAction(p,this.block));
        }else{
            this.addToBot(new MakeTempCardInHandAction(c,1));
        }
    }
    public AbstractCard makeCopy(){
        return new Peak();
    }
}
