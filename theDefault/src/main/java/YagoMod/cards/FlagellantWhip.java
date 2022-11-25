package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.LifestealAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

// public class ${NAME} extends AbstractDynamicCard
public class FlagellantWhip extends AbstractDynamicCard {

    /**
     * (3) Deal 38(48) damage. Can only be played if you took damage this turn.
     */


    public static final String ID = DefaultMod.makeID(FlagellantWhip.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int DAMAGE = 38;
    private static final int UPGRADE_PLUS_DMG = 10;

    // /STAT DECLARATION/

    public FlagellantWhip() { // public ${NAME}() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Deal damage
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = "Must take damage before playing this card.";
        return GameActionManager.damageReceivedThisCombat > 0;
    }


    //used by other methods to add a copy to the hand
    public AbstractCard makeCopy() {return new FlagellantWhip();}

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
