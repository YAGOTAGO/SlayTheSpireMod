package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.SacrificialStabAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

// public class ${NAME} extends AbstractDynamicCard
public class SacrificialStab extends AbstractDynamicCard {

    /**
     * Deal 12 damage, if fatal gain 2(3) energy.
     */

    public static final String ID = DefaultMod.makeID(SacrificialStab.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("SacrificialStab.jpg");// "public static final String IMG = makeCardPath("${NAME}.png");


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int DAMAGE = 12;

    private static final int ENERGY = 2;

    private static final int ENERGY_UPGRADE = 1;

    // /STAT DECLARATION/


    public SacrificialStab() { // public ${NAME}() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = ENERGY;
        magicNumber = baseMagicNumber;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Deal damage
        AbstractDungeon.actionManager.addToBottom(new SacrificialStabAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));

    }

    //used by other methods to add a copy to the hand
    public AbstractCard makeCopy() {return new SacrificialStab();}

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(ENERGY_UPGRADE);
            initializeDescription();
        }
    }
}
