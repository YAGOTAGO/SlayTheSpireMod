package YagoMod.relics;

import YagoMod.DefaultMod;
import YagoMod.powers.PainPower;
import YagoMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.BetterOnLoseHpRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.RupturePower;
import static YagoMod.DefaultMod.makeRelicOutlinePath;
import static YagoMod.DefaultMod.makeRelicPath;

public class Anguish extends CustomRelic implements BetterOnLoseHpRelic{

    /*
     * Whenever you lose hp from a card, gain a stack of pain.
     */

    public static final String ID = DefaultMod.makeID("Anguish");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("AnguishIcon.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    public Anguish() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public int betterOnLoseHp(DamageInfo damageInfo, int i) {
        if(damageInfo.owner == AbstractDungeon.player){

            //Add pain power here
            this.flash();
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PainPower(AbstractDungeon.player, AbstractDungeon.player), 1));
        }

        return i;
    }
}
