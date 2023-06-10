package theColorful.Cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class WindBreak extends ToningCards {
    public static final String ID = NameAssist.MakePath("WindBreak");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/WindBreak.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final MainTone TONE = MainTone.GREEN;


    public WindBreak() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.upgradeMagicNumber(1);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void triggerOnEndOfPlayerTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower(NameAssist.MakePath("ToneYellow")) || p.hasPower(NameAssist.MakePath("ToneGreen")) || p.hasPower(NameAssist.MakePath("ToneBlue"))){
            this.addToBot(new ApplyPowerAction(p,p,new PlatedArmorPower(p,3)));
        }else{
            this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,1)));
        }
        if(!this.upgraded) {
            this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }
    }
    public AbstractCard makeCopy(){
        return new WindBreak();
    }
}
