package theColorful.Cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

import java.util.Iterator;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class GlimpseofDark extends ToningCards {
    public static final String ID = NameAssist.MakePath("GlimpseofDark");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    //private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/GlimpseofDark.png";
    private static final int COST = 1;
    //private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final MainTone TONE = MainTone.PURPLE;


    public GlimpseofDark() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.updateCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator<AbstractMonster> var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = var3.next();
            this.addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo,1,false), 1, true, AbstractGameAction.AttackEffect.NONE));
        }
        this.addToBot(new ToningAction(p,TONE));
    }


    public AbstractCard makeCopy(){
        return new GlimpseofDark();
    }
}
