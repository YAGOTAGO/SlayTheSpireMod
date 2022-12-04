package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.IndoctrinateAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;


public class Indoctrinate extends AbstractDynamicCard {
    /*
     * (2): Deal 10(13) damage, gain that much block next turn.
     */

    public static final String ID = DefaultMod.makeID(Indoctrinate.class.getSimpleName());
    public static final String IMG = makeCardPath("Indoctrinate.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 3;

    public Indoctrinate() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new IndoctrinateAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT, p));
    }

    //used by other methods to add a copy to the hand
    public AbstractCard makeCopy() {return new Indoctrinate();}

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
