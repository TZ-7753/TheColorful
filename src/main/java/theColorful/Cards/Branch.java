package theColorful.Cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

import java.util.ArrayList;

public class Branch extends ToningCards {
    public static final String ID = NameAssist.MakePath("Branch");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/Branch.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final MainTone TONE = MainTone.GREEN;


    public Branch() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.sprout = true;
        this.block = this.baseBlock = 4;
        AbstractCard c1 = new MegaTree();
        AbstractCard c2 = new ThornsLayer();
        MultiCardPreview.add(this, c1, c2);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(1);
            MultiCardPreview.clear(this);
            AbstractCard c1 = new MegaTree();
            AbstractCard c2 = new ThornsLayer();
            c1.upgrade();
            c2.upgrade();
            MultiCardPreview.add(this, c1, c2);
            this.upgradeMagicNumber(1);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p,this.block));
        this.addToBot(new GainBlockAction(p,this.block));
        if(p.hasPower(NameAssist.MakePath("ToneGreen"))){
            this.exhaust = true;
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            AbstractCard c1 = new MegaTree();
            AbstractCard c2 = new ThornsLayer();
            if(this.upgraded){
                c1.upgrade();
                c2.upgrade();
            }
            stanceChoices.add(c1);
            stanceChoices.add(c2);
            this.addToBot(new ChooseOneAction(stanceChoices));
        }
    }

    public void onChoseThisOption() {
        this.addToBot(new MakeTempCardInDrawPileAction(this,1,true,true,false));
    }

    public AbstractCard makeCopy(){
        return new Branch();
    }
}
