package YagoMod.powers;


import YagoMod.DefaultMod;
import YagoMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BloodAlchemyPower extends AbstractPower implements CloneablePowerInterface {

    private int hpLoss;
    private AbstractCreature source;
    private int amount;

    public static final String POWER_ID = DefaultMod.makeID("BloodAlchemyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture("YagoModResources/images/powers/Potion84.png");
    private static final Texture tex32 = TextureLoader.getTexture("YagoModResources/images/powers/Potion32.png");


    public BloodAlchemyPower(final AbstractCreature owner, final AbstractCreature source, int hpLoss, int potionAmount){
        name = NAME;
        ID = POWER_ID;
        this.hpLoss = hpLoss;
        this.owner = owner;
        this.amount = potionAmount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    //what the power does
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();

            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner, this.owner, this.hpLoss));

            for(int i = 0; i < this.amount; ++i) {
                AbstractDungeon.actionManager.addToBottom(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + hpLoss + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + hpLoss + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        this.hpLoss += stackAmount;
    }

    @Override
    public AbstractPower makeCopy() {return new BloodAlchemyPower(owner, source, hpLoss, amount);}
}
