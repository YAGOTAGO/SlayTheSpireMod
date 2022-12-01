package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.FairyPotion;

import static YagoMod.DefaultMod.makeCardPath;

public class FairyBrew extends AbstractDynamicCard {

    /*
     * (6)->(5) Obtain a fairy in a bottle. Reduce the cost of this card by 1 when discarded. Exhaust.
     */

    public static final String ID = DefaultMod.makeID(FairyBrew.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final int COST = 6;
    private static final int COST_UPGRADE = 5;

    public FairyBrew() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ObtainPotionAction(new FairyPotion()));

    }

    @Override
    public void triggerOnManualDiscard() {
        if (this.cost>0)
            this.cost -=1;
    }

    public AbstractCard makeCopy() {return new FairyBrew();}

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(COST_UPGRADE);
            initializeDescription();
        }
    }
}
