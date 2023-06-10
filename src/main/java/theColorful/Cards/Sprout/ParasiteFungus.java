package theColorful.Cards.Sprout;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;
import static theColorful.characters.TC_character.Enums.SPROUT;
import java.util.ArrayList;

public class ParasiteFungus extends ToningCards {
    public static final String ID = NameAssist.MakePath("ParasiteFungus");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    //private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/ParasiteFungus.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final MainTone TONE = MainTone.GREEN;


    public ParasiteFungus() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 6;
        AbstractCard c1 = new ColossalMatsutake();
        AbstractCard c2 = new Decomposer();
        MultiCardPreview.add(this, c1, c2);
        this.tags.add(SPROUT);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(2);
            MultiCardPreview.clear(this);
            AbstractCard c1 = new ColossalMatsutake();
            AbstractCard c2 = new Decomposer();
            c1.upgrade();
            c2.upgrade();
            MultiCardPreview.add(this, c1, c2);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m,p,new PoisonPower(m,p,this.magicNumber)));
        if(p.hasPower(NameAssist.MakePath("ToneGreen"))) {
            this.exhaust = true;
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            AbstractCard c1 = new ColossalMatsutake();
            AbstractCard c2 = new Decomposer();
            if (this.upgraded) {
                c1.upgrade();
                c2.upgrade();
            }
            stanceChoices.add(c1);
            stanceChoices.add(c2);
            this.addToBot(new ChooseOneAction(stanceChoices));
        }
    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new MakeTempCardInDrawPileAction(this,1,true,true,false));
    }

    public AbstractCard makeCopy(){
        return new ParasiteFungus();
    }
}
