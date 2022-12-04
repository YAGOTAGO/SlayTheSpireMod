package YagoMod.relics;

import YagoMod.DefaultMod;
import YagoMod.powers.DDamagePower;
import YagoMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import static YagoMod.DefaultMod.makeRelicOutlinePath;
import static YagoMod.DefaultMod.makeRelicPath;

public class Anguish extends CustomRelic {

    /*
     * Deal double damage while below 50% HP.
     */

    public static final String ID = DefaultMod.makeID("Anguish");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("AnguishIcon.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    private static boolean hasBeenApplied = false;
    public Anguish() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        if((float)AbstractDungeon.player.currentHealth < (float)(AbstractDungeon.player.maxHealth * 0.5) && !hasBeenApplied){
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DDamagePower(AbstractDungeon.player), 1));
            hasBeenApplied = true;
        }
    }

    @Override
    public void onVictory() {
        hasBeenApplied = false;
    }

    @Override
    public void onBloodied() {
        if(AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !hasBeenApplied) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DDamagePower(AbstractDungeon.player)));
            hasBeenApplied = true;
        }
    }

    @Override
    public void onNotBloodied() {
        if(AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && hasBeenApplied) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, DDamagePower.POWER_ID));
            hasBeenApplied = false;
        }
    }


}
