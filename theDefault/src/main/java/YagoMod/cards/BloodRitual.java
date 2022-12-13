package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.LifestealAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;


public class BloodRitual extends AbstractDynamicCard {
    /*
     * (1): Lifesteal 0, gains 2(3) damage when drawn or discarded. Lifesteal. Exhaust.
     */

    public static final String ID = DefaultMod.makeID(BloodRitual.class.getSimpleName());
    public static final String IMG = makeCardPath("BloodRitual.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 0;
    private static final int DAMAGE_INC = 2;
    private static final int DAMAGE_INC_UPG = 1;

    public BloodRitual() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = DAMAGE_INC;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void triggerWhenDrawn() {
        this.baseDamage += magicNumber;
    }

    @Override
    public void triggerOnManualDiscard() {
        this.baseDamage += magicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new LifestealAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    //used by other methods to add a copy to the hand
    public AbstractCard makeCopy() {return new BloodRitual();}

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(DAMAGE_INC_UPG);
            initializeDescription();
        }
    }
}
