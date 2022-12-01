package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static YagoMod.DefaultMod.makeCardPath;


public class DesecratingBlow extends AbstractDynamicCard {

    /*
     * (1) Lose 1 hp. Deal 15(20) damage. Increase damage taken by 2;
     */

    public static final String ID = DefaultMod.makeID(DesecratingBlow.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 15;
    private static final int UPGRADE_PLUS_DMG = 5;
    private static final int DAMAGE_TAKE = 1;
    private static final int DAMAGE_TAKE_INC = 2;

    public DesecratingBlow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = DAMAGE_TAKE;
        this.magicNumber = this.baseMagicNumber;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.baseMagicNumber+= DAMAGE_TAKE_INC;
    }

    //used by other methods to add a copy to the hand
    public AbstractCard makeCopy() {return new DesecratingBlow();}

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
