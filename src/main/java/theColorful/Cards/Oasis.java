package theColorful.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import theColorful.Helpers.NameAssist;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class Oasis extends CustomCard {
    public static final String ID = NameAssist.MakePath("Oasis");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    //private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/Oasis.png";
    private static final int COST = 1;
    //private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;


    public Oasis() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.isEthereal = true;
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 3;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new HealAction(p,p,this.magicNumber));
    }

    public AbstractCard makeCopy(){
        return new Oasis();
    }
}
