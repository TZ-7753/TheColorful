package theColorful.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Actions.DrawMaterialsAction;
import theColorful.Helpers.NameAssist;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class DrawMaterials extends CustomCard {
    public static final String ID = NameAssist.MakePath("DrawMaterials");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/DrawMaterials.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public DrawMaterials() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 4;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return p.hasRelic(NameAssist.MakePath("Pallite"));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(3);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawMaterialsAction(p,this.magicNumber));
    }

    public AbstractCard makeCopy(){
        return new DrawMaterials();
    }
}
