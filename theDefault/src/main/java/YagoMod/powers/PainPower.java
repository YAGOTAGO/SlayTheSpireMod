package YagoMod.powers;


import YagoMod.DefaultMod;
import YagoMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PainPower extends AbstractPower implements CloneablePowerInterface {

    private AbstractCreature source;
    private int amountOfPain = 1;

    public static final String POWER_ID = DefaultMod.makeID("PainPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture("YagoModResources/images/powers/BloodDrop84.png");
    private static final Texture tex32 = TextureLoader.getTexture("YagoModResources/images/powers/BloodDrop32.png");

    public PainPower(final AbstractCreature owner, final AbstractCreature source){
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = amountOfPain + DESCRIPTIONS[0];
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amountOfPain += stackAmount;
    }

    @Override
    public AbstractPower makeCopy() {return new PainPower(owner, source);}
}
