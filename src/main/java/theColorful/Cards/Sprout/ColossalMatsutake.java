package theColorful.Cards.Sprout;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

import java.util.Iterator;

public class ColossalMatsutake extends ToningCards {
    public static final String ID = NameAssist.MakePath("ColossalMatsutake");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    //private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/ColossalMatsutake.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final MainTone TONE = MainTone.GREEN;


    public ColossalMatsutake() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 4;
        this.block = this.baseBlock = 4;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(2);
            this.upgradeMagicNumber(1);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator<AbstractMonster> var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = var3.next();
            this.addToBot(new ApplyPowerAction(mo, p, new PoisonPower(mo,p,this.magicNumber)));
        }
        this.addToBot(new GainBlockAction(p,this.block));
        this.addToBot(new MakeTempCardInDiscardAction(this,1));
    }

    public void triggerOnManualDiscard() {
        this.addToTop(new ExhaustSpecificCardAction(this,AbstractDungeon.player.discardPile));
    }

    public void onChoseThisOption() {
        this.addToBot(new MakeTempCardInDrawPileAction(this,1,true,true,false));
    }
    public AbstractCard makeCopy(){
        return new ColossalMatsutake();
    }
}
