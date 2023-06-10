package theColorful.Cards;

import basemod.helpers.TooltipInfo;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.Painted;

import java.util.ArrayList;
import java.util.List;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class Dune extends ToningCards {

    public static final String ID = NameAssist.MakePath("Dune");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    //private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/Dune.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final MainTone TONE = MainTone.YELLOW;


    public Dune() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        MultiCardPreview.add(this, new SandstoneBarrier_TC(), new Oasis());
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            AbstractCard c1 = new SandstoneBarrier_TC();
            AbstractCard c2 = new Oasis();
            MultiCardPreview.clear(this);
            c1.upgrade();
            c2.upgrade();
            MultiCardPreview.add(this, c1, c2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c1 = new SandstoneBarrier_TC();
        AbstractCard c2 = new Oasis();
        if(this.upgraded){
            c1.upgrade();
            c2.upgrade();
        }
        this.addToBot(new MakeTempCardInHandAction(c1,2));
        if(p.hasPower(NameAssist.MakePath("ToneOrange")) || p.hasPower(NameAssist.MakePath("ToneYellow")) || p.hasPower(NameAssist.MakePath("ToneGreen"))){
            this.addToBot(new MakeTempCardInHandAction(c2,1));
        }else{
            this.addToBot(new ToningAction(p,TONE));
        }

    }


    public AbstractCard makeCopy(){
        return new Dune();
    }
}
