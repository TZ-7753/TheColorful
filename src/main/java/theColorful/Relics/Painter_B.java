package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Cards.ReTone;
import theColorful.Helpers.NameAssist;

public class Painter_B extends CustomRelic{

    public static final String ID = NameAssist.MakePath("PainterB");
    private static final Texture IMG = new Texture("TC_resources/img/relics/painterB.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/painterB.png");
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public Painter_B() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Painter_B();
    }

    public void obtain() {
        if (AbstractDungeon.player.hasRelic(NameAssist.MakePath("Painter"))) {
            for(int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (((AbstractRelic)AbstractDungeon.player.relics.get(i)).relicId.equals(NameAssist.MakePath("Painter"))) {
                    this.instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    public void atBattleStart() {
        this.flash();
        this.addToBot(new MakeTempCardInHandAction(new ReTone(),3));
    }
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(NameAssist.MakePath("Painter"));
    }

}
