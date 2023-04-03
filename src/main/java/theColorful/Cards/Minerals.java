package theColorful.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.Painted;

public class Minerals extends CustomCard {
    public static final String ID = NameAssist.MakePath("Minerals");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    //private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/Minerals.png";
    private static final int COST = -2;
    //private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;
    private static final AbstractCard.CardRarity RARITY = CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;


    public Minerals() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = 4;
        this.magicNumber = this.baseMagicNumber = 3;

    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void triggerWhenDrawn() {
        this.addToTop(new GainBlockAction(AbstractDungeon.player,this.block));
    }

    @Override
    public void triggerOnManualDiscard() {
        this.addToBot(new GainEnergyAction(1));
    }

    @Override
    public void triggerOnExhaust() {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new MetallicizePower(AbstractDungeon.player,this.magicNumber)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            upgradeBlock(2);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    public AbstractCard makeCopy(){
        return new Minerals();
    }
}
