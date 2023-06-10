package theColorful.Cards.Arma.Green;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Actions.OvergrowAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Cards.Choices.ProfusionInk;
import theColorful.Cards.Choices.ProfusionSprout;
import theColorful.Cards.Sprout.ColossalMatsutake;
import theColorful.Cards.Sprout.Decomposer;
import theColorful.Helpers.NameAssist;

import java.util.ArrayList;

public class Overgrow extends ToningCards {

    public static final String ID = NameAssist.MakePath("Overgrow");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/Overgrow.png";
    private static final int COST = 3;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final MainTone TONE = MainTone.GREEN;

    public Overgrow() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new OvergrowAction(p));
    }


    public AbstractCard makeCopy(){
        return new Overgrow();
    }

}
