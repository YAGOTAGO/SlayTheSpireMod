package YagoMod.powers;


import YagoMod.DefaultMod;
import YagoMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class LastStandPower extends AbstractPower implements CloneablePowerInterface {

    private AbstractCreature source;
    private int amount;
    private int damage;
    public static final String POWER_ID = DefaultMod.makeID("LastStandPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("YagoModResources/images/powers/BloodDrop84.png");
    private static final Texture tex32 = TextureLoader.getTexture("YagoModResources/images/powers/BloodDrop32.png");

    public LastStandPower(final AbstractCreature owner, final AbstractCreature source, int amountOfTimes, int damage){
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amountOfTimes;
        this.damage = damage;
        this.source = source;
        type = PowerType.DEBUFF;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void atStartOfTurn() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner, this.owner, damage));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + damage + DESCRIPTIONS[1];
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        this.damage += 2;
    }

    @Override
    public AbstractPower makeCopy() {return new LastStandPower(owner, source, amount, damage);}
}
