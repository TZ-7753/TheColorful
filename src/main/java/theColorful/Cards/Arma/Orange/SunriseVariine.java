package theColorful.Cards.Arma.Orange;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Actions.SunriseAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

public class SunriseVariine extends ToningCards {

    public static final String ID = NameAssist.MakePath("SunriseVariine");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/SunriseVariine.png";
    private static final int COST = -1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final MainTone TONE = MainTone.ORANGE;

    public SunriseVariine() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 6;
        this.retain = true;
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
        if(this.upgraded){
            this.addToBot(new SunriseAction(p,(this.energyOnUse + 1)));
        }else{
            this.addToBot(new SunriseAction(p,this.energyOnUse));
        }
    }

    public AbstractCard makeCopy(){
        return new SunriseVariine();
    }

}
