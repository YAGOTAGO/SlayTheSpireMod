package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class Hellfire extends AbstractDynamicCard {

    /*
     * (1): Deal 12(15) damage to all enemies. Discard 1 card.
     */

    public static final String ID = DefaultMod.makeID(Hellfire.class.getSimpleName());
    public static final String IMG = makeCardPath("Hellfire.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 12;
    private static final int DAMAGE_PLUS= 3;

    public Hellfire() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Discard
        AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 1, false));

        //Attack
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_FIRE"));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }


    public AbstractCard makeCopy() {
        return new Hellfire();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_PLUS);
            initializeDescription();
        }
    }
}
