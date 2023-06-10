package theColorful.Cards.Arma.Orange;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Actions.SunriseAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

public class SunsetSonata extends ToningCards {

    public static final String ID = NameAssist.MakePath("SunsetSonata");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/SunsetSonata.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final MainTone TONE = MainTone.ORANGE;

    public SunsetSonata() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = true;
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
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
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if(AbstractDungeon.player.hand.contains(this)){
            this.addToBot(new GainEnergyAction(this.magicNumber));
        }
    }

    public AbstractCard makeCopy(){
        return new SunsetSonata();
    }

}
