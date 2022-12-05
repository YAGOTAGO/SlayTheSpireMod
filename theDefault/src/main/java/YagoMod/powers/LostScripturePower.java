package YagoMod.powers;


import YagoMod.DefaultMod;
import YagoMod.actions.DiscoverAction;
import YagoMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Random;

public class LostScripturePower extends AbstractPower implements CloneablePowerInterface {

    private final AbstractCreature source;
    private int amount;
    public static final String POWER_ID = DefaultMod.makeID("LostScripturePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("YagoModResources/images/powers/Book84.png");
    private static final Texture tex32 = TextureLoader.getTexture("YagoModResources/images/powers/Book32.png");

    public LostScripturePower(final AbstractCreature owner, final AbstractCreature source, int amount){
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;
        this.priority = 1;
        type = PowerType.BUFF;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void atStartOfTurn() {
        this.flash();

        Random rand = new Random();
        int randInt = rand.nextInt(4);
        AbstractCard.CardColor cardCol = AbstractCard.CardColor.RED;

        switch (randInt){
            case 0: cardCol = AbstractCard.CardColor.BLUE; break;
            case 1: cardCol = AbstractCard.CardColor.GREEN; break;
            case 2: cardCol = AbstractCard.CardColor.RED; break;
            case 3: cardCol = AbstractCard.CardColor.PURPLE; break;
            default: break;
        }

        for(int i =0; i<this.amount; i++){
            AbstractDungeon.actionManager.addToBottom(new DiscoverAction(cardCol, true));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public AbstractPower makeCopy() {return new LostScripturePower(owner, source, amount);}
}
