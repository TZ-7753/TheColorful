package theColorful.Cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.LoseNRGNextTurn;
import theColorful.Powers.NoTone_pow;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class Warmth extends ToningCards {
    public static final String ID = NameAssist.MakePath("Warmth");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/Warmth.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final MainTone TONE = MainTone.ORANGE;

    public Warmth() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.block = this.baseBlock = 4;
        this.isEthereal = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void triggerWhenDrawn() {
        AbstractPlayer p = AbstractDungeon.player;
        if(this.upgraded){
            if(p.hasPower(NameAssist.MakePath("ToneOrange")) || p.hasPower(NameAssist.MakePath("ToneRed")) || p.hasPower(NameAssist.MakePath("ToneYellow"))){
                this.addToTop(new GainEnergyAction(1));
            }
        }else{
            if(p.hasPower(NameAssist.MakePath("ToneOrange"))){
                this.addToTop(new GainEnergyAction(1));
            }
        }
        this.addToBot(new GainBlockAction(p,this.block));
    }

    public AbstractCard makeCopy(){
        return new Warmth();
    }
}
