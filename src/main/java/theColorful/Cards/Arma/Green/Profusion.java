package theColorful.Cards.Arma.Green;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Cards.Choices.ProfusionInk;
import theColorful.Cards.Choices.ProfusionSprout;
import theColorful.Cards.Oasis;
import theColorful.Cards.Sprout.ColossalMatsutake;
import theColorful.Cards.Sprout.Decomposer;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.Blendin_pow;

import java.util.ArrayList;

public class Profusion extends ToningCards {

    public static final String ID = NameAssist.MakePath("Profusion");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/Profusion.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final MainTone TONE = MainTone.GREEN;

    public Profusion() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        AbstractCard c1 = new ProfusionInk();
        AbstractCard c2 = new ProfusionSprout();
        MultiCardPreview.add(this, c1, c2);
        this.isEthereal = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            MultiCardPreview.clear(this);
            AbstractCard c1 = new ProfusionInk();
            AbstractCard c2 = new ProfusionSprout();
            c1.upgrade();
            c2.upgrade();
            MultiCardPreview.add(this, c1, c2);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public void triggerWhenDrawn() {
        this.addToTop(new HealAction(AbstractDungeon.player,AbstractDungeon.player,(int)(AbstractDungeon.player.maxHealth * 0.05F)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        AbstractCard c1 = new ProfusionInk();
        AbstractCard c2 = new ProfusionSprout();
        if (this.upgraded) {
            c1.upgrade();
            c2.upgrade();
        }
        stanceChoices.add(c1);
        stanceChoices.add(c2);
        this.addToBot(new ChooseOneAction(stanceChoices));
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        this.addToTop(new MakeTempCardInDrawPileAction(this,1,true,true,false));
    }
    public AbstractCard makeCopy(){
        return new Profusion();
    }

}
