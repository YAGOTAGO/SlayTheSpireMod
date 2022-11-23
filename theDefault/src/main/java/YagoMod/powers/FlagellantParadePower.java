package YagoMod.powers;


import YagoMod.DefaultMod;
import YagoMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class FlagellantParadePower extends AbstractPower implements CloneablePowerInterface {

    private AbstractCreature source;
    private int amount;
    private int damage;

    public static final String POWER_ID = DefaultMod.makeID("FlagellantParadePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture("YagoModResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("YagoModResources/images/powers/placeholder_power32.png");


    public FlagellantParadePower(final AbstractCreature owner, final AbstractCreature source, int amountOfTimes, int damage){
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amountOfTimes;
        this.damage = damage;
        this.source = source;

        type = PowerType.BUFF;
        this.isPostActionPower = true;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && info.owner == this.owner) {
            this.flash();

            for(int i = 0; i<this.amount; i++){
                AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(this.owner, this.damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }

    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + damage + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + damage + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public AbstractPower makeCopy() {return new FlagellantParadePower(owner, source, amount, damage);}
}
