package theColorful.Cards.Sprout;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Cards.ReTone;
import theColorful.Helpers.NameAssist;

import java.util.ArrayList;

import static theColorful.characters.TC_character.Enums.SPROUT;
import static theColorful.characters.TC_character.Enums.TC_CARD;

public class Leafgem extends ToningCards {
    public static final String ID = NameAssist.MakePath("Leafgem");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    //private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/Leafgem.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final MainTone TONE = MainTone.GREEN;


    public Leafgem() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(SPROUT);
        AbstractCard c1 = new Branch();
        AbstractCard c2 = new Bud();
        MultiCardPreview.add(this, c1, c2);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            MultiCardPreview.clear(this);
            AbstractCard c1 = new Branch();
            AbstractCard c2 = new Bud();
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
        AbstractCard c0 = new ReTone();
        AbstractCard c1 = new Branch();
        AbstractCard c2 = new Bud();
        if(this.upgraded){
            c0.upgrade();
            c1.upgrade();
            c2.upgrade();
        }
        this.addToBot(new MakeTempCardInHandAction(c0));
        if(p.hasPower(NameAssist.MakePath("ToneYellow")) || p.hasPower(NameAssist.MakePath("ToneGreen")) || p.hasPower(NameAssist.MakePath("ToneBlue"))){
            this.exhaust = true;
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            stanceChoices.add(c1);
            stanceChoices.add(c2);
            this.addToBot(new ChooseOneAction(stanceChoices));
        }
    }


    public AbstractCard makeCopy(){
        return new Leafgem();
    }
}
