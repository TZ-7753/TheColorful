package theColorful.Cards.Arma.Yellow;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Cards.Choices.AshesShelter;
import theColorful.Cards.Choices.AshesStorm;
import theColorful.Cards.Oasis;
import theColorful.Cards.Quicksand_TC;
import theColorful.Cards.SandstoneBarrier_TC;
import theColorful.Helpers.NameAssist;

import java.util.ArrayList;

public class MirageCity extends ToningCards {
    public static final String ID = NameAssist.MakePath("MirageCity");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/MirageCity.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final MainTone TONE = MainTone.YELLOW;


    public MirageCity() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
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
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        AbstractCard c1 = new Quicksand_TC();
        AbstractCard c2 = new SandstoneBarrier_TC();
        if(this.upgraded){
            c1.upgrade();
            c2.upgrade();
        }
        stanceChoices.add(c1);
        stanceChoices.add(c2);
        this.addToBot(new ChooseOneAction(stanceChoices));
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        this.addToBot(new MakeTempCardInDiscardAction(this,1));
    }

    public AbstractCard makeCopy(){
        return new MirageCity();
    }
}
