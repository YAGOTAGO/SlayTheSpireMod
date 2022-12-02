package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class SinnersBlood extends AbstractDynamicCard {
    /*
     * (1): Deal 0 damage 4(5) times. NL Damage is equal to damage taken this turn.
     */

    public static final String ID = DefaultMod.makeID(SinnersBlood.class.getSimpleName());
    public static final String IMG = makeCardPath("SinnersBlood.jpg");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 0;
    private static final int NUM_TIMES = 4;
    private static final int NUM_TIMES_PLUS = 1;

    public SinnersBlood() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;

        this.baseMagicNumber = NUM_TIMES;
        this.magicNumber = this.baseMagicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        for(int i =0; i<magicNumber; i++){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }

    }

    @Override
    public void tookDamage() {
        this.baseDamage = GameActionManager.damageReceivedThisTurn;
    }

    @Override
    public void atTurnStart() {
        this.baseDamage = 0;
    }
    @Override
    public void triggerOnEndOfPlayerTurn() {
        this.baseDamage = 0;
    }

    public AbstractCard makeCopy() {return new SinnersBlood();}

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(NUM_TIMES_PLUS);
            initializeDescription();
        }
    }
}
