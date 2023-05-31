package theColorful.Cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class GoldCraft extends ToningCards {
    public static final String ID = NameAssist.MakePath("GoldCraft");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/GoldCraft.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final MainTone TONE = MainTone.YELLOW;

    public GoldCraft() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int cnt = 0;
        for (AbstractCard c : p.hand.group) {
            if (c.color == CardColor.COLORLESS) {
                cnt += 1;
            }
        }
        if(cnt > 0){
            if(p.hasPower(NameAssist.MakePath("ToneOrange")) || p.hasPower(NameAssist.MakePath("ToneYellow")) || p.hasPower(NameAssist.MakePath("ToneGreen"))){
                this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,cnt)));
                this.addToBot(new ApplyPowerAction(p,p,new LoseStrengthPower(p,cnt)));
            }else{
                this.addToBot(new GainBlockAction(p,cnt * 2));
                this.addToBot(new ToningAction(p,TONE));
            }
        }
    }


    public AbstractCard makeCopy(){
        return new GoldCraft();
    }
}
