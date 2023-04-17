package theColorful.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theColorful.Actions.ApplyFieldAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.Field.Desert_pow;
import theColorful.Powers.NoTone_pow;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class BirdsWatchout extends ToningCards {
    public static final String ID = NameAssist.MakePath("BirdsWatchout");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/BirdsWatchout.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final MainTone TONE = MainTone.ORANGE;

    public BirdsWatchout() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = 5;
        this.magicNumber = this.baseMagicNumber = 1;
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
        if(p.hasPower(NameAssist.MakePath("ToneOrange")) || p.hasPower(NameAssist.MakePath("ToneYellow")) || p.hasPower(NameAssist.MakePath("ToneRed"))){
            this.addToBot(new ApplyPowerAction(p,p,new NoTone_pow(p,1)));
        }else{
            int g = AbstractDungeon.getCurrRoom().monsters.monsters.size();
            for(int i=0;i<g;i++){
                this.addToBot(new GainBlockAction(p,this.block));
            }
        }
    }


    public AbstractCard makeCopy(){
        return new BirdsWatchout();
    }
}
