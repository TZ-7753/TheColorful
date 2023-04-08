package theColorful.Cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.ToneYellow;

import java.util.ArrayList;
import java.util.List;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class DustFog_TC extends ToningCards {
    public static final String ID = NameAssist.MakePath("DustFog");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    //private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/DustFog.png";
    private static final int COST = 1;
    //private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final MainTone TONE = MainTone.YELLOW;


    public DustFog_TC() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        AbstractCard c1 = new Quicksand_TC();
        AbstractCard c2 = new SandstoneBarrier_TC();
        MultiCardPreview.add(this, c1, c2);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            MultiCardPreview.clear(this);
            AbstractCard c1 = new Quicksand_TC();
            AbstractCard c2 = new SandstoneBarrier_TC();
            c1.upgrade();
            c2.upgrade();
            MultiCardPreview.add(this, c1, c2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard card1 = new Quicksand_TC();
        AbstractCard card2 = new SandstoneBarrier_TC();
        if(this.upgraded){
            card1.upgrade();
            card2.upgrade();
        }
        this.addToBot(new MakeTempCardInHandAction(card1,1));
        this.addToBot(new MakeTempCardInHandAction(card2,1));
        this.addToBot(new ToningAction(p,TONE));

    }

    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tips = new ArrayList();
        tips.add(new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[0], cardStrings.EXTENDED_DESCRIPTION[1]));
        return tips;
    }

    public AbstractCard makeCopy(){
        return new DustFog_TC();
    }
}
