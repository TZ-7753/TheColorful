package theColorful.Cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class StonePile extends ToningCards {

    public static final String ID = NameAssist.MakePath("StonePile");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/StonePile.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final MainTone TONE = MainTone.RED;


    public StonePile() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = 6;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(3);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p,this.block));
        if(p.hasPower(NameAssist.MakePath("ToneRed")) || p.hasPower(NameAssist.MakePath("ToneOrange")) || p.hasPower(NameAssist.MakePath("TonePurple"))){
            this.addToBot(new ApplyPowerAction(p,p,new NextTurnBlockPower(p, this.block), this.block));
        }
    }


    public AbstractCard makeCopy(){
        return new StonePile();
    }
}
