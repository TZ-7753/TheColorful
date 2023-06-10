package theColorful.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.ConstructForm_pow;
import theColorful.Powers.Creation_pow;
import theColorful.Powers.Ink_pow;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class ConstructForm extends CustomCard {
    public static final String ID = NameAssist.MakePath("ConstructForm");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/ConstructForm.png";
    private static final int COST = 3;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public ConstructForm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p,new ConstructForm_pow(p,this.magicNumber)));
        this.addToBot(new ApplyPowerAction(p,p,new Ink_pow(p,this.magicNumber)));
    }

    public AbstractCard makeCopy(){
        return new ConstructForm();
    }
}
