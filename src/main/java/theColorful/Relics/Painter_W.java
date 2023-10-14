package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Cards.ReTone;
import theColorful.Helpers.NameAssist;

public class Painter_W extends CustomRelic{

    public static final String ID = NameAssist.MakePath("PainterW");
    private static final Texture IMG = new Texture("TC_resources/img/relics/painterW.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/painterW.png");
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public Painter_W() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Painter_W();
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
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(NameAssist.MakePath("Painter"));
    }

}
